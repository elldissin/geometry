package nubiki.game;

import java.awt.Point;
import java.util.ArrayList;

public class Animator {
	private ArrayList<ArrayList <Point>> points;
	private int frameCounter;
	private int tickCounter;
	public Animator(int posX, int posY) {
		frameCounter=0;
		points = new ArrayList<ArrayList<Point>>();
		for(int i=0;i<20;i++) 
			points.add(i,new ArrayList <Point>());
		for(int i=0;i<points.size();i++) {
			for(int j=0;j<3;j++) {
				int x1=(int) (posX+(20*Math.cos(2*Math.PI/(1+2)*j+i*2)));
				int y1=(int) (posY+(20*Math.sin(2*Math.PI/(1+2)*j+i*2)));
				Point p = new Point(x1,y1);
				points.get(i).add(j, p);
			}
		}
	}
	
	public ArrayList <Point> getFrame() {
		if(tickCounter++%1==0)
			frameCounter++;
		if (frameCounter>19)
			frameCounter=0;
		if(points.size()>=frameCounter+1)
			return points.get(frameCounter);
		else return null;
	}
}
