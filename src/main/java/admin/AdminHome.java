package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class AdminHome extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton signout, createAccount, block_unblock, searchAccount, closeAccount, updateClientInfo;
	private JLabel userName;

	public AdminHome() {
		userName = new JLabel("Admin");
		userName.setBounds(100, 70, 550, 40);
		userName.setFont(new Font("Raleway", Font.BOLD, 27));
		add(userName);

		signout = new JButton("Sign Out");
		signout.setBounds(800, 30, 170, 40);
		signout.setFocusable(false);
		signout.setBackground(new Color(115, 224, 171));
		signout.setForeground(Color.WHITE);
		signout.addActionListener(this);
		add(signout);

		createAccount = new JButton("Create Account");
		createAccount.setBounds(100, 190, 170, 40);
		createAccount.setFocusable(false);
		createAccount.setBackground(new Color(115, 224, 171));
		createAccount.setForeground(Color.WHITE);
		createAccount.addActionListener(this);
		add(createAccount);

		block_unblock = new JButton("Block/Unblock Account");
		block_unblock.setBounds(390, 190, 170, 40);
		block_unblock.setFocusable(false);
		block_unblock.setBackground(new Color(115, 224, 171));
		block_unblock.setForeground(Color.WHITE);
		block_unblock.addActionListener(this);
		add(block_unblock);

		searchAccount = new JButton("Search Account");
		searchAccount.setBounds(680, 190, 170, 40);
		searchAccount.setFocusable(false);
		searchAccount.setBackground(new Color(115, 224, 171));
		searchAccount.setForeground(Color.WHITE);
		searchAccount.addActionListener(this);
		add(searchAccount);

		closeAccount = new JButton("Close Account");
		closeAccount.setBounds(100, 290, 170, 40);
		closeAccount.setFocusable(false);
		closeAccount.setBackground(new Color(115, 224, 171));
		closeAccount.setForeground(Color.WHITE);
		closeAccount.addActionListener(this);
		add(closeAccount);

		updateClientInfo = new JButton("Update Client Info");
		updateClientInfo.addActionListener(this);
		updateClientInfo.setFocusable(false);
		updateClientInfo.setBounds(390, 290, 170, 40);
		updateClientInfo.setBackground(new Color(115, 224, 171));
		updateClientInfo.setForeground(Color.WHITE);
		add(updateClientInfo);

		getContentPane().setBackground(Color.WHITE);
		setTitle("Admin");
		setSize(1000, 600);
		setLocation(100, 100);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createAccount) {
			new AdminCreateAccount();
			this.dispose();
		} else if (e.getSource() == block_unblock) {
			new BlockUnblock();
			this.dispose();
		} else if (e.getSource() == searchAccount) {
			new SearchAccount();
			this.dispose();
		} else if (e.getSource() == closeAccount) {
			new CloseAccount();
			this.dispose();
		} else if (e.getSource() == updateClientInfo) {
			new UserSerchToUpdate();
			this.dispose();
		}else if(e.getSource()==signout) {
			new AdminLogin();
			this.dispose();
		}

	}

	public static void main(String[] args) {
		new AdminHome();
	}

}
