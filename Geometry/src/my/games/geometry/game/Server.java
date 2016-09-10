package my.games.geometry.game;

import my.games.geometry.behaviour.Behaviour;
import my.games.geometry.behaviour.BumpEffect;
import my.games.geometry.behaviour.PlayerBehaviour;
import my.games.geometry.events.EventHandler;
import my.games.geometry.events.EventSource;
import my.games.geometry.events.InputConverter;
import my.games.geometry.events.LocalSource;
import my.games.geometry.game.engine.NoRenderEngine;
import my.games.geometry.game.engine.RenderEngine;
import my.games.geometry.game.objects.Player;
import my.games.geometry.networking.ClientEventNotifier;
import my.games.geometry.networking.ClientService;
import my.games.geometry.networking.PlayerInput;

public class Server {
	private static final long serialVersionUID = 1L;

	private RenderEngine renderEngine;
	private World world;
	private EventSource eventSourceForLocalWorld;
	private EventHandler eventHandler;
	private ClientService clientService;
	private ClientEventNotifier clientEventNotifier;
	private WorldRunner runner;
	private Player player1, player2;

	public Server() {
		super();
		world = new World();
		eventHandler = new EventHandler(world);
		renderEngine = new NoRenderEngine();
		eventSourceForLocalWorld = new LocalSource(); // LATER change to remote
		addPlayers();
		runner = new ServerWorldRunner(world, renderEngine, eventSourceForLocalWorld, eventHandler);
		clientService = new ClientService();
		clientEventNotifier = new ClientEventNotifier();
	}

	private void addPlayers() {
		player1 = (Player) world.createGameObject("player", 100, 100, 0.0);
		player2 = (Player) world.createGameObject("player", 400, 100, 0.0);
		Behaviour beh1 = new PlayerBehaviour();
		Behaviour beh2 = new PlayerBehaviour();
		player1.setBehaviour(beh1);
		player2.setBehaviour(beh2);
		player1.addOnHitEffect(new BumpEffect(0));
		player2.addOnHitEffect(new BumpEffect(0));
	}

	public RenderEngine getRenderEngine() {
		return renderEngine;
	}

	public void setRenderEngine(RenderEngine renderEngine) {
		this.renderEngine = renderEngine;
	}

	public void start() {
		// runner.start(); //LATER - enable this when ready
		clientService.start();
		while (true) {
			closeObsoleteClients();
			pollClientsForInput();
			notifyClients();
			try {
				Thread.sleep(5); // To reduce CPU load
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void notifyClients() {
		clientEventNotifier.notifyClients(clientService.getClientList());
	}

	private void pollClientsForInput() {
		PlayerInput input = null;
		// LATER check if correct loop logic
		for (int i = 0; i < clientService.getClientList().size(); i++) {
			input = clientService.getClientList().get(i).getInput();
			if (input != null && InputConverter.toEvent(input) != null) {
				eventSourceForLocalWorld.addEvent(InputConverter.toEvent(input));
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
}
