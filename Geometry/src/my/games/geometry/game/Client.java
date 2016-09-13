package my.games.geometry.game;

import java.awt.event.KeyListener;

import my.games.geometry.events.EventHandler;
import my.games.geometry.events.EventSource;
import my.games.geometry.events.RemoteSource;
import my.games.geometry.game.engine.ClientRenderEngine;
import my.games.geometry.game.engine.RenderEngine;
import my.games.geometry.game.objects.Controller;
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

	public Client() {
		super();
		world = new World();
		eventHandler = new EventHandler(world);
		renderEngine = new ClientRenderEngine(world);
		comm = new ServerCommunicator();
		comm.openConnectionTo("localhost");
		eventSourceForLocalWorld = new RemoteSource(comm);
		controller = new Controller(comm);
		runner = new ClientWorldRunner(world, renderEngine, eventSourceForLocalWorld, eventHandler);
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
