package my.games.geometry.game;

import java.util.ArrayList;

import my.games.geometry.behaviour.Behaviour;
import my.games.geometry.behaviour.BumpEffect;
import my.games.geometry.behaviour.PlayerBehaviour;
import my.games.geometry.events.EventHandler;
import my.games.geometry.events.EventSource;
import my.games.geometry.events.LocalSource;
import my.games.geometry.game.engine.NoRenderEngine;
import my.games.geometry.game.engine.RenderEngine;
import my.games.geometry.game.objects.Player;
import my.games.geometry.networking.ConnectedClient;

public class Server {
	private static final long serialVersionUID = 1L;

	private RenderEngine renderEngine;
	private World world;
	private EventSource eventSource;
	private EventHandler eventHandler;
	private WorldRunner runner;
	private Player player1, player2;
	ArrayList<ConnectedClient> clientList = new ArrayList<ConnectedClient>();

	public Server() {
		super();
		world = new World();
		eventHandler = new EventHandler(world);
		renderEngine = new NoRenderEngine();
		eventSource = new LocalSource();
		addPlayers();
		runner = new WorldRunner(world, renderEngine, eventSource, eventHandler);
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
		runner.start();
	}

}
