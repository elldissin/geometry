package my.games.geometry.game;

import my.games.geometry.events.EventHandler;
import my.games.geometry.events.EventSource;
import my.games.geometry.events.GameEvent;
import my.games.geometry.game.engine.RenderEngine;

public class WorldRunner implements Runnable {
	private boolean isRunning;
	private RenderEngine renderEngine;
	private EventSource eventSource;
	private EventHandler eventHandler;
	private Thread thread;
	private World world;

	public WorldRunner(World world, RenderEngine renderEngine, EventSource eventSource, EventHandler eventHandler) {
		isRunning = false;
		this.world = world;
		this.eventSource = eventSource;
		this.renderEngine = renderEngine;
		this.eventHandler = eventHandler;
	}

	public synchronized void start() {
		if (isRunning)
			return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
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
		// do-while is required here to have at least one world update per tick
		do {
			GameEvent event = eventSource.getNext();
			if (event != null) {
				eventHandler.handleEvent(event);
				world.checkForCollisions();
			}
		} while (eventSource.hasNext());
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
}
