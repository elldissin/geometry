package my.games.geometry.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class LoginWindow extends JPanel implements ActionListener {

	private List<JLabel> mLabelList;
	private List<JTextPane> mTextList;
	private volatile boolean logButtClicked;
	JButton loginButt = new JButton();

	public LoginWindow() {

		mLabelList = new ArrayList<JLabel>();
		mTextList = new ArrayList<JTextPane>();
		logButtClicked = false;
		// for (int i = 0; i < 2; i++) {
		// JTextPane anotherTextField = new JTextPane();
		// anotherTextField.setBounds(0, (i * 50) + 25, 200, 20);
		// anotherTextField.setText("0");
		// mTextList.add(i, anotherTextField);
		// this.add(anotherTextField);
		//
		// JLabel anotherLabel = new JLabel();
		// anotherLabel.setBounds(0, (i * 50), 200, 20);
		// mLabelList.add(i, anotherLabel);
		// this.add(anotherLabel);
		// }

		// loginButt.setBounds(215, 205, 70, 30);
		// loginButt.addActionListener(this);
		// this.add(loginButt);

		this.setBounds(0, 0, 500, 500);
		this.setLayout(null);

		// mLabelList.get(0).setText("Login:");
		// mLabelList.get(1).setText("Password");

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
//
// @Override
// public void run() {
//
// }
