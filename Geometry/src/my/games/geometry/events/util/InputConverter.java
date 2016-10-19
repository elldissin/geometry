package my.games.geometry.events.util;

import java.awt.event.KeyEvent;

import my.games.geometry.events.GameEvent;
import my.games.geometry.events.NoEvent;
import my.games.geometry.events.ToggleMoveEvent;
import my.games.geometry.events.ToggleShootEvent;
import my.games.geometry.events.ToggleTurnEvent;
import my.games.geometry.game.movers.Mover;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.networking.PlayerInput;

/**
 * @author LCrystal Converts given input keys from player into GameEvents
 */
public class InputConverter {
	// FIXME add key press/release checking
	public static GameEvent toEvent(PlayerInput input, GameObject sourceObject) {
		GameEvent ev = null;
		switch (input.getKeyCode()) {
		case KeyEvent.VK_UP:
			ev = new ToggleMoveEvent(sourceObject, input.isKeyPressed());
			return ev;
		case KeyEvent.VK_RIGHT:
			ev = new ToggleTurnEvent(sourceObject, Mover.TurnDirection.CW, input.isKeyPressed());
			return ev;
		case KeyEvent.VK_LEFT:
			ev = new ToggleTurnEvent(sourceObject, Mover.TurnDirection.CCW, input.isKeyPressed());
			return ev;
		case KeyEvent.VK_CONTROL:
			ev = new ToggleShootEvent(sourceObject, input.isKeyPressed());
			return ev;
		}
		return new NoEvent(sourceObject);
	}
}
