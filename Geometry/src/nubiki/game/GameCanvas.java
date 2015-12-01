package nubiki.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

//import com.sun.swing.internal.plaf.synth.resources.synth;

public class GameCanvas extends JPanel implements Runnable, KeyListener {
    private static final long serialVersionUID = 1L;
    
    private boolean isRunning;
    private Thread thread;
    
    private final int WIDTH=800;
    private final int HEIGHT=600;
    private Player player1,player2;
    private static List<GameObject> updatableObjects;
    private static List<Drawable> drawableObjects;
	public GameCanvas() {
		super();
		isRunning=false;
		updatableObjects=new ArrayList<GameObject>();
		drawableObjects=new ArrayList<Drawable>();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
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
		updatableObjects.add(player1);
		updatableObjects.add(player2);
		drawableObjects.add(player1);
		drawableObjects.add(player2);
	}

	protected static void addProjectile(Projectile obj) {
		if(obj!=null) {
			updatableObjects.add(obj);
			drawableObjects.add(obj);
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
	public void paintComponent(Graphics g) {
//		System.out.println("painting canvas...");
		super.paintComponent(g);
		for(int i=0; i<updatableObjects.size();i++)
			drawableObjects.get(i).draw(g);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("Key pressed...");
		int code = e.getKeyCode();
		if (code==KeyEvent.VK_UP){
			player1.setMoving();
		}
		if (code==KeyEvent.VK_RIGHT){
			player1.setTurningCW();
		}
		if (code==KeyEvent.VK_LEFT){
			player1.setTurningCCW();
		}
		
		if (code==KeyEvent.VK_W){
			player2.setMoving();
		}
		if (code==KeyEvent.VK_D){
			player2.setTurningCW();
		}
		if (code==KeyEvent.VK_A){
			player2.setTurningCCW();
		}
		
		if (code==KeyEvent.VK_Q) { //casting to be fixed
			player2.shoot();
		}
		
		if (code==KeyEvent.VK_CONTROL) { //casting to be fixed
			player1.shoot();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		//this needs to be fixed, stopping even when releasing the other key
		if (code==KeyEvent.VK_UP){
			player1.setStopped();;
		}
		if (code==KeyEvent.VK_LEFT){
			player1.setNotTurning();
		}
		if (code==KeyEvent.VK_RIGHT){
			player1.setNotTurning();
		}
		
		if (code==KeyEvent.VK_W){
			player2.setStopped();
		}
		if (code==KeyEvent.VK_A){
			player2.setNotTurning();
		}
		if (code==KeyEvent.VK_D){
			player2.setNotTurning();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	private void render() {
		repaint();
	}
	
	private void updateState() {
		//Handles destruction of obsolete objects
		for(int i=0; i<updatableObjects.size();i++) {
			if(updatableObjects.get(i).isDestroyed()) {
				updatableObjects.remove(i);
				drawableObjects.remove(i); //THE TWO LISTS MAY BE DIFFERENT ...?
			}
		}
		
		//Handles collisions
		for(int i=0; i<updatableObjects.size();i++)
			for(int j=0; j<updatableObjects.size();j++) {
				if(updatableObjects.get(i).isColliding(updatableObjects.get(j)) && i!=j) {
					System.out.println("Collision happened");
					updatableObjects.get(i).destroy(); //marks object for deletion
					updatableObjects.get(j).destroy(); //marks object for deletion
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
//			if(System.currentTimeMillis()-timer>1000) {
//				timer+=1000;
//				System.out.println("FPS:"+frames+" Updates:"+updates);
//				frames=0;
//				updates=0;
//			}
		}
		stop();
	}
}
