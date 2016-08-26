package my.games.geometry.game;

import java.awt.event.KeyListener;

import geometry.networking.NetworkMessage;
import geometry.networking.events.GameEvent;
import my.games.geometry.behaviour.Behaviour;
import my.games.geometry.behaviour.BumpEffect;
import my.games.geometry.behaviour.PlayerBehaviour;
import my.games.geometry.events.EventHandler;
import my.games.geometry.events.EventManager;
import my.games.geometry.game.engine.ClientRenderEngine;
import my.games.geometry.game.engine.RenderEngine;
import my.games.geometry.game.objects.Controller;
import my.games.geometry.game.objects.Player;
import my.games.geometry.networking.ServerCommunicator;

public class Client implements Runnable {
	private static final long serialVersionUID = 1L;

	private boolean isRunning;
	private RenderEngine renderEngine;
	private World world;
	private Thread thread;
	private Controller controller;
	private EventManager eventManager;
	private EventHandler eventHandler;
	private ServerCommunicator comm;
	private Player player1, player2;

	public Client() {
		super();
		world = new World();
		isRunning = false;
		eventManager = new EventManager();
		eventHandler = new EventHandler(world);
		renderEngine = new ClientRenderEngine(world);
		comm = new ServerCommunicator();
		comm.openConnectionTo("localhost");
		controller = new Controller(comm);
		addPlayers();
	}

	public synchronized void start() {
		if (isRunning)
			return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
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

	// May be required for applet
	public synchronized void stop() {
		if (!isRunning)
			return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}

	private void updateState() {
		// here we add events from server to our eventmanager
		NetworkMessage nm = null;
		while ((nm = comm.getNextMessage()) != null) {
			// NetworkMessage nm=comm.getNextMessage();
			if (nm != null) // the message queue might yet be empty at this
				// stage
				eventManager.addEvent(nm.getEvent());
			// and immediately ask eventmanager if it has new events
			GameEvent ev = eventManager.nextEvent();
			if (ev != null) {
				eventHandler.handleEvent(ev);
				world.update();
				// checkForCollisions(); // if many events within one tick, some
				// collisions are missed
			}
		}
		world.update();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ticks = 30.0;
		double ns = 1000000000 / ticks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				updateState();
				renderEngine.render();
				frames++;
				updates++;
				delta--;
			}
			// to print out fps and updates
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS:"+frames+" Updates:"+updates);
				// frames=0;
				// updates=0;
				// System.out.println("\nTotal
				// collidable:"+collidableObjects.size());
				// System.out.println("Total drawable:"+drawableObjects.size());
				// System.out.println("Total
				// updatable:"+updatableObjects.size());
			}
		}
		stop();
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

}
