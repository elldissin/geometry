package nubiki.game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class Player extends GameObject implements Shooting {
	private static final long serialVersionUID = 1L;
	private int health;
	private int level;
	private  ArrayList<Projectile> projectiles;

	public Player() {
		super(); 
		projectiles=new ArrayList<Projectile>();
		health=100;
		level=1;
		body();
	}

	public ArrayList<Point> body() {
//		points.clear();
		if(points.isEmpty()) {
			System.out.println("player points recalculation...");
			for(int i=0;i<level+2;i++) {
				int x1=(int) (posX+(objWidth*Math.cos(2*Math.PI/(level+2)*i)));
				int y1=(int) (posY+(objHeight*Math.sin(2*Math.PI/(level+2)*i)));
				Point p = new Point(x1,y1);
				points.add(i, p);
			}
		}
		return points;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}

	//@Override
	public void draw(Graphics g) {
		body();
		
		Graphics2D g2 = (Graphics2D) g;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
		path.moveTo(points.get(0).getX(), points.get(0).getY());
		for(int i=1;i<points.size();i++) {
			path.lineTo(points.get(i).getX(), points.get(i).getY());	
		}		
		path.closePath();
		g2.draw(path);
		g2.fill(path);
	}
	
	public void move() {
		for(int i=0;i<points.size();i++)
			points.get(i).translate(speedX, speedY);
	}
	//try to handle projectile moving and drawing inside player
	@Override
	public void shoot() {
		Projectile projectile = new Projectile();
		projectiles.add(projectile);
	}
}
