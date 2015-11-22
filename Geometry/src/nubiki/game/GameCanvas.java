package nubiki.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

//import com.sun.swing.internal.plaf.synth.resources.synth;

public class GameCanvas extends Canvas implements Runnable, KeyListener {
    private static final long serialVersionUID = 1L;
    
    private boolean isRunning;
    private Thread thread;
    
    private int width;
    private int height;
    private GameObject player,player2;
    
    private ArrayList<GameObject> gameObjects;
	public GameCanvas() {
		super();
		isRunning=false;
		width=800;
		height=600;
		gameObjects=new  ArrayList<GameObject>();
		setPreferredSize(new Dimension(width, height));
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addplayers();
	}
	
	public synchronized void start() {
		if(isRunning)
			return;
		isRunning=true;
		thread=new Thread(this);
		thread.start();
	}
	
	private void addplayers() {
		player=new Player();
		player2=new Player();
		player2.setPosX(player2.getPosX()+300);
		gameObjects.add(player);
		gameObjects.add(player2);
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
	public void paint(Graphics g) {
//		System.out.println("painting canvas...");
		super.paint(g);
		for(int i=0; i<gameObjects.size();i++)
			gameObjects.get(i).draw(g);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("Key pressed...");
		int code = e.getKeyCode();
		
		if (code==KeyEvent.VK_RIGHT){
			player.setSpeedX(5);
		}
		if (code==KeyEvent.VK_LEFT){
			player.setSpeedX(-5);
		}
		if (code==KeyEvent.VK_UP){
			player.setSpeedY(-5);
		}
		if (code==KeyEvent.VK_DOWN){
			player.setSpeedY(5);
		}
		
		if (code==KeyEvent.VK_D){
			player2.setSpeedX(5);
		}
		if (code==KeyEvent.VK_A){
			player2.setSpeedX(-5);
		}
		if (code==KeyEvent.VK_W){
			player2.setSpeedY(-5);
		}
		if (code==KeyEvent.VK_S){
			player2.setSpeedY(5);
		}
		
		if (code==KeyEvent.VK_Q){
			Projectile projectile=new Projectile();
			projectile.setSpeedX(15);
			gameObjects.add(projectile);
		}
//		repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code==KeyEvent.VK_RIGHT || code==KeyEvent.VK_LEFT) {
			player.setSpeedX(0);
		}
		if (code==KeyEvent.VK_UP || code==KeyEvent.VK_DOWN ){
			player.setSpeedY(0);
		}
		if (code==KeyEvent.VK_A || code==KeyEvent.VK_D) {
			player2.setSpeedX(0);
		}
		if (code==KeyEvent.VK_W|| code==KeyEvent.VK_S ){
			player2.setSpeedY(0);
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
		for(int i=0; i<gameObjects.size();i++) {
			if(gameObjects.get(i).isObsolete())
				gameObjects.remove(i);
		}
		
		//Handles objects movement
		for(int i=0; i<gameObjects.size();i++) {
//			System.out.println("gameobjects size:"+gameObjects.size());
			gameObjects.get(i).move();
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
				System.out.println("FPS:"+frames+" Updates:"+updates);
				frames=0;
				updates=0;
			}
		}
		stop();
	}
}
