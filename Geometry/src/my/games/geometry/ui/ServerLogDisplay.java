package my.games.geometry.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class ServerLogDisplay implements Runnable {

	private List<JLabel> mLabelList;
	private List<JTextPane> mTextList;

	public ServerLogDisplay() {
		mLabelList = new ArrayList<JLabel>();
		mTextList = new ArrayList<JTextPane>();
	}

	@Override
	public void run() {
		JFrame frame = new JFrame("Server log");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setResizable(false);

		for (int i = 0; i < 5; i++) {
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
		mLabelList.get(0).setText("gameObjectsMap");
		mLabelList.get(1).setText("drawableObjectList");
		mLabelList.get(2).setText("updatableObjectList");
		mLabelList.get(3).setText("collidableObjectList");
		mLabelList.get(4).setText("shootersList");

		frame.setVisible(true);
		frame.setFocusable(true);
	}

	public List<JTextPane> getmTextList() {
		return mTextList;
	}
}
