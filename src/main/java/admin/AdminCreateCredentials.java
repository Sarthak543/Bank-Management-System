package admin;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.project.BM_System.credentials;

import dataBase.Accounts;
import dataBase.STATUS;
import dataBase.logIn;

public class AdminCreateCredentials extends credentials {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminCreateCredentials(Accounts applicant) {
		super(applicant);
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
					new AdminHome();
				} catch (HibernateException he) {
					he.printStackTrace();
				}
			}
		}

	}

}
