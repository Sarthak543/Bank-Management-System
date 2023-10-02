package com.project.BM_System;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dataBase.Accounts;
import dataBase.UserTransection;
import dataBase.logIn;

public class onlineTransferMoeny extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel heading, background, accountNo, amount;
	private JTextField accountNo_tf, amount_tf;
	private JButton done;
	private String uname;

	public onlineTransferMoeny(String uname) {
		this.uname = uname;
		heading = new JLabel("Online Money Transfer");
		heading.setFont(new Font("Raleway", Font.BOLD, 40));
		heading.setBounds(100, 40, 500, 70);
		add(heading);

		background = new JLabel();
		Border baseline = BorderFactory.createLineBorder(Color.BLACK);
		background.setBorder(baseline);
		background.setFont(new Font("Raleway", Font.BOLD, 40));
		background.setBackground(Color.black);
		background.setBounds(100, 170, 800, 350);
		add(background);

		accountNo = new JLabel("<html>Account<br/>number</html>");
		accountNo.setFont(new Font("Raleway", Font.BOLD, 14));
		accountNo.setBounds(100, 0, 300, 150);
		background.add(accountNo);
		accountNo_tf = new JTextField();
		accountNo_tf.setBounds(200, 60, 350, 25);
		background.add(accountNo_tf);

		amount = new JLabel("Amount");
		amount.setFont(new Font("Raleway", Font.BOLD, 14));
		amount.setBounds(100, 80, 300, 150);
		background.add(amount);
		amount_tf = new JTextField();
		amount_tf.setBounds(200, 140, 350, 25);
		background.add(amount_tf);

		done = new JButton("OK");
		done.setBounds(200, 400, 170, 40);
		done.setBackground(new Color(115, 224, 171));
		done.setForeground(Color.WHITE);
		done.addActionListener(this);
		add(done);

		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setTitle("Online Money Transfer");
		setSize(1000, 600);
		setLocation(50, 50);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == done) {
			if (accountNo_tf.getText().equals("")  ||  amount_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter account Number");
			} else {
				long accountno = Long.parseLong(accountNo_tf.getText());
				long amount = Long.parseLong(amount_tf.getText());

				Configuration config = new Configuration().configure("hibernate.cfg.xml");
				SessionFactory sf = config.buildSessionFactory();
				Session s = sf.openSession();
				logIn sender = (logIn) s.get(logIn.class, uname);
				Accounts receiver = (Accounts) s.get(Accounts.class, accountno);
				
				if (sender.getDetails().getBalance() < amount) {
					JOptionPane.showMessageDialog(null, "Your dont have enough balance to transfer");
				} else {
					Transaction tx = s.beginTransaction();
					// saving transaction
					// sender
					UserTransection utx = new UserTransection();
					utx.setAccount(sender.getDetails());
					utx.setAmount(amount);
					utx.setDate(new Date());
					utx.setReciever_Account(receiver.getName());
					utx.setSender_Account("You");
					utx.setType("Online Money Transfer");
					sender.getDetails().getTransection().add(utx);
					sender.getDetails().setBalance(sender.getDetails().getBalance()-amount);

					UserTransection utx1 = new UserTransection();
					utx1.setAccount(receiver);
					utx1.setAmount(amount);
					utx1.setDate(new Date());
					utx1.setReciever_Account("You");
					utx1.setSender_Account(sender.getDetails().getName());
					utx1.setType("Online Money Transfer");
					receiver.getTransection().add(utx);
					receiver.setBalance(receiver.getBalance()+amount);
					s.update(sender);
					s.save(utx);
					s.save(utx1);
					s.update(receiver);
					tx.commit();
					JOptionPane.showMessageDialog(null, "Money successfully transfered");
					this.dispose();
					new userLogin(uname);
				}
			}
		}

	}
}
