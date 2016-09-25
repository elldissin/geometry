package my.games.geometry.events;

import java.awt.event.KeyEvent;

import my.games.geometry.game.objects.GameObject;
import my.games.geometry.networking.PlayerInput;

/**
 * @author LCrystal Converts given input keys from player into GameEvents
 */
public class InputConverter {

	public static GameEvent toEvent(PlayerInput input, GameObject sourceObject) { // LATER magic
																					// numbers (0)
																					// (1)
		GameEvent ev = null;
		switch (input.getKeyCode()) {
		case KeyEvent.VK_W:
			ev = new MoveEvent(sourceObject);
			return ev;
		case KeyEvent.VK_D:
			ev = new TurnEventCW(sourceObject);
			return ev;
		case KeyEvent.VK_A:
			ev = new TurnEventCCW(sourceObject);
			return ev;
		case KeyEvent.VK_Q:
			ev = new ShootEvent(sourceObject);
			return ev;
		}
		return new NoEvent(sourceObject);
	}
}
