package nubiki.game;

import java.awt.Event;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import nubiki.behaviour.Behaviour;
import nubiki.behaviour.BumpEffect;
import nubiki.behaviour.PlayerBehaviour;
import nubiki.events.EventManager;
import nubiki.events.GameEvent;

//import com.sun.swing.internal.plaf.synth.resources.synth;

public class GameManager implements Runnable {
	private static final long serialVersionUID = 1L;

	private boolean isRunning;
	private Thread thread;
	private GameCamera camera1, camera2;
	private Controller controller;
	private EffectManager effManager;
	private EventManager eventManager;
	private Player player1, player2;
	private static List<GameObject> updatableObjects;
	private static List<GameObject> drawableObjects; //to avoid unnecessary drawing on all objects
	private static List<GameObject> collidableObjects;

	public GameManager() {
		super();
		isRunning = false;
		camera1 = new GameCamera();
		camera2 = new GameCamera();
		effManager = new EffectManager();
		eventManager = new EventManager();
		updatableObjects = new ArrayList<GameObject>();
		drawableObjects = new ArrayList<GameObject>();
		collidableObjects = new ArrayList<GameObject>();
		controller = new Controller(eventManager);
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
		player1 = (Player)GameObjectManager.createGameObject("player", 100, 100);
		player2 = (Player)GameObjectManager.createGameObject("player", 400, 100);
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

	private void render() {
		// ties camera 1 to player 1
		Point p = new Point((int) player1.getPos().x - camera1.getViewWidth() / 2,
				(int) player1.getPos().y - camera1.getViewHeight() / 2);
		camera1.setViewOffset(p);
		camera1.show(drawableObjects);
		// ties camera 2 to player 2
		p = new Point((int) player2.getPos().x - camera2.getViewWidth() / 2,
				(int) player2.getPos().y - camera2.getViewHeight() / 2);
		camera2.setViewOffset(p);
		camera2.show(drawableObjects);
	}

	private void updateState() {
		GameEvent ev=eventManager.nextEvent();
		if(ev!=null)
			ev.doEvent();
		// Handles destruction of obsolete objects
		for (int i = 0; i < updatableObjects.size(); i++) {
			if (updatableObjects.get(i).isDestroyed()) {
				removeFromScene(updatableObjects.get(i));
			}
		}

		// Handles collisions
		for (int i = 0; i < collidableObjects.size(); i++)
			for (int j = 0; j < collidableObjects.size(); j++) {
				if (collidableObjects.get(i).isColliding(collidableObjects.get(j)) && i != j) {
//					 System.out.println("Collision happened");
					GameObject actor1 = collidableObjects.get(i);
					GameObject actor2 = collidableObjects.get(j);
//					System.out.println("Collision happened objects: " + actor1.getObjectID() +" "+actor2.getObjectID());
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

		// Handles objects updates
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
				render();
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

	public GameCamera getCamera(int number) {
		if (number == 1)
			return camera1;
		if (number == 2)
			return camera2;
		return new GameCamera(); // if not 1 or 2 return empty camera
	}

	public KeyListener getController() {
		return controller;
	}
}
