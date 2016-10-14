package my.games.geometry.game;

import my.games.geometry.events.GameEvent;
import my.games.geometry.events.streams.EventStream;
import my.games.geometry.events.util.EventHandler;
import my.games.geometry.game.engine.RenderEngine;

public class ClientWorldRunner extends WorldRunner {

	public ClientWorldRunner(World world, RenderEngine renderEngine, EventStream eventSource,
			EventHandler eventHandler) {
		super(world, renderEngine, eventSource, eventHandler);
	}

	@Override
	protected void updateState(double delta) {
		// do-while is required here to have at least one world update per tick
		do {
			GameEvent event = eventSource.getNext();
			if (event != null) {
				eventHandler.handleEvent(event);
			}
		} while (eventSource.hasNext());
	}

}
