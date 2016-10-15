package my.games.geometry.game.engine;

import my.games.geometry.events.GameEvent;
import my.games.geometry.events.streams.EventStream;
import my.games.geometry.events.util.EventHandler;
import my.games.geometry.ui.engine.RenderEngine;

public class ServerWorldRunner extends WorldRunner {

	public ServerWorldRunner(World world, RenderEngine renderEngine, EventStream eventSource,
			EventHandler eventHandler) {
		super(world, renderEngine, eventSource, eventHandler);
	}

	@Override
	protected void updateState(double delta) {
		// do-while is required here to have at least one world update per tick
		do {
			GameEvent event = eventSource.getNext();
			if (event != null) { // server is handling events only from Clients
				eventHandler.handleEvent(event);
				world.checkForCollisions();
			}
		} while (eventSource.hasNext());
		world.update(delta);
	}
}
