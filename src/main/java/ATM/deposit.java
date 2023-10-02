package ATM;

import javax.swing.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dataBase.UserTransection;
import dataBase.logIn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class deposit extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String uname;
	JTextField amount;
	JButton deposit, back;

	public deposit(String uname) {
		this.uname = uname;
		ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("./Icons/atm.jpg"));
		Image bg = img.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
		ImageIcon bg_img = new ImageIcon(bg);

		JLabel background = new JLabel(bg_img);
		background.setBounds(0, 0, 900, 900);
		add(background);

		JLabel text = new JLabel("Enter the amount you want to deposit");
		text.setBounds(190, 275, 400, 20);
		text.setFont(new Font("System", Font.BOLD, 16));
		text.setForeground(Color.white);
		background.add(text);

		amount = new JTextField();
		amount.setBounds(200, 325, 270, 25);
		amount.setFont(new Font("Raleway", Font.BOLD, 22));
		background.add(amount);

//        todo:buttons

		deposit = new JButton("Deposit");
		deposit.setBounds(355, 450, 150, 30);
		deposit.addActionListener(this);
		background.add(deposit);

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

	public static void main(String[] args) {
		new deposit("s");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deposit) {
			long amt = Long.parseLong(amount.getText());
			if (amt > 0) {
				// configuration
				Configuration congif = new Configuration().configure("hibernate.cfg.xml");
				SessionFactory sf = congif.buildSessionFactory();
				Session s = sf.openSession();
				
				// finding user details and creating the record of transaction
				logIn person = (logIn) s.get(logIn.class, uname);
				person.getDetails().setBalance(person.getDetails().getBalance()+amt);
				// transaction
				UserTransection utx = new UserTransection();
				utx.setAccount(person.getDetails());
				utx.setAmount(amt);
				utx.setDate(new Date());
				utx.setReciever_Account("Self");
				utx.setSender_Account("Through ATM");
				utx.setType("Deposit");
				person.getDetails().getTransection().add(utx);
				
				// commit the change
				Transaction tx = s.beginTransaction();
				s.update(person);
				s.save(utx);
				tx.commit();
				s.close();
				sf.close();
				JOptionPane.showMessageDialog(null, "Successfully deposited");
				this.dispose();
				new mainScreen(uname);
			} else {
				JOptionPane.showMessageDialog(null, "Please enter the amount");
			}
		} else {
			this.dispose();
			new mainScreen(uname);
		}
	}

}
