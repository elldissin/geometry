package my.games.geometry.events;

import java.awt.event.KeyEvent;

import my.games.geometry.networking.PlayerInput;

/**
 * @author LCrystal Converts given input keys from player into GameEvents
 */
public class InputConverter {

	public static GameEvent toEvent(PlayerInput input) {
		GameEvent ev = null;
		switch (input.getKeyCode()) {
		case KeyEvent.VK_W:
			ev = new MoveEvent(1);
			break;
		case KeyEvent.VK_D:
			ev = new TurnEventCW(1);
			break;
		case KeyEvent.VK_A:
			ev = new TurnEventCCW(1);
			break;
		case KeyEvent.VK_Q:
			ev = new ShootEvent(1);
			break;
		case KeyEvent.VK_UP:
			ev = new MoveEvent(2);
			break;
		case KeyEvent.VK_RIGHT:
			ev = new TurnEventCW(2);
			break;
		case KeyEvent.VK_LEFT:
			ev = new TurnEventCCW(2);
			break;
		case KeyEvent.VK_CONTROL:
			ev = new ShootEvent(2);
			break;
		}
		return ev;
	}
}
