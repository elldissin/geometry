package nubiki.game;

import javax.swing.JFrame;

public class Game {
    private static final long serialVersionUID = 1L;
    public static void main(String[] args) 
    {
    	GameCanvas game=new GameCanvas();
    	JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(true);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
