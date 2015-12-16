package nubiki.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class GameCamera extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final int WIDTH=800;
    private final int HEIGHT=600;
    private List<Drawable> drawableObjects;
    
	public GameCamera() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	public void show(List<Drawable> objects) {
		drawableObjects=objects;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
//		System.out.println("painting canvas...");
		super.paintComponent(g);
		for(int i=0; i<drawableObjects.size();i++)
			drawableObjects.get(i).draw(g);
	}
}
