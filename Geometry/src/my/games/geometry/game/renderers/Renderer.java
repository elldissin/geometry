package my.games.geometry.game.renderers;

import java.awt.Graphics;

import my.games.geometry.game.objects.GameObject;

public interface Renderer {
	public void draw(Graphics g, GameObject obj);
}
