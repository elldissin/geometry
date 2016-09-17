package my.games.geometry.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class LoginWindow implements Runnable, ActionListener {
	private List<JLabel> mLabelList;
	private List<JTextPane> mTextList;
	private volatile boolean logButtClicked;

	public LoginWindow() {
		mLabelList = new ArrayList<JLabel>();
		mTextList = new ArrayList<JTextPane>();
		logButtClicked = false;
	}

	@Override
	public void run() {
		JFrame frame = new JFrame("Login to geometria online");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setResizable(false);

		for (int i = 0; i < 2; i++) {
			JTextPane anotherTextField = new JTextPane();
			anotherTextField.setBounds(0, (i * 50) + 25, 200, 20);
			anotherTextField.setText("0");
			mTextList.add(i, anotherTextField);
			frame.add(anotherTextField);

			JLabel anotherLabel = new JLabel();
			anotherLabel.setBounds(0, (i * 50), 200, 20);
			mLabelList.add(i, anotherLabel);
			frame.add(anotherLabel);
		}
		JPanel jPanel = new JPanel();
		jPanel.setBounds(250, 100, 70, 20);
		frame.add(jPanel);
		JButton loginButt = new JButton("Log in");
		loginButt.addActionListener(this);
		jPanel.add(loginButt);

		mLabelList.get(0).setText("Login:");
		mLabelList.get(1).setText("Password");

		frame.setVisible(true);
		frame.setFocusable(true);

	}

	public List<JTextPane> getmTextList() {
		return mTextList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		logButtClicked = true;
		System.out.println("logButtClicked");
	}

	public boolean isLogButtClicked() {
		return logButtClicked;
	}

}
