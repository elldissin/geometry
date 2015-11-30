package nubiki.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

//import com.sun.swing.internal.plaf.synth.resources.synth;

public class GameCanvas extends JPanel implements Runnable, KeyListener {
    private static final long serialVersionUID = 1L;
    
    private boolean isRunning;
    private Thread thread;
    
    private int width;
    private int height;
    private NormalPlayer player1,player2;
    
    private static ArrayList<NormalPlayer> players;
    private static ArrayList<NormalProjectile> projectiles;
	public GameCanvas() {
		super();
		isRunning=false;
		width=800;
		height=600;
		players=new  ArrayList<NormalPlayer>();
		projectiles= new ArrayList<NormalProjectile>();
		setPreferredSize(new Dimension(width, height));
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
		player1=new Player();
		player2=new Player();
		players.add(player1);
		players.add(player2);
	}

	protected static void addProjectile(NormalProjectile obj) {
		if(obj!=null)
			projectiles.add(obj);
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
		for(int i=0; i<players.size();i++)
			players.get(i).draw(g);
		for(int i=0; i<projectiles.size();i++)
			projectiles.get(i).draw(g);
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
		
//		if (code==KeyEvent.VK_RIGHT){
//			player1.setSpeedX(5);
//		}
//		if (code==KeyEvent.VK_LEFT){
//			player1.setSpeedX(-5);
//		}
//		if (code==KeyEvent.VK_UP){
//			player1.setSpeedY(-5);
//		}
//		if (code==KeyEvent.VK_DOWN){
//			player1.setSpeedY(5);
//		}
//		
//		if (code==KeyEvent.VK_D){
//			player2.setSpeedX(5);
//		}
//		if (code==KeyEvent.VK_A){
//			player2.setSpeedX(-5);
//		}
//		if (code==KeyEvent.VK_W){
//			player2.setSpeedY(-5);
//		}
//		if (code==KeyEvent.VK_S){
//			player2.setSpeedY(5);
//		}
		
		if (code==KeyEvent.VK_Q) { //casting to be fixed
			Player shootingPlayer = (Player)(player2);
			if(shootingPlayer!=null)
				shootingPlayer.shoot();
			//			Projectile projectile=new Projectile();
			//			projectile.setSpeedX(15);
			//			gameObjects.add(projectile);
		}
		
		if (code==KeyEvent.VK_CONTROL) { //casting to be fixed
			Player shootingPlayer = (Player)(player1);
			if(shootingPlayer!=null)
				shootingPlayer.shoot();
				//			Projectile projectile=new Projectile();
				//			projectile.setSpeedX(15);
				//			gameObjects.add(projectile);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		//this needs to be fixed, stopping even when releasing the other key
//		if (code==KeyEvent.VK_RIGHT || code==KeyEvent.VK_LEFT) {
//			player1.setSpeedX(0);
//		}
//		if (code==KeyEvent.VK_UP || code==KeyEvent.VK_DOWN ){
//			player1.setSpeedY(0);
//		}
//		if (code==KeyEvent.VK_A || code==KeyEvent.VK_D) {
//			player2.setSpeedX(0);
//		}
//		if (code==KeyEvent.VK_W|| code==KeyEvent.VK_S ){
//			player2.setSpeedY(0);
//		}
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
		for(int i=0; i<players.size();i++) {
			if(players.get(i).isDestroyed())
				players.remove(i);
		}
		
		for(int i=0; i<projectiles.size();i++) {
			if(projectiles.get(i).isDestroyed())
				projectiles.remove(i);
		}
	
		//Handles collisions BUG collision with own projectile; can shoot after death
		for(int i=0; i<players.size();i++)
			for(int j=0; j<projectiles.size();j++) {
				if(((GameObject) players.get(i)).isColliding((GameObject) projectiles.get(j))) {
					System.out.println("Collision happened");
					players.get(i).destroy(); //marks object for deletion
					projectiles.get(j).destroy(); //marks object for deletion
				}
			}

		//Handles objects movement
		for(int i=0; i<players.size();i++) {
			players.get(i).updateState();
		}
		for(int i=0; i<projectiles.size();i++) {
			projectiles.get(i).updateState();
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
