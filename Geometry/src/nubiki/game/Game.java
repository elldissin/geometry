package nubiki.game;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;

public class Game {
    private static final long serialVersionUID = 1L;
    public static void main(String[] args) 
    {
    	GameManager game=new GameManager();
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
