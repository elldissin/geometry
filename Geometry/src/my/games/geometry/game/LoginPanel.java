package my.games.geometry.game;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class LoginPanel extends JPanel {
	private List<JLabel> mLabelList;
	private List<JTextPane> mTextList;
	private volatile boolean logButtClicked;

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

		this.setLayout(new GridLayout(3, 2, 0, 5));

		JLabel anotherLabel = new JLabel();
		anotherLabel.setText("login");
		anotherLabel.setPreferredSize(new Dimension(70, 30));
		this.add(anotherLabel);
		JTextField loginField = new JTextField();
		loginField.setPreferredSize(new Dimension(70, 30));
		this.add(loginField);

		JLabel anotherLabel1 = new JLabel();
		anotherLabel1.setText("Password");
		anotherLabel1.setPreferredSize(new Dimension(70, 30));
		this.add(anotherLabel1);
		JTextField passwordField = new JTextField();
		passwordField.setPreferredSize(new Dimension(70, 30));
		this.add(passwordField);

		JButton loginButt = new JButton();
		LoginButtonListener loginButtonListener = new LoginButtonListener(client, clientwindow);
		loginButt.setText("Log in");
		loginButt.addActionListener(loginButtonListener);
		loginButt.setPreferredSize(new Dimension(30, 30));
		this.add(loginButt);

	}

	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
