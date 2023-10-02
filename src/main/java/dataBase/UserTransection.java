package dataBase;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Transection")
public class UserTransection {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long serialNo;
	
	@ManyToOne
	@JoinColumn(name="accountnumber")
	private Accounts account;
	
	@Column
	private long amount;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date DOB;
	
	@Column
	private String reciever_Account;
	
	@Column
	private String sender_Account;
	
	@Column
	private String type;
	
	public int fieldToDisplay() {
		return 5;
	}
//	"Date","Amount","Sender","Receiver","Type"
	public Object[] toArray() {
		Object[] obj =new Object [5]; 
		obj[0]=this.DOB;
		obj[1]=this.amount;
		obj[2]=this.sender_Account;
		obj[3]=this.reciever_Account;
		obj[4]=this.type;
		return obj;
	}

	public Accounts getAccount() {
		return account;
	}

	public void setAccount(Accounts account) {
		this.account = account;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return DOB;
	}

	public void setDate(Date date) {
		this.DOB = date;
	}

	public String getReciever_Account() {
		return reciever_Account;
	}

	public void setReciever_Account(String reciever_Account) {
		this.reciever_Account = reciever_Account;
	}

	public String getSender_Account() {
		return sender_Account;
	}

	public void setSender_Account(String sender_Account) {
		this.sender_Account = sender_Account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	

}
