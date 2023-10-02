package ATM;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dataBase.UserTransection;
import dataBase.logIn;

public class cashWithdrawal extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String uname;
	JTextField amount;
	JButton withdraw, back;

	public cashWithdrawal(String uname) {
		this.uname = uname;
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("./Icons/atm.jpg"));
		Image bg = img.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
		ImageIcon bg_img = new ImageIcon(bg);

		JLabel background = new JLabel(bg_img);
		background.setBounds(0, 0, 900, 900);
		add(background);

		JLabel text = new JLabel("Enter the amount you want to Withdraw");
		text.setBounds(190, 275, 400, 20);
		text.setFont(new Font("System", Font.BOLD, 16));
		text.setForeground(Color.white);
		background.add(text);

		amount = new JTextField();
		amount.setBounds(200, 325, 270, 25);
		amount.setFont(new Font("Raleway", Font.BOLD, 22));
		background.add(amount);

//        todo:buttons

		withdraw = new JButton("withdraw");
		withdraw.setBounds(355, 450, 150, 30);
		withdraw.addActionListener(this);
		background.add(withdraw);

		back = new JButton("Back");
		back.setBounds(355, 485, 150, 30);
		back.addActionListener(this);
		background.add(back);

		// todo:dimension
		setSize(900, 900);
		setLocation(300, 0);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == withdraw) {
			long amt = Long.parseLong(amount.getText());
			Configuration congif = new Configuration().configure("hibernate.cfg.xml");
			SessionFactory sf = congif.buildSessionFactory();
			Session s = sf.openSession();
			logIn person = (logIn) s.get(logIn.class, uname);
			if (person.getDetails().getBalance() > amt) {
				
				// saving transaction 
				UserTransection utx = new UserTransection();
				utx.setAccount(person.getDetails());
				utx.setAmount(amt);
				utx.setDate(new Date());
				utx.setReciever_Account("_");
				utx.setSender_Account("_");
				utx.setType("Withdrawn");
				person.getDetails().getTransection().add(utx);
				
				person.getDetails().setBalance(person.getDetails().getBalance()-amt);
				Transaction tx = s.beginTransaction();
				s.update(person);
				s.save(utx);
				tx.commit();
				s.close();
				sf.close();
				JOptionPane.showMessageDialog(null, "Successfully withdrawal");
				this.dispose();
				new mainScreen(uname);
			} else {
				JOptionPane.showMessageDialog(null, "Your dont have enough balance");
			}
		} else {
			this.dispose();
			new mainScreen(uname);
		}
	}
}
