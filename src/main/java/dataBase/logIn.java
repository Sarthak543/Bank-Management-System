package dataBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class logIn {

	@Id
	@Column
	private String UserName;
	@Column
	private String password;
	@Enumerated(EnumType.STRING)
	private STATUS accountStatus;
	@OneToOne
	@JoinColumn(name = "account_Number")
	private Accounts details;
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public STATUS getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(STATUS accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Accounts getDetails() {
		return details;
	}
	public void setDetails(Accounts details) {
		this.details = details;
	}
	
	
	
	
}
