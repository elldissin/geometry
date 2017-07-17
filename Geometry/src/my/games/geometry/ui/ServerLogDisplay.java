package my.games.geometry.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ServerLogDisplay implements Runnable {

	private List<JLabel> labelsList;

	public ServerLogDisplay() {
		labelsList = new ArrayList<JLabel>();
	}

	@Override
	public void run() {
		JFrame frame = new JFrame("Server log");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setResizable(false);

		for (int i = 0; i < 5; i++) {
			JLabel label = new JLabel();
			label.setBounds(0, (i * 50), 200, 20);
			labelsList.add(i, label);
			frame.add(label);
		}
		frame.setVisible(true);
		frame.setFocusable(true);
	}

	public void logTextInField(String text, int fieldNumber) {
		labelsList.get(fieldNumber).setText(text);
	}
}