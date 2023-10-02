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
public class fastCash extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton amt2000, amt5000, amt10000, amt15000, amt20000,amt25000,back;
    String uname;
    
    public fastCash(String uname) {
    	this.uname = uname;
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("./Icons/atm.jpg"));
        Image bg = img.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon bg_img = new ImageIcon(bg);

        JLabel background = new JLabel(bg_img);
        background.setBounds(0, 0, 900, 900);
        add(background);

        JLabel text = new JLabel("Select your Transection");
        text.setBounds(240, 300, 700, 35);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setForeground(Color.white);
        background.add(text);

        amt2000 = new JButton("2000");
        amt2000.setBounds(170, 415, 150, 30);
        amt2000.addActionListener(this);
        background.add(amt2000);

        amt5000 = new JButton("5000");
        amt5000.setBounds(345, 415, 150, 30);
        amt5000.addActionListener(this);
        background.add(amt5000);

        amt10000 = new JButton("10000");
        amt10000.setBounds(170, 450, 150, 30);
        amt10000.addActionListener(this);
        background.add(amt10000);

        amt15000 = new JButton("15000");
        amt15000.setBounds(345, 450, 150, 30);
        amt15000.addActionListener(this);
        background.add(amt15000);

        amt20000 = new JButton("20000");
        amt20000.setBounds(170, 485, 150, 30);
        amt20000.addActionListener(this);
        background.add(amt20000);

        amt25000 = new JButton("25000");
        amt25000.addActionListener(this);
        amt25000.setBounds(345, 485, 150, 30);
        background.add(amt25000);

        back = new JButton("Back");
        back.setBounds(345, 520, 150, 30);
        back.addActionListener(this);
        background.add(back);

//        todo: dimension
        setSize(900, 900);
        setLayout(null);
        setLocation(300, 0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
        	new mainScreen(uname);
        	this.dispose();
        }else {
        	long amt=Long.parseLong(((JButton)e.getSource()).getText());
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
        }
            
    }
}
