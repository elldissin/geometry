package nubiki.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

public class GameCamera extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int viewWidth;
    private int viewHeight;
	private List<Drawable> drawableObjects;
    private Point viewOffset;
    
	public GameCamera() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		viewOffset=new Point(0,0);
		viewWidth=800;
		viewHeight=600;
	}
	
    public int getViewWidth() {
		return viewWidth;
	}

	public void setViewWidth(int viewWidth) {
		this.viewWidth = viewWidth;
	}

	public int getViewHeight() {
		return viewHeight;
	}

	public void setViewHeight(int viewHeight) {
		this.viewHeight = viewHeight;
	}
	
	public Point getViewOffset() {
		return viewOffset;
	}

	public void setViewOffset(Point viewOffset) {
		this.viewOffset = viewOffset;
	}

	public void show(List<Drawable> objects) {
		drawableObjects=objects;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
//		System.out.println("painting canvas...");
		super.paintComponent(g);
		g.translate((int)-viewOffset.getX(), (int)-viewOffset.getY());
		if(drawableObjects!=null)
		for(int i=0; i<drawableObjects.size();i++)
			drawableObjects.get(i).draw(g);
	}
}
