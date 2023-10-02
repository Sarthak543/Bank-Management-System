package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.toedter.calendar.JDateChooser;

import dataBase.Accounts;

public class updateClienInfo extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path;
	private JButton done, change;
	private JRadioButton male, female, saving, pension, current;
	private Accounts userDetails;
	private JLabel heading, background, Aadhar, name, mobile, State, City, FathersName, MothersName, PanCard, Email,
			Pincode, gender, dob, scheme;
	private JTextField Aadhar_tf, name_tf, mobile_tf, State_tf, City_tf, FathersName_tf, MothersName_tf, PanCard_tf,
			Email_tf, Pincode_tf;
	private JDateChooser date_chooser;

	public updateClienInfo(Accounts  userDetails) {
		this.userDetails = userDetails;
		// heading
		heading = new JLabel("Update User Info");
		heading.setFont(new Font("Raleway", Font.BOLD, 40));
		heading.setBounds(80, 50, 500, 70);
		add(heading);

		// account Type

		scheme = new JLabel("Scheme Type : ");
		scheme.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 20));
		scheme.setBounds(80, 120, 150, 25);
		add(scheme);
		saving = new JRadioButton("Saving");
		saving.setBounds(250, 120, 70, 30);
		saving.setContentAreaFilled(false);
		pension = new JRadioButton("Pension");
		pension.setBounds(330, 120, 75, 30);
		pension.setContentAreaFilled(false);
		pension.setFocusable(false);
		current = new JRadioButton("Current");
		current.setBounds(410, 120, 70, 30);
		current.setFocusable(false);
		current.setContentAreaFilled(false);
		add(pension);
		add(saving);
		add(current);
		ButtonGroup schemeGroup = new ButtonGroup();
		schemeGroup.add(current);
		schemeGroup.add(saving);
		schemeGroup.add(pension);
		if (userDetails.getAccountType().equals("Saving")) {
			saving.setSelected(true);
		} else if (userDetails.getAccountType().equals("Current")) {
			current.setSelected(true);
		} else if (userDetails.getAccountType().equals("Pension")) {
			pension.setSelected(true);
		}

		// name
		name = new JLabel("Name :");
		name.setBounds(80, 150, 50, 50);
		name.setFont(new Font("Raleway", Font.ITALIC, 15));
		add(name);
		name_tf = new JTextField();
		name_tf.setText(userDetails.getName());
		name_tf.setBounds(135, 165, 150, 20);
		add(name_tf);

		// Gender
		gender = new JLabel("Gender: ");
		gender.setBounds(340, 160, 70, 30);
		gender.setFont(new Font("Raleway", Font.BOLD, 15));
		add(gender);
		male = new JRadioButton("Male");
		male.setBounds(420, 160, 70, 30);
		male.setContentAreaFilled(false);
		female = new JRadioButton("Female");
		female.setBounds(490, 160, 100, 30);
		female.setContentAreaFilled(false);
		add(male);
		add(female);
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(male);
		genderGroup.add(female);
		if (userDetails.getGender().equals("Male")) {
			male.setSelected(true);
		} else if (userDetails.getGender().equals("Female")) {
			female.setSelected(true);
		}

		// Email
		Email = new JLabel("Email :");
		Email.setBounds(630, 150, 50, 50);
		Email.setFont(new Font("Raleway", Font.ITALIC, 15));
		add(Email);
		Email_tf = new JTextField();
		Email_tf.setText(userDetails.getEmail());
		Email_tf.setBounds(685, 165, 200, 20);
		add(Email_tf);

		// Mothers , pincode
		MothersName = new JLabel("Mother's Name :");
		MothersName.setBounds(80, 220, 120, 50);
		MothersName.setFont(new Font("Raleway", Font.ITALIC, 15));
		add(MothersName);
		MothersName_tf = new JTextField();
		MothersName_tf.setText(userDetails.getMothersName());
		MothersName_tf.setBounds(200, 235, 200, 20);
		add(MothersName_tf);

		// fathers
		FathersName = new JLabel("Father's Name :");
		FathersName.setBounds(440, 220, 120, 50);
		FathersName.setFont(new Font("Raleway", Font.ITALIC, 15));
		add(FathersName);
		FathersName_tf = new JTextField();
		FathersName_tf.setText(userDetails.getFathersName());
		FathersName_tf.setBounds(560, 235, 200, 20);
		add(FathersName_tf);

		// Pincode
		Pincode = new JLabel("Pincode :");
		Pincode.setBounds(800, 220, 120, 50);
		Pincode.setFont(new Font("Raleway", Font.ITALIC, 15));
		add(Pincode);
		Pincode_tf = new JTextField();
		Pincode_tf.setText(userDetails.getPincode());
		Pincode_tf.setBounds(880, 235, 200, 20);
		add(Pincode_tf);

		// mobile
		mobile = new JLabel("Mobile :");
		mobile.setBounds(80, 270, 120, 50);
		mobile.setFont(new Font("Raleway", Font.ITALIC, 15));
		add(mobile);
		mobile_tf = new JTextField();
		mobile_tf.setText(userDetails.getMobile());
		mobile_tf.setBounds(200, 285, 200, 20);
		add(mobile_tf);

		// City
		City = new JLabel("City :");
		City.setBounds(440, 270, 120, 50);
		City.setFont(new Font("Raleway", Font.ITALIC, 15));
		add(City);
		City_tf = new JTextField();
		City_tf.setText(userDetails.getCity());
		City_tf.setBounds(500, 285, 200, 20);
		add(City_tf);

		// State
		State = new JLabel("State :");
		State.setBounds(800, 270, 120, 50);
		State.setFont(new Font("Raleway", Font.ITALIC, 15));
		add(State);
		State_tf = new JTextField();
		State_tf.setText(userDetails.getState());
		State_tf.setBounds(880, 285, 200, 20);
		add(State_tf);

		// pancard
		PanCard = new JLabel("Pancard :");
		PanCard.setBounds(80, 325, 120, 50);
		PanCard.setFont(new Font("Raleway", Font.ITALIC, 15));
		add(PanCard);
		PanCard_tf = new JTextField();
		PanCard_tf.setText(userDetails.getPanCard());
		PanCard_tf.setBounds(200, 340, 200, 20);
		add(PanCard_tf);

		// Aadhar
		Aadhar = new JLabel("Aadhar :");
		Aadhar.setBounds(440, 325, 120, 50);
		Aadhar.setFont(new Font("Raleway", Font.ITALIC, 15));
		add(Aadhar);
		Aadhar_tf = new JTextField();
		Aadhar_tf.setText(userDetails.getAadhar());
		Aadhar_tf.setBounds(500, 340, 200, 20);
		add(Aadhar_tf);

		// DOB
		dob = new JLabel("Date of Birth: ");
		dob.setFont(new Font("Raleway", Font.ROMAN_BASELINE, 15));
		dob.setBounds(800, 325, 120, 50);
		date_chooser = new JDateChooser();
		date_chooser.setBounds(900, 340, 200, 20);
		date_chooser.setDate(userDetails.getDOB());
		date_chooser.setFont(new Font("Raleway", Font.BOLD, 15));
		date_chooser.setForeground(Color.BLACK);
		add(dob);
		add(date_chooser);

		// image
		background = new JLabel();
		background.setBounds(100, 400, 170, 180);
		if (userDetails.getImage()==null) {
			setDefaultImg();
		} else {
			try {
				
				BufferedImage bi = ImageIO.read(new ByteArrayInputStream(userDetails.getImage()));
				Image img = bi.getScaledInstance(170, 180, Image.SCALE_DEFAULT);
				ImageIcon imgIcon = new ImageIcon(img);
				background.setIcon(imgIcon);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		add(background);

		// btn
		done = new JButton("Done");
		done.addActionListener(this);
		done.setBounds(900, 520, 150, 40);
		done.setBackground(new Color(115, 224, 171));
		done.setForeground(Color.WHITE);
		add(done);

		// change
		change = new JButton("Change");
		change.addActionListener(this);
		change.setBounds(300, 500, 150, 40);
		change.setBackground(new Color(115, 224, 171));
		change.setForeground(Color.WHITE);
		add(change);

		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setTitle("Bank Management System");
		setSize(1200, 650);
		setLocation(50, 50);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == change) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			File f = fc.getSelectedFile();
			path = f.getAbsolutePath();
			try {
				BufferedImage bi = ImageIO.read(new File(path));
				Image image = bi.getScaledInstance(170, 180, Image.SCALE_DEFAULT);
				ImageIcon imgicon = new ImageIcon(image);
				background.setIcon(imgicon);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == done) {
			if (e.getSource() == done) {
				userDetails.setAadhar(this.Aadhar_tf.getText());
				userDetails.setName(this.name_tf.getText());
				userDetails.setMobile(this.mobile_tf.getText());
				userDetails.setState(this.State_tf.getText());
				userDetails.setCity(this.City_tf.getText());
				userDetails.setFathersName(this.FathersName_tf.getText());
				userDetails.setMothersName(this.MothersName_tf.getText());
				userDetails.setPanCard(this.PanCard_tf.getText());
				userDetails.setEmail(this.Email_tf.getText());
				userDetails.setPincode(this.Pincode_tf.getText());
				userDetails.setDOB(date_chooser.getDate());
				if(path!=null) {
					// if dp is changed
					userDetails.setImage(imgToArray());
				}

				if (saving.isSelected())
					userDetails.setAccountType("Saving");
				else if (current.isSelected())
					userDetails.setAccountType("Current");
				else if (pension.isSelected())
					userDetails.setAccountType("Pension");

				if (male.isSelected())
					userDetails.setGender("Male");
				else
					userDetails.setGender("Female");

				if (userDetails.getAadhar().length() == 0 || userDetails.getAccountType().length() == 0 || userDetails.getCity().length() == 0
						|| userDetails.getEmail().length() == 0 || userDetails.getFathersName().length() == 0
						|| userDetails.getMothersName().length() == 0 || userDetails.getMobile().length() == 0
						|| userDetails.getState().length() == 0 || userDetails.getPanCard().length() == 0
						|| userDetails.getPincode().length() == 0 || userDetails.getName().length() == 0) {
					JOptionPane.showMessageDialog(null, "Please Fill all the details");
				} else {

					SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
					Session s = sf.openSession();
					Transaction tx = s.beginTransaction();
					s.update(userDetails);
					tx.commit();
					s.close();
					sf.close();
					JOptionPane.showMessageDialog(null, "Updation succefully completed");
				}
			}
		}
	}

	private void setDefaultImg() {
		ImageIcon imgicon = new ImageIcon(ClassLoader.getSystemResource("./Icons./default_Img.jpg"));
		Image image = imgicon.getImage().getScaledInstance(170, 180, Image.SCALE_DEFAULT);
		ImageIcon imgicon1 = new ImageIcon(image);
		background.setIcon(imgicon1);
	}

	private byte[] imgToArray() {
		try {
			FileInputStream fis = new FileInputStream(path);
			byte[] data = new byte[fis.available()];
			fis.read(data);
			fis.close();
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
