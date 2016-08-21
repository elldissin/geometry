package my.games.geometry.game;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

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
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.game.objects.Player;
import my.games.geometry.game.objects.StaticObject;
import my.games.geometry.game.objects.Updatable;
import my.games.geometry.networking.ServerCommunicator;

//import com.sun.swing.internal.plaf.synth.resources.synth;

public class Client implements Runnable {
	private static final long serialVersionUID = 1L;

	private boolean isRunning;
	private RenderEngine renderEngine;
	private Thread thread;
	private Controller controller;
	private EffectManager effManager;
	private EventManager eventManager;
	private ServerCommunicator comm;
	private Player player1, player2;
	private static List<GameObject> updatableObjects;
	private static List<GameObject> drawableObjects;
	private static List<GameObject> collidableObjects;

	public Client() {
		super();
		isRunning = false;
		effManager = new EffectManager();
		eventManager = new EventManager();
		updatableObjects = new ArrayList<GameObject>();
		drawableObjects = new ArrayList<GameObject>();
		renderEngine = new ClientRenderEngine(drawableObjects);
		collidableObjects = new ArrayList<GameObject>();
		comm = new ServerCommunicator();
		comm.openConnectionTo("localhost");
		controller = new Controller(eventManager, comm);
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
		player1 = (Player) GameObjectManager.createGameObject("player", 100, 100);
		player2 = (Player) GameObjectManager.createGameObject("player", 400, 100);
		StaticObject obst = new StaticObject(250, 50);
		Behaviour beh1 = new PlayerBehaviour();
		Behaviour beh2 = new PlayerBehaviour();
		player1.setBehaviour(beh1);
		player2.setBehaviour(beh2);

		player1.addOnHitEffect(new BumpEffect(0));
		player2.addOnHitEffect(new BumpEffect(0));
		obst.addOnHitEffect(new BumpEffect(0));

		updatableObjects.add(player1);
		updatableObjects.add(player2);

		drawableObjects.add(player1);
		drawableObjects.add(player2);
		drawableObjects.add(obst);

		collidableObjects.add(player1);
		collidableObjects.add(player2);
		collidableObjects.add(obst);

		controller.takeControlOf(player1);
		controller.takeControlOf(player2);
	}

	// Called from method, that creates the projectile as a result of shooting
	public static void addProjectile(GameObject obj) {
		if (obj != null) {
			updatableObjects.add(obj);
			drawableObjects.add(obj);
			collidableObjects.add(obj);
		}
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
				EventHandler.handleEvent(ev);
				checkForCollisions();
			}
		}
		checkForCollisions();
		// Handles destruction of obsolete objects
		for (int i = 0; i < updatableObjects.size(); i++) {
			if (updatableObjects.get(i).isDestroyed()) {
				removeFromScene(updatableObjects.get(i));
			}
		}

		// Handles objects updates DOES NOTHING AT THE MOMENT
		for (int i = 0; i < updatableObjects.size(); i++) {
			updatableObjects.get(i).update();
		}
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

	private void removeFromScene(Updatable obj) {
		updatableObjects.remove(obj);
		drawableObjects.remove(obj);
		collidableObjects.remove(obj);
	}

	public KeyListener getController() {
		return controller;
	}

	private void checkForCollisions() {
		// Handles collisions
		for (int i = 0; i < collidableObjects.size(); i++)
			for (int j = 0; j < collidableObjects.size(); j++) {
				if (collidableObjects.get(i).isColliding(collidableObjects.get(j)) && i != j) {
					// System.out.println("Collision happened");
					GameObject actor1 = collidableObjects.get(i);
					GameObject actor2 = collidableObjects.get(j);
					// System.out.println("Collision happened objects: " +
					// actor1.getObjectID() +" "+actor2.getObjectID());
					for (int k = 0; k < actor2.getOnHitEffects().size(); k++) {
						effManager.handle(actor1, actor2.getOnHitEffects().get(k));
					}
					for (int k = 0; k < actor1.getOnHitEffects().size(); k++) {
						effManager.handle(actor2, actor1.getOnHitEffects().get(k));
					}
					// collidableObjects.get(i).destroy();
					// collidableObjects.get(i).destroy();
					// collidableObjects.get(j).destroy();//empty destroy method
					// in projectile to ignore ?
				}
			}
	}

	public RenderEngine getRenderEngine() {
		return renderEngine;
	}

	public void setRenderEngine(RenderEngine renderEngine) {
		this.renderEngine = renderEngine;
	}

}
