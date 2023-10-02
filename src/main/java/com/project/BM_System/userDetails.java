package com.project.BM_System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dataBase.Accounts;
import dataBase.logIn;

public class userDetails extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String uname;
	public JButton back;
	private JLabel img,account_no, Aadhar, name, mobile, State, City, AccountType, FathersName, MothersName, PanCard, Email,
			Pincode, gender;

	public userDetails(String uname) {
		this.uname=uname;
		Accounts userDetails=unameTodetails(uname);
		// name
		name = new JLabel(userDetails.getName());
		name.setFont(new Font("Raleway", Font.BOLD, 60));
		name.setBounds(100, 40, 500, 70);
		add(name);
		
		// img 
		img= new JLabel();
        img.setBounds(730, 10, 170, 180);
        if(userDetails.getImage()==null) {
        	setDefaultImg();
        }else {
        	// fetching the image from DB and then display it in the table
        	try {
				BufferedImage bi = ImageIO.read(new ByteArrayInputStream(userDetails.getImage()));
				Image image = bi.getScaledInstance(170, 180, Image.SCALE_DEFAULT);
				ImageIcon imgi=new ImageIcon(image);
				img.setIcon(imgi);
			} catch (IOException e) {
				setDefaultImg();
			}
        }
        add(img);

        // adding account number 
		account_no = new JLabel(String.valueOf("Acoount no."+userDetails.getAccountNo()));
		account_no.setFont(new Font("Raleway", Font.BOLD, 15));
		account_no.setBounds(100, 160, 500, 150);
		add(account_no);

		// Gender
		gender = new JLabel("Gender : " + userDetails.getGender());
		gender.setFont(new Font("Raleway", Font.BOLD, 15));
		gender.setBounds(400, 160, 500, 150);
		add(gender);

		// Email
		Email = new JLabel("Email: " + userDetails.getEmail());
		Email.setFont(new Font("Raleway", Font.BOLD, 15));
		Email.setBounds(700, 160, 500, 150);
		add(Email);
		// Mother's Name
		MothersName = new JLabel("Mother's Name: " + userDetails.getMothersName());
		MothersName.setFont(new Font("Raleway", Font.BOLD, 15));
		MothersName.setBounds(100, 240, 500, 150);
		add(MothersName);
		// Father's Name
		FathersName = new JLabel("Father'sName: " + userDetails.getFathersName());
		FathersName.setFont(new Font("Raleway", Font.BOLD, 15));
		FathersName.setBounds(400, 240, 500, 150);
		add(FathersName);

//		Pincode
		Pincode = new JLabel("Pincode: " + userDetails.getPincode());
		Pincode.setFont(new Font("Raleway", Font.BOLD, 15));
		Pincode.setBounds(700, 240, 500, 150);
		add(Pincode);
		
//		mobile
		mobile = new JLabel("Mobile: " + userDetails.getMobile());
		mobile.setFont(new Font("Raleway", Font.BOLD, 15));
		mobile.setBounds(100, 320, 500, 150);
		add(mobile);

//		city
		City = new JLabel("City: " + userDetails.getCity());
		City.setFont(new Font("Raleway", Font.BOLD, 15));
		City.setBounds(400, 320, 500, 150);
		add(City);

//		state
		State = new JLabel("State: " + userDetails.getState());
		State.setFont(new Font("Raleway", Font.BOLD, 15));
		State.setBounds(700, 320, 500, 150);
		add(State);

//		pancard
		PanCard = new JLabel("Pancard: " + userDetails.getPanCard());
		PanCard.setFont(new Font("Raleway", Font.BOLD, 15));
		PanCard.setBounds(100, 400, 500, 150);
		add(PanCard);

//		Aadhar card
		Aadhar = new JLabel("Aadhar: " + userDetails.getAadhar());
		Aadhar.setFont(new Font("Raleway", Font.BOLD, 15));
		Aadhar.setBounds(400, 400, 500, 150);
		add(Aadhar);

//		Account type
		AccountType = new JLabel("Account Type: " + userDetails.getAccountType());
		AccountType.setFont(new Font("Raleway", Font.BOLD, 15));
		AccountType.setBounds(700, 400, 500, 150);
		add(AccountType);

//		Back button
		back = new JButton("Back");
		back.addActionListener(this);
		back.setFocusable(false);
		back.setBounds(100, 550, 150, 40);
		back.setBackground(new Color(115, 224, 171));
		back.setForeground(Color.WHITE);

		add(back);

		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setTitle("Bank Management System");
		setSize(1200, 700);
		setLocation(50, 50);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			new userLogin(uname).setVisible(true);
			this.dispose();
		}

	}
	
	public static void main(String[] args) {
		new userDetails("sarthak_13");
	}
	
	private Accounts unameTodetails(String uname) {
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = config.buildSessionFactory();
		Session s = sf.openSession();
		// fetching details
		logIn user = null;
		user=(logIn)s.get(logIn.class, uname);
		s.close();
		sf.close();
		return user.getDetails();
	}
	
	private void setDefaultImg() {
		ImageIcon imgicon = new ImageIcon(ClassLoader.getSystemResource("./Icons./default_Img.jpg"));
		Image image = imgicon.getImage().getScaledInstance(170, 180, Image.SCALE_DEFAULT);
		ImageIcon imgicon1=new ImageIcon(image);
		img.setIcon(imgicon1);
	}

}
