package com.project.BM_System;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dataBase.Accounts;
import dataBase.STATUS;
import dataBase.logIn;
import modifiedClasses.TextField;

public class credentials extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel heading;
	public Accounts applicant;
	public TextField name, password, repassword;
	public JRadioButton btn;
	public JButton done;

	public credentials(Accounts applicant) {
		this.applicant = applicant;
		btn = new JRadioButton();
		btn.setBounds(600, 50, 500, 500);
		add(btn);
		heading = new JLabel("Setting Up the Credentials");
		heading.setFont(new Font("Raleway", Font.BOLD, 27));
		heading.setBounds(100, 10, 450, 40);
		add(heading);

		name = new TextField("New User Name*", 130, 100, 290, 28, Color.black);
		name.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		add(name);

		password = new TextField("New Password*", 130, 150, 290, 28, Color.black);
		password.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		add(password);

		repassword = new TextField("Re-Enter Password*", 130, 200, 290, 28, Color.black);
		repassword.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		add(repassword);

		done = new JButton("Sign Up");
		done.setBounds(150, 300, 240, 30);
		done.setBackground(new Color(115, 224, 171));
		done.setForeground(Color.WHITE);
		done.addActionListener(this);
		add(done);

		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		setSize(550, 500);
		setLocation(50, 50);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == done) {
			String un = name.getText();
			String ps = password.getText();
			String rps = repassword.getText();

			if ((un.length() == 0 || ps.length() == 0 || rps.length() == 0) && (ps.length() == rps.length())) {
				JOptionPane.showMessageDialog(null, "Please fill all the field");
			} else {
				logIn c = new logIn();
				c.setDetails(this.applicant);
				this.applicant.setCredentials(c);
				c.setAccountStatus(STATUS.OPEN);
				c.setUserName(un);
				c.setPassword(ps);
				try {
					Configuration cnf = new Configuration().configure("hibernate.cfg.xml");
					SessionFactory sf = cnf.buildSessionFactory();
					Session s = sf.openSession();
					org.hibernate.Transaction tx = s.beginTransaction();
					s.save(c);
					s.save(applicant);
					tx.commit();
					s.close();
					JOptionPane.showMessageDialog(null,
							"Account has been created. You can log in to your profile through these credentials");
					this.dispose();
					new App().setVisible(true);
				} catch (HibernateException he) {
					he.printStackTrace();
				}
			}
		}

	}
}
