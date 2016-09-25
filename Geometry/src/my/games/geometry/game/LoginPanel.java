package my.games.geometry.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class LoginPanel extends JPanel {
	private List<JLabel> mLabelList;
	private List<JTextPane> mTextList;
	private volatile boolean logButtClicked;
	JButton loginButt = new JButton();

	private Client client;
	private int viewWidth;
	private int viewHeight;
	private ClientWindow clientwindow;

	LoginPanel(Client client, ClientWindow clientwindow) {
		this.clientwindow = clientwindow;
		this.client = client;
		mLabelList = new ArrayList<JLabel>();
		mTextList = new ArrayList<JTextPane>();
		logButtClicked = false;

		this.setLayout(new BorderLayout());
		for (int i = 0; i < 2; i++) {
			JTextPane anotherTextField = new JTextPane();
			// anotherTextField.setBounds(0, (i * 50) + 25, 200, 20);
			anotherTextField.setText("0");
			anotherTextField.setPreferredSize(new Dimension(200, 20));
			mTextList.add(i, anotherTextField);
			this.add(anotherTextField, BorderLayout.PAGE_START);

			JLabel anotherLabel = new JLabel();
			// anotherLabel.setBounds(0, (i * 50), 200, 20);
			mLabelList.add(i, anotherLabel);
			this.add(anotherLabel, BorderLayout.PAGE_END);
		}

		mLabelList.get(0).setText("Login:");
		mLabelList.get(1).setText("Password");

		// LoginButtonListener loginButtonListener = new
		// LoginButtonListener(client, clientwindow);
		// loginButt.setText("Log in");
		// loginButt.setBounds(215, 205, 70, 30);
		// loginButt.addActionListener(loginButtonListener);
		// this.add(loginButt);

		viewWidth = 800;
		viewHeight = 600;
		setPreferredSize(new Dimension(viewWidth, viewHeight));
		this.setLayout(null);

	}

	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
