package admin;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.project.BM_System.NewApplicant;

import dataBase.Accounts;

public class AdminCreateAccount  extends NewApplicant{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminCreateAccount() {
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			Accounts applicant = new Accounts();
			applicant.setBalance(0);
			applicant.setAadhar(super.aadhar.getText());
			applicant.setName(super.name.getText());
			applicant.setMobile(super.mobile.getText());
			applicant.setState(super.state.getText());
			applicant.setCity(super.city.getText());
			applicant.setFathersName(super.fname.getText());
			applicant.setMothersName(super.mname.getText());
			applicant.setPanCard(super.pan.getText());
			applicant.setEmail(super.emailid.getText());
			applicant.setPincode(super.pincode.getText());
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

			if (applicant.getAadhar().length() == 0 || applicant.getAccountType().length() == 0
					|| applicant.getCity().length() == 0 || applicant.getEmail().length() == 0
					|| applicant.getFathersName().length() == 0 || applicant.getMothersName().length() == 0
					|| applicant.getMobile().length() == 0 || applicant.getState().length() == 0
					|| applicant.getPanCard().length() == 0 || applicant.getPincode().length() == 0
					|| applicant.getName().length() == 0) {
				JOptionPane.showMessageDialog(null, "Please Fill all the details");
			} else if (!confirm.isSelected()) {
				JOptionPane.showMessageDialog(null, "Please check the condition");
			} else {

				this.dispose();
				new AdminCreateCredentials(applicant);
			}
		} else if (e.getSource() == upload) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			File f = fc.getSelectedFile();
			path = f.getAbsolutePath();
			try {
				BufferedImage bi = ImageIO.read(new File(path));
				Image image = bi.getScaledInstance(170, 180, Image.SCALE_DEFAULT);
				ImageIcon imgicon = new ImageIcon(image);
				img.setIcon(imgicon);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
