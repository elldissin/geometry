package my.games.geometry.game;

import my.games.geometry.events.EventHandler;
import my.games.geometry.events.EventSource;
import my.games.geometry.events.GameEvent;
import my.games.geometry.game.engine.RenderEngine;

public class ClientWorldRunner extends WorldRunner {

	public ClientWorldRunner(World world, RenderEngine renderEngine, EventSource eventSource,
			EventHandler eventHandler) {
		super(world, renderEngine, eventSource, eventHandler);
	}

	@Override
	protected void updateState() {
		// do-while is required here to have at least one world update per tick
		do {
			GameEvent event = eventSource.getNext();
			if (event != null) {
				eventHandler.handleEvent(event);
			}
		} while (eventSource.hasNext());
	}

}
