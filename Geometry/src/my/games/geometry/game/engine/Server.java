package my.games.geometry.game.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import javax.swing.SwingUtilities;

import my.games.geometry.events.GameEvent;
import my.games.geometry.events.streams.ClientEventStream;
import my.games.geometry.events.streams.EventStream;
import my.games.geometry.events.util.EventHandler;
import my.games.geometry.events.util.GameEventObserver;
import my.games.geometry.events.util.InputConverter;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.networking.BufferedEventSender;
import my.games.geometry.networking.ClientService;
import my.games.geometry.networking.NetworkMessage;
import my.games.geometry.networking.PlayerInput;
import my.games.geometry.ui.ServerStatusWindow;
import my.games.geometry.ui.engine.NoRenderEngine;
import my.games.geometry.ui.engine.RenderEngine;

public class Server {
	private static final long serialVersionUID = 1L;

	private RenderEngine renderEngine;
	private World world;
	private EventStream eventSourceForLocalWorld;
	private EventHandler eventHandler;
	private ClientService clientService;
	private GameEventObserver gameEventObserver; // Observer
	private BufferedEventSender bufferedSender;
	private WorldRunner runner;
	private ServerStatusWindow logDisplay;
	private LogDisplayNotifier logDisplayNotifier;
	private Map<Integer, Integer> clientToPlayerMap;

	public Server() {
		super();
		world = new World();
		world.initializeWorld();
		gameEventObserver = new GameEventObserver();
		logDisplayNotifier = new LogDisplayNotifier();
		world.registerWorldObserver(gameEventObserver);
		world.registerLogDisplayNotifyer(logDisplayNotifier);
		eventHandler = new EventHandler(world);
		renderEngine = new NoRenderEngine();
		eventSourceForLocalWorld = new ClientEventStream();
		runner = new ServerWorldRunner(world, renderEngine, eventSourceForLocalWorld, eventHandler);
		clientService = new ClientService();
		clientToPlayerMap = new HashMap<Integer, Integer>();
		bufferedSender = new BufferedEventSender();
	}

	public RenderEngine getRenderEngine() {
		return renderEngine;
	}

	public void setRenderEngine(RenderEngine renderEngine) {
		this.renderEngine = renderEngine;
	}

	public void start() {
		runner.start();
		clientService.start();
		while (true) {
			clientService.sendWorldStateToNewClients(world, clientToPlayerMap);
			closeObsoleteClients();
			pollClientsForInput();
			notifyClients();
			notifyLogWindow();
			try {
				Thread.sleep(5); // To reduce CPU load
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void notifyLogWindow() {
		if (logDisplay != null)
			if (logDisplayNotifier.isChanged()) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						logDisplay.logTextInField("System time: " + String.valueOf(System.currentTimeMillis()), 0);
						logDisplay.logTextInField("Active players: " + clientService.getClientList().size(), 1);
					}
				});

			}
	}

	private void notifyClients() {
		Queue<GameEvent> eventsQueue = gameEventObserver.getCopyForProcessing();
		while (eventsQueue.size() > 0) {
			NetworkMessage msg = new NetworkMessage();
			GameEvent ev = eventsQueue.poll().copy();
			msg.setEvent(ev);
			bufferedSender.sendMessageTo(msg, clientService.getClientList());
		}
	}

	private void pollClientsForInput() {
		PlayerInput input = null;
		GameObject sourcePlayer = null;
		// LATER check if correct loop logic
		for (int i = 0; i < clientService.getClientList().size(); i++) {
			input = clientService.getClientList().get(i).getInput();
			if (input != null) {
				int idToBeFound = clientIDtoPlayerID(input.getClientID());
				sourcePlayer = world.getObjectByID(idToBeFound);
				eventSourceForLocalWorld.addEvent(InputConverter.toEvent(input, sourcePlayer));
			}
		}
	}

	public void closeObsoleteClients() {
		for (int i = 0; i < clientService.getClientList().size(); i++) {
			if (!clientService.getClientList().get(i).isConnected()) {
				System.out.println("The following client disconnected from server: "
						+ clientService.getClientList().get(i).getClientID());
				clientService.getClientList().remove(i);
			}
		}
	}

	public void setLogDisplay(ServerStatusWindow logDisplay) {
		this.logDisplay = logDisplay;
		world.registerLogDisplayNotifyer(logDisplayNotifier);
	}

	private int clientIDtoPlayerID(int clientID) {
		return clientToPlayerMap.get(clientID);
	}
}
