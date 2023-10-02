package dataBase;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Accounts {
	@Id
	@GeneratedValue(generator = "account_seq",strategy = GenerationType.AUTO)
	@SequenceGenerator(name="account_seq",sequenceName = "account_sequence",initialValue = 50001,allocationSize = 5)
	private long accountNo;

	@Column
	private long balance;
	@Column
	private String Aadhar;
	
	@Column
	private String name;
	
	@Column
	private String mobile;
	
	@Column
	private String State;
	
	@Column
	private String City;
	
	@Column
	private String AccountType;
	
	@Column
	private String FathersName;
	
	@Column
	private String MothersName;
	
	@Column
	private String PanCard;
	
	@Column
	private String Email;
	
	@Column
	private String Pincode;
	
	@Column
	private String Gender;
	
	@Temporal(TemporalType.DATE)
	private Date DOB;
	
	@OneToOne(mappedBy = "details")
	private logIn credentials;
	
	@OneToMany(mappedBy = "account")
	private List<UserTransection> transection;
	
	@Lob
	private byte[] image;

	
	
	public long getAccountNo() {
		return accountNo;
	}
	
	
	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getAadhar() {
		return Aadhar;
	}

	public void setAadhar(String aadhar) {
		Aadhar = aadhar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getFathersName() {
		return FathersName;
	}

	public void setFathersName(String fathersName) {
		FathersName = fathersName;
	}

	public String getMothersName() {
		return MothersName;
	}

	public void setMothersName(String mothersName) {
		MothersName = mothersName;
	}

	public String getPanCard() {
		return PanCard;
	}

	public void setPanCard(String panCard) {
		PanCard = panCard;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPincode() {
		return Pincode;
	}

	public void setPincode(String pincode) {
		Pincode = pincode;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public logIn getCredentials() {
		return credentials;
	}

	public void setCredentials(logIn credentials) {
		this.credentials = credentials;
	}

	public List<UserTransection> getTransection() {
		return transection;
	}

	public void setTransection(List<UserTransection> transection) {
		this.transection = transection;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
	
	

}
