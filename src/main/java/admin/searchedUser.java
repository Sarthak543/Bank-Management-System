package admin;

import java.awt.event.ActionEvent;

import com.project.BM_System.userDetails;

public class searchedUser extends userDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public searchedUser(String uname) {
		super(uname);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==super.back){
			new AdminHome();
			this.dispose();
		}
	}
	
}
