package ATM;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.project.BM_System.userLogin;

public class mainScreen extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton deposit, cashWithdrawal, fastCash, balance, exit;
	String uname;
	public mainScreen(String uname){
		this.uname=uname;
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

        deposit = new JButton("Deposit");
        deposit.setBounds(170, 415, 150, 30);
        deposit.addActionListener(this);
        background.add(deposit);

        cashWithdrawal = new JButton("Cash Withdrawal");
        cashWithdrawal.setBounds(345, 415, 150, 30);
        cashWithdrawal.addActionListener(this);
        background.add(cashWithdrawal);

        fastCash = new JButton("Fast Cash");
        fastCash.setBounds(170, 450, 150, 30);
        fastCash.addActionListener(this);
        background.add(fastCash);

        balance = new JButton("Check Balance");
        balance.setBounds(345, 450, 150, 30);
        balance.addActionListener(this);
        background.add(balance);


        exit = new JButton("Exit");
        exit.setBounds(345, 520, 150, 30);
        exit.addActionListener(this);
        background.add(exit);

//        todo: dimension
        setSize(900, 900);
        setLayout(null);
        setLocation(300, 0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==deposit) {
			this.dispose();
			new deposit(uname);
		}else if(e.getSource()==cashWithdrawal) {
			this.dispose();
			new cashWithdrawal(uname);
		}else if(e.getSource()==fastCash) {
			this.dispose();
			new fastCash(uname);
		}else if(e.getSource()==balance) {
			new checkBalance(uname);
			this.dispose();
		}else if(e.getSource()==exit) {
			this.dispose();
			new userLogin(uname);
		}
	}
	
	public static void main(String[] args) {
		new mainScreen("s");
	}

}
