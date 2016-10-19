package my.games.geometry.game.renderers;

import java.awt.Graphics;
import java.io.Serializable;

import my.games.geometry.game.objects.GameObject;

public class NoRenderer implements Renderer, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoRenderer(GameObject objectToDraw) {
		// TO BE EMPTY
	}

	@Override
	public void draw(Graphics g) {
		// TO BE EMPTY
	}

}
