package my.games.geometry.game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Application {
    private static final long serialVersionUID = 1L;
    public static void main(String[] args) 
    {
    	Client game=new Client();
    	JFrame frame = new JFrame("Geometry game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.add(game.getCamera(1), BorderLayout.LINE_START);
        frame.add(game.getCamera(2), BorderLayout.LINE_END);
        frame.setVisible(true);
        frame.setFocusable(true); //important call to allow listening to keys
        frame.addKeyListener(game.getController());
        game.start();
    }
}
