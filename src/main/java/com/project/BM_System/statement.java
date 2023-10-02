package com.project.BM_System;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dataBase.UserTransection;
import dataBase.logIn;

public class statement extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton back;
	private String uname;
	public statement(String uname) {
		this.uname=uname;
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session s = sf.openSession();
		logIn person = (logIn) s.get(logIn.class, uname);
		List<UserTransection> tnx = person.getDetails().getTransection();

		if (tnx.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No transection found");
		} else {
			String[] columns = { "Date", "Amount", "Sender", "Receiver", "Type" };
			Object[][] data = listToArray(tnx);

			JTable table = new JTable(data, columns);
			table.setCellSelectionEnabled(true);
			ListSelectionModel model = table.getSelectionModel();
			model.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scroll = new JScrollPane(table);
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBounds(0, 100, 800, 300);
			scroll.setBounds(10, 0, 770, 290);
			panel.add(scroll);
			add(panel);

			JLabel heading = new JLabel("Transaction History");
			heading.setFont(new Font("Raleway", Font.BOLD, 30));
			heading.setBounds(20, 0, 300, 150);
			add(heading);
			
			back = new JButton("back");
			back.addActionListener(this);
			back.setFocusable(false);
			back.setBounds(600, 400, 150, 40);
			back.setBackground(new Color(115, 224, 171));
			back.setForeground(Color.WHITE);
			add(back);
			
		}
		setLayout(null);
		setForeground(Color.WHITE);
		setTitle("Bank Management System");
		setSize(800, 500);
		setLocation(50, 50);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		new userLogin(uname);
	}

	private Object[][] listToArray(List<UserTransection> tnx) {
		int n = tnx.size();
		Object[][] result = new Object[n][];
		for (int i = 0; i < n; i++) {
			result[i] = tnx.get(i).toArray();
		}
		return result;
	}
	
	public static void main(String[] args) {
		new statement("sarthak_13");
	}

}
