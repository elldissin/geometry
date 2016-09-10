package my.games.geometry.game;

import java.awt.event.KeyListener;

import my.games.geometry.behaviour.Behaviour;
import my.games.geometry.behaviour.BumpEffect;
import my.games.geometry.behaviour.PlayerBehaviour;
import my.games.geometry.events.EventHandler;
import my.games.geometry.events.EventSource;
import my.games.geometry.events.RemoteSource;
import my.games.geometry.game.engine.ClientRenderEngine;
import my.games.geometry.game.engine.RenderEngine;
import my.games.geometry.game.objects.Controller;
import my.games.geometry.game.objects.Player;
import my.games.geometry.networking.ServerCommunicator;

public class Client {
	private static final long serialVersionUID = 1L;

	private RenderEngine renderEngine;
	private World world;
	private Controller controller;
	private EventSource eventSourceForLocalWorld;
	private EventHandler eventHandler;
	private WorldRunner runner;
	private ServerCommunicator comm;
	private Player player1, player2;

	public Client() {
		super();
		world = new World();
		eventHandler = new EventHandler(world);
		renderEngine = new ClientRenderEngine(world);
		comm = new ServerCommunicator();
		comm.openConnectionTo("localhost");
		eventSourceForLocalWorld = new RemoteSource(comm);
		controller = new Controller(comm);
		addPlayers();
		runner = new ClientWorldRunner(world, renderEngine, eventSourceForLocalWorld, eventHandler);

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
		controller.takeControlOf(player1);
		controller.takeControlOf(player2);
	}

	public KeyListener getController() {
		return controller;
	}

	public RenderEngine getRenderEngine() {
		return renderEngine;
	}

	public void setRenderEngine(RenderEngine renderEngine) {
		this.renderEngine = renderEngine;
	}

	public void start() {
		runner.start();
	}

}
