package my.games.geometry.events.util;

import java.awt.event.KeyEvent;

import my.games.geometry.events.GameEvent;
import my.games.geometry.events.MoveEvent;
import my.games.geometry.events.NoEvent;
import my.games.geometry.events.ShootEvent;
import my.games.geometry.events.TurnEventCCW;
import my.games.geometry.events.TurnEventCW;
import my.games.geometry.game.objects.GameObject;
import my.games.geometry.networking.PlayerInput;

/**
 * @author LCrystal Converts given input keys from player into GameEvents
 */
public class InputConverter {

	public static GameEvent toEvent(PlayerInput input, GameObject sourceObject) {
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
