package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dataBase.Accounts;
import dataBase.STATUS;
import dataBase.logIn;
import modifiedClasses.TextField;

public class CloseAccount extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JLabel heading;
	private TextField account_tf;
	private JButton search;

	public CloseAccount() {

		heading = new JLabel("Seach Account");
		heading.setFont(new Font("Raleway", Font.BOLD, 40));
		heading.setBounds(80, 40, 500, 70);
		add(heading);
		account_tf = new TextField("Account Number", 80, 120, 500, 30, Color.BLACK);
		add(account_tf);
		search = new JButton("Search");
		search.addActionListener(this);
		search.setBounds(80, 160, 150, 40);
		search.setBackground(new Color(115, 224, 171));
		search.setForeground(Color.WHITE);
		add(search);

		getContentPane().setBackground(Color.WHITE);
		JRadioButton btn = new JRadioButton(".");
		btn.setBounds(10000, 0, 3, 3);
		add(btn);
		setTitle("Admin");
		setSize(700, 400);
		setLocation(100, 100);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==search) {
			logIn userCredentials=accToLogIn(account_tf.getText());
			Configuration cnf = new Configuration().configure("hibernate.cfg.xml");
			SessionFactory sf = cnf.buildSessionFactory();
			Session s = sf.openSession();
			Transaction tx = s.beginTransaction();
			if(userCredentials.getAccountStatus()==STATUS.OPEN) {
				int choice=JOptionPane.showConfirmDialog(null, "User account is open. Want to close it?");
				if(choice==0) {
					//yes
					userCredentials.setAccountStatus(STATUS.CLOSE);
				}
			}else if(userCredentials.getAccountStatus()==STATUS.CLOSE) {
				int choice=JOptionPane.showConfirmDialog(null, "User account is Closed. Want to reOpen it?");
				if(choice==0) {
					//yes
					userCredentials.setAccountStatus(STATUS.OPEN);
				}
			}
			s.update(userCredentials);
			tx.commit();
			s.close();
			sf.close();
			this.dispose();
			new AdminHome();
		}
		
	}
	
	private logIn accToLogIn(String acc) {
		logIn person= new logIn();
		Configuration cnf = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cnf.buildSessionFactory();
		Session s = sf.openSession();
		long id=Long.parseLong(acc);
		Accounts person_acc=(Accounts)s.get(Accounts.class, id);
		person=person_acc.getCredentials();
		s.close();
		sf.close();
		return person;
	}

}
