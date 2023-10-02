package com.project.BM_System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDateChooser;
import dataBase.Accounts;
import modifiedClasses.TextField;

public class NewApplicant extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String path;
	public JCheckBox confirm;
	public JDateChooser date_chooser;
	public JLabel scheme, gender,dob,heading,img;
	public JButton submit,upload;
	public JRadioButton saving, current, pension, male, female;
	public TextField name, fname, mname, mobile, pan, aadhar, emailid, state, city, pincode;

	public NewApplicant(){
		// heading
		heading=new JLabel("New Applicant");
		heading.setFont(new Font("Raleway", Font.BOLD, 30));
		heading.setBounds(500, 20, 250, 30);
		add(heading);
		// Scheme Type
		scheme = new JLabel("Scheme Type : ");
		scheme.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 20));
		scheme.setBounds(150, 100, 150, 25);
		add(scheme);
		saving = new JRadioButton("Saving");
		saving.setBounds(320, 100, 70, 30);
		saving.setContentAreaFilled(false);
		pension = new JRadioButton("Pension");
		pension.setBounds(400, 100, 75, 30);
		pension.setContentAreaFilled(false);
		pension.setFocusable(false);
		current = new JRadioButton("Current");
		current.setBounds(480, 100, 70, 30);
		current.setFocusable(false);
		current.setContentAreaFilled(false);
		add(pension);
		add(saving);
		add(current);
		ButtonGroup schemeGroup = new ButtonGroup();
		schemeGroup.add(current);
		schemeGroup.add(saving);
		schemeGroup.add(pension);

		// Name and gender
		name = new TextField("Full Name*", 150, 250, 500, 28, Color.gray);
		name.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		add(name);

		gender = new JLabel("Gender: ");
		gender.setBounds(150, 150, 70, 30);
		gender.setFont(new Font("Raleway", Font.BOLD, 15));
		add(gender);
		male = new JRadioButton("Male");
		male.setBounds(250, 150, 70, 30);
		male.setContentAreaFilled(false);
		female = new JRadioButton("Female");
		female.setBounds(350, 150, 100, 30);
		female.setContentAreaFilled(false);
		add(male);
		add(female);
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(male);
		genderGroup.add(female);

		// Fathers name and mothers name
		fname = new TextField("Father name*", 150, 310, 250, 28, Color.gray);
		fname.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		mname = new TextField("Mother Name*", 460, 310, 250, 28, Color.gray);
		mname.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		pincode = new TextField("Pincode*", 770, 310, 250, 28, Color.gray);
		pincode.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		add(fname);
		add(mname);
		add(pincode);

		// mobile pan aadhar
		mobile = new TextField("Mobile no.*", 150, 370, 250, 28, Color.gray);
		mobile.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		pan = new TextField("PAN Number*", 460, 370, 250, 30, Color.gray);
		pan.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		aadhar = new TextField("Aadhar Card*", 770, 370, 250, 30, Color.gray);
		aadhar.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		add(mobile);
		add(aadhar);
		add(pan);

		// address and email
		emailid = new TextField("Email ID*", 150, 430, 250, 28, Color.gray);
		emailid.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));

		state = new TextField("State: *", 460, 430, 250, 30, Color.gray);
		state.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		city = new TextField("City: *", 770, 430, 250, 30, Color.gray);
		city.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		add(emailid);
		add(state);
		add(city);
		
		
		// DOB AND DP
		dob=new JLabel("Date of Birth: ");
		dob.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 20));
		dob.setBounds(150,185,150,50);
		date_chooser = new JDateChooser();
        date_chooser.setBounds(300,200,300,25);
        date_chooser.setFont(new Font("Raleway", Font.BOLD, 15));
        date_chooser.setForeground(Color.BLACK);
        add(dob);
        add(date_chooser);
        //
        img= new JLabel();
        img.setBounds(730, 90, 170, 180);
        img.setBackground(Color.BLACK);
        setdefaultImg();
        add(img);
        
        upload = new JButton("Upload Profile Photo");
		upload.setBackground(new Color(115,224,171));
		upload.setBounds(930, 240, 150, 30);
		upload.setFocusable(false);
		upload.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		upload.addActionListener(this);
		add(upload);
        

		confirm = new JCheckBox("I hereby declares that the above enter details are correct to the best of my knowledge");
		confirm.setFont(new Font("Raleway", Font.BOLD, 12));
		confirm.setBounds(150, 480, 600, 25);
		add(confirm);

		submit = new JButton("Submit");
		submit.setBackground(new Color(115,224,171));
		submit.setBounds(150, 510, 100, 25);
		submit.setFocusable(false);
		submit.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		submit.addActionListener(this);
		add(submit);

		setLayout(null);
		setTitle("Applicant Details");
		getContentPane().setBackground(Color.white);
		setSize(1200, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
//		saving,current,pension,male,female;
		if (e.getSource() == submit) {
			Accounts applicant = new Accounts();
			applicant.setBalance(0);
			applicant.setAadhar(this.aadhar.getText());
			applicant.setName(this.name.getText());
			applicant.setMobile(this.mobile.getText());
			applicant.setState(this.state.getText());
			applicant.setCity(this.city.getText());
			applicant.setFathersName(this.fname.getText());
			applicant.setMothersName(this.mname.getText());
			applicant.setPanCard(this.pan.getText());
			applicant.setEmail(this.emailid.getText());
			applicant.setPincode(this.pincode.getText());
			applicant.setDOB(date_chooser.getDate());
			applicant.setImage(imgToArray());
			
			if (saving.isSelected())
				applicant.setAccountType("Saving");
			else if (current.isSelected())
				applicant.setAccountType("Current");
			else if (pension.isSelected())
				applicant.setAccountType("Pension");

			if (male.isSelected())
				applicant.setGender("Male");
			else
				applicant.setGender("Female");

			if (applicant.getAadhar().length() == 0 || applicant.getAccountType().length() == 0 || applicant.getCity().length() == 0
					|| applicant.getEmail().length() == 0 || applicant.getFathersName().length() == 0
					|| applicant.getMothersName().length() == 0 || applicant.getMobile().length() == 0
					|| applicant.getState().length() == 0 || applicant.getPanCard().length() == 0
					|| applicant.getPincode().length() == 0 || applicant.getName().length() == 0) {
				JOptionPane.showMessageDialog(null, "Please Fill all the details");
			}else if(!confirm.isSelected()) {
				JOptionPane.showMessageDialog(null, "Please check the condition");
			} 
			else {
						
						this.dispose();
						new credentials(applicant);
			}
		}else if(e.getSource()==upload) {
			JFileChooser fc= new JFileChooser();
			fc.showOpenDialog(null);
			File f = fc.getSelectedFile();
			path=f.getAbsolutePath();
			try {
				BufferedImage bi = ImageIO.read(new File(path));
				Image image = bi.getScaledInstance(170, 180, Image.SCALE_DEFAULT);
				ImageIcon imgicon=new ImageIcon(image);
				img.setIcon(imgicon);
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new NewApplicant();
	}
	
	public void setdefaultImg() {
		ImageIcon imgicon = new ImageIcon(ClassLoader.getSystemResource("./Icons./default_Img.jpg"));
		Image image = imgicon.getImage().getScaledInstance(170, 180, Image.SCALE_DEFAULT);
		ImageIcon imgicon1=new ImageIcon(image);
		img.setIcon(imgicon1);
	}
	
	public byte[]imgToArray(){
		try {
			FileInputStream fis = new FileInputStream(path);
			byte[] data = new byte[fis.available()];
			fis.read(data);
			fis.close();
			return data;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
