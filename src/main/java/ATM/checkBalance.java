package ATM;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import dataBase.logIn;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class checkBalance extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel background, balance;
	JButton back;

	String uname;
	public checkBalance(String uname) {
		this.uname = uname;
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = config.buildSessionFactory();
		Session s = sf.openSession();
		logIn person = (logIn) s.get(logIn.class, uname);
		long blc = person.getDetails().getBalance();
		s.close();
		sf.close();

		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("./Icons/atm.jpg"));
		Image bg = img.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
		ImageIcon bg_img = new ImageIcon(bg);

		background = new JLabel(bg_img);
		setLayout(null);
		background.setBounds(0, 0, 900, 900);
		add(background);

		back = new JButton("BACK");
		back.setBounds(355, 490, 150, 30);
		back.addActionListener(this);
		background.add(back);

		balance = new JLabel("Your balance is RS: " + blc);
		balance.setForeground(Color.WHITE);
		balance.setBounds(170, 300, 400, 30);
		background.add(balance);
		
		setSize(900, 900);
		setLocation(300, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back) {
			new mainScreen(uname);
			this.dispose();
		}

	}

	public static void main(String[] args) {
		new checkBalance("s");
	}

}
