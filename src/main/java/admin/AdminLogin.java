package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminLogin extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel background, heading, userName, password;
	private JPanel panel;
	private JTextField userName_tf;
	private JPasswordField password_tf;
	private JButton signIn;

	public AdminLogin() {
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("./Icons./admin_mainScreen.jpg"));
		Image img = icon.getImage().getScaledInstance(1700, 900, Image.SCALE_DEFAULT);
		ImageIcon newIcon = new ImageIcon(img);
		setLayout(null);
		background = new JLabel(newIcon);
		background.setBounds(0, 0, 1200, 700);
		add(background);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(710, 40, 430, 550);
		panel.setBackground(new Color(115, 168, 161, 20));
		background.add(panel);

		heading = new JLabel("Sign In");
		heading.setFont(new Font("Raleway", Font.BOLD, 30));
		heading.setForeground(Color.WHITE);
		heading.setBounds(150, 10, 500, 150);
		panel.add(heading);

		userName = new JLabel("User Name");
		userName.setFont(new Font("Raleway", Font.BOLD, 17));
		userName.setForeground(Color.WHITE);
		userName.setBounds(50, 100, 400, 100);
		panel.add(userName);

		userName_tf = new JTextField();
		userName_tf.setBounds(50, 180, 350, 30);
		panel.add(userName_tf);

		password = new JLabel("Password");
		password.setFont(new Font("Raleway", Font.BOLD, 17));
		password.setForeground(Color.WHITE);
		password.setBounds(50, 220, 400, 100);
		panel.add(password);

		password_tf = new JPasswordField();
		password_tf.setBounds(50, 300, 350, 30);
		panel.add(password_tf);

		signIn = new JButton("Sign In");
		signIn.setBounds(120, 370, 170, 40);
		signIn.setBackground(new Color(115, 224, 171));
		signIn.setForeground(Color.WHITE);
		signIn.addActionListener(this);
		panel.add(signIn);

		setForeground(Color.WHITE);
		setTitle("Bank Management System");
		setSize(1200, 700);
		setLocation(50, 50);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == signIn) {
			String uname = userName_tf.getText();
			String pass = String.valueOf(password_tf.getPassword());

			if(uname.equals("sarthak") &&	pass.equals("sarthak")) {
				new AdminHome();
				this.dispose();
			}
		}
	}

	public static void main(String[] args) {
		new AdminLogin();
	}

}
