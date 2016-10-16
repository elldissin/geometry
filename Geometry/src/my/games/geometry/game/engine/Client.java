package my.games.geometry.game.engine;

import java.awt.event.KeyListener;

import my.games.geometry.events.streams.EventStream;
import my.games.geometry.events.streams.ServerEventStream;
import my.games.geometry.events.util.EventHandler;
import my.games.geometry.game.controller.Controller;
import my.games.geometry.networking.ServerCommunicator;
import my.games.geometry.ui.engine.ClientRenderEngine;
import my.games.geometry.ui.engine.RenderEngine;

public class Client {
	private static final long serialVersionUID = 1L;

	private RenderEngine renderEngine;
	private World world;
	private Controller controller;
	private EventStream eventSourceForLocalWorld;
	private EventHandler eventHandler;
	private WorldRunner runner;
	private ServerCommunicator comm;
	private int clientID;
	private String hostName;

	public Client() {
		super();
		world = new World();
		eventHandler = new EventHandler(world);
		renderEngine = new ClientRenderEngine(world);
		comm = new ServerCommunicator();
		eventSourceForLocalWorld = new ServerEventStream(comm);
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

	public World getWorld() {
		return world;
	}

	public void start() { // The ID must be already given to client before this
							// call
		controller = new Controller(comm, clientID);
		comm.openConnectionTo(hostName, clientID);
		renderEngine.setFocusedObjectID(comm.getObjectIDassignedByServer());
		runner.start();
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public void setServerIP(String hostName) {
		this.hostName = hostName;

	}

}
