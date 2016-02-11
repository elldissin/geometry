package nubiki.game;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

//import com.sun.swing.internal.plaf.synth.resources.synth;

public class GameManager implements Runnable, KeyListener {
    private static final long serialVersionUID = 1L;
    
    private boolean isRunning;
    private Thread thread;
    private GameCamera camera1, camera2;
    private EffectManager effManager;
    
    private Player player1,player2;
    private static List<Updatable> updatableObjects;
    private static List<Drawable> drawableObjects;
    private static List<Collidable> collidableObjects;
	public GameManager() {
		super();
		isRunning=false;
		camera1=new GameCamera();
		camera2=new GameCamera();
		effManager=new EffectManager();
		updatableObjects=new ArrayList<Updatable>();
		drawableObjects=new ArrayList<Drawable>();
		collidableObjects=new ArrayList<Collidable>();
		addPlayers();
	}
	
	public synchronized void start() {
		if(isRunning)
			return;
		isRunning=true;
		thread=new Thread(this);
		thread.start();
	}
	
	private void addPlayers() {
		player1=new Player(100,100);
		player2=new Player(400,100);
		
		player1.setBehaviour(new SlowBehaviour(player1));
		player2.setBehaviour(new SlowBehaviour(player2));
		
		updatableObjects.add(player1);
		updatableObjects.add(player2);
		
		drawableObjects.add(player1);
		drawableObjects.add(player2);
		
		collidableObjects.add(player1);
		collidableObjects.add(player2);
	}

	protected static void addProjectile(Projectile obj) {
		if(obj!=null) {
			updatableObjects.add(obj);
			drawableObjects.add(obj);
			collidableObjects.add(obj);
		}
	}
	
	
	//May be required for applet
	public synchronized void stop() {
		if(!isRunning)
			return;
		isRunning=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("Key pressed...");
		int code = e.getKeyCode();
		if (code==KeyEvent.VK_UP){
			player2.setMoving();
		}
		if (code==KeyEvent.VK_RIGHT){
			player2.setTurningCW();
		}
		if (code==KeyEvent.VK_LEFT){
			player2.setTurningCCW();
		}
		
		if (code==KeyEvent.VK_W){
			player1.setMoving();
		}
		if (code==KeyEvent.VK_D){
			player1.setTurningCW();
		}
		if (code==KeyEvent.VK_A){
			player1.setTurningCCW();
		}
		
		if (code==KeyEvent.VK_Q) { //casting to be fixed
			player1.shoot();
		}
		
		if (code==KeyEvent.VK_CONTROL) { //casting to be fixed
			player2.shoot();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		//this needs to be fixed, stopping even when releasing the other key
		if (code==KeyEvent.VK_UP){
			player2.setStopped();;
		}
		if (code==KeyEvent.VK_LEFT){
			player2.setNotTurning();
		}
		if (code==KeyEvent.VK_RIGHT){
			player2.setNotTurning();
		}
		
		if (code==KeyEvent.VK_W){
			player1.setStopped();
		}
		if (code==KeyEvent.VK_A){
			player1.setNotTurning();
		}
		if (code==KeyEvent.VK_D){
			player1.setNotTurning();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	private void render() {
		//ties camera 1 to player 1
		Point p = new Point((int)player1.getPosX()-camera1.getViewWidth()/2,(int)player1.getPosY()
				-camera1.getViewHeight()/2);
		camera1.setViewOffset(p);
		camera1.show(drawableObjects);
		//ties camera 2 to player 2
		p = new Point((int)player2.getPosX()-camera2.getViewWidth()/2,(int)player2.getPosY()
				-camera2.getViewHeight()/2);
		camera2.setViewOffset(p);
		camera2.show(drawableObjects);
	}
	
	private void updateState() {
		//Handles destruction of obsolete objects
		for(int i=0; i<updatableObjects.size();i++) {
			if(updatableObjects.get(i).isDestroyed()) {
				removeFromScene(updatableObjects.get(i));
			}
		}
		
		//Handles collisions
		for(int i=0; i<collidableObjects.size();i++)
			for(int j=0; j<collidableObjects.size();j++) {
				if(collidableObjects.get(i).isColliding(collidableObjects.get(j)) && i!=j) {
					System.out.println("Collision happened");
					GameObject converted = (GameObject)(collidableObjects.get(i));
					if (converted.getBehaviour()!=null)
						effManager.handle(converted.getBehaviour(), new SlowEffect(10));
//					collidableObjects.get(i).destroy();
//					collidableObjects.get(i).destroy();
//					collidableObjects.get(j).destroy();//empty destroy method in projectile to ignore ?
				}
			}

		//Handles objects updates
		for(int i=0; i<updatableObjects.size();i++) {
			updatableObjects.get(i).update();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ticks = 30.0;
		double ns= 1000000000 / ticks;
		double delta=0;
		int updates=0;
		int frames=0;
		long timer=System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			if(delta>=1){
				updateState();
				render();
				frames++;
				updates++;
				delta--;
			}
			//to print out fps and updates
			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
//				System.out.println("FPS:"+frames+" Updates:"+updates);
//				frames=0;
//				updates=0;
//			System.out.println("\nTotal collidable:"+collidableObjects.size());
//			System.out.println("Total drawable:"+drawableObjects.size());
//			System.out.println("Total updatable:"+updatableObjects.size());
			}
		}
		stop();
	}
	
	private void removeFromScene(Updatable obj) {
		updatableObjects.remove(obj);
		drawableObjects.remove(obj);
		collidableObjects.remove(obj);
	}
	
	public GameCamera getCamera(int number) {
		if(number==1)
			return camera1;
		if(number==2)
			return camera2;
		return new GameCamera(); //if not 1 or 2 return empty camera
	}
}
