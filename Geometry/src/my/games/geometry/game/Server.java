package my.games.geometry.game;

import java.awt.event.KeyEvent;

import my.games.geometry.behaviour.Behaviour;
import my.games.geometry.behaviour.BumpEffect;
import my.games.geometry.behaviour.PlayerBehaviour;
import my.games.geometry.events.EventHandler;
import my.games.geometry.events.EventSource;
import my.games.geometry.events.GameEvent;
import my.games.geometry.events.LocalSource;
import my.games.geometry.events.MoveEvent;
import my.games.geometry.events.ShootEvent;
import my.games.geometry.events.TurnEventCCW;
import my.games.geometry.events.TurnEventCW;
import my.games.geometry.game.engine.NoRenderEngine;
import my.games.geometry.game.engine.RenderEngine;
import my.games.geometry.game.objects.Player;
import my.games.geometry.networking.ClientService;
import my.games.geometry.networking.NetworkMessage;
import my.games.geometry.networking.PlayerInput;

public class Server {
	private static final long serialVersionUID = 1L;

	private RenderEngine renderEngine;
	private World world;
	private EventSource eventSource;
	private EventHandler eventHandler;
	private ClientService clientService;
	private WorldRunner runner;
	private Player player1, player2;

	public Server() {
		super();
		world = new World();
		eventHandler = new EventHandler(world);
		renderEngine = new NoRenderEngine();
		eventSource = new LocalSource(); // TODO change to remote
		addPlayers();
		runner = new ServerWorldRunner(world, renderEngine, eventSource, eventHandler);
		clientService = new ClientService();
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
		// runner.start();
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
		if (eventSource.hasNext())
			for (int j = 0; j < clientService.getClientList().size(); j++)
				clientService.getClientList().get(j).sendMessage(createNetworkMessage(eventSource.getNext()));
	}

	private void pollClientsForInput() {
		PlayerInput input = null;
		for (int i = 0; i < clientService.getClientList().size(); i++)
			input = clientService.getClientList().get(i).getInput();
		if (input != null && eventFromInput(input) != null)
			eventSource.addEvent(eventFromInput(input));
	}

	private GameEvent eventFromInput(PlayerInput input) {
		GameEvent ev = null;
		switch (input.getKeyCode()) {
		case KeyEvent.VK_W:
			ev = new MoveEvent(1);
			break;
		case KeyEvent.VK_D:
			ev = new TurnEventCW(1);
			break;
		case KeyEvent.VK_A:
			ev = new TurnEventCCW(1);
			break;
		case KeyEvent.VK_Q:
			ev = new ShootEvent(1);
			break;
		case KeyEvent.VK_UP:
			ev = new MoveEvent(2);
			break;
		case KeyEvent.VK_RIGHT:
			ev = new TurnEventCW(2);
			break;
		case KeyEvent.VK_LEFT:
			ev = new TurnEventCCW(2);
			break;
		case KeyEvent.VK_CONTROL:
			ev = new ShootEvent(2);
			break;
		}
		return ev;
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

	private NetworkMessage createNetworkMessage(GameEvent event) {
		NetworkMessage msg = new NetworkMessage();
		msg.setEvent(event);
		return msg;
	}

}
