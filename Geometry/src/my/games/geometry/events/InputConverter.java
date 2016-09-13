package my.games.geometry.events;

import java.awt.event.KeyEvent;

import my.games.geometry.networking.PlayerInput;

/**
 * @author LCrystal Converts given input keys from player into GameEvents
 */
public class InputConverter {

	public static GameEvent toEvent(PlayerInput input) { // LATER magic numbers (0) (1)
		GameEvent ev = null;
		switch (input.getKeyCode()) {
		case KeyEvent.VK_W:
			ev = new MoveEvent(0);
			break;
		case KeyEvent.VK_D:
			ev = new TurnEventCW(0);
			break;
		case KeyEvent.VK_A:
			ev = new TurnEventCCW(0);
			break;
		case KeyEvent.VK_Q:
			ev = new ShootEvent(0);
			break;
		case KeyEvent.VK_UP:
			ev = new MoveEvent(1);
			break;
		case KeyEvent.VK_RIGHT:
			ev = new TurnEventCW(1);
			break;
		case KeyEvent.VK_LEFT:
			ev = new TurnEventCCW(1);
			break;
		case KeyEvent.VK_CONTROL:
			ev = new ShootEvent(1);
			break;
		}
		return ev;
	}
}
