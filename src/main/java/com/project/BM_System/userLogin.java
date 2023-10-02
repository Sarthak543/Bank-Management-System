package com.project.BM_System;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ATM.mainScreen;

public class userLogin extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton signout,accountInfo,transferMoney,ATM,changePassword,EStatement;
	private JLabel userName;
	private String uname;
	public userLogin(String name) {
		this.uname=name;
		userName=new JLabel(name);
		userName.setBounds(100,70,550,40);
		userName.setFont(new Font("Raleway", Font.BOLD, 27));
		add(userName);
		
		signout= new JButton("Sign Out");
		signout.setBounds(800, 30, 170, 40);
		signout.setFocusable(false);
		signout.setBackground(new Color(115, 224, 171));
		signout.setForeground(Color.WHITE);
		signout.addActionListener(this);
		add(signout);
		
		accountInfo = new JButton("Account information");
		accountInfo.setBounds(100, 190, 170, 40);
		accountInfo.setFocusable(false);
		accountInfo.setBackground(new Color(115, 224, 171));
		accountInfo.setForeground(Color.WHITE);
		accountInfo.addActionListener(this);
		add(accountInfo);
		
		transferMoney = new JButton("Online Transfer Money");
		transferMoney.setBounds(390, 190, 170, 40);
		transferMoney.setFocusable(false);
		transferMoney.setBackground(new Color(115, 224, 171));
		transferMoney.setForeground(Color.WHITE);
		transferMoney.addActionListener(this);
		add(transferMoney);
		
		
		ATM=new JButton("ATM");
		ATM.setBounds(680, 190, 170, 40);
		ATM.setFocusable(false);
		ATM.setBackground(new Color(115, 224, 171));
		ATM.setForeground(Color.WHITE);
		ATM.addActionListener(this);
		add(ATM);
		
		
		changePassword=new JButton("Change Password");
		changePassword.setBounds(100, 290, 170, 40);
		changePassword.setFocusable(false);
		changePassword.setBackground(new Color(115, 224, 171));
		changePassword.setForeground(Color.WHITE);
		changePassword.addActionListener(this);
		add(changePassword);
		
		EStatement = new JButton("EStatement");
		EStatement.addActionListener(this);
		EStatement.setFocusable(false);
		EStatement.setBounds(390, 290, 170, 40);
		EStatement.setBackground(new Color(115, 224, 171));
		EStatement.setForeground(Color.WHITE);
		add(EStatement);
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("User");
		setSize(1000, 600);
		setLocation(100, 100);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==accountInfo) {
			new userDetails(uname);
			this.dispose();
		}
		else if(e.getSource()==signout) {
			new App();
			this.dispose();
		}else if(e.getSource()==ATM) {
			this.dispose();
			new mainScreen(uname);
		}else if(e.getSource()==transferMoney) {
			new onlineTransferMoeny(uname);
			this.dispose();
		}else if(e.getSource()==EStatement) {
			new statement(uname);
			this.dispose();
		}
		else if(e.getSource()==changePassword) {
			new changePassword(uname);
			this.dispose();
		}
	}
public static void main(String[] args) {
	new userLogin("sarthak_13");
}

}
