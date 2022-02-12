package main.it.polimi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alert_view")

public class Alert {
	
	@Id
	private int userId;
	private String username;
	private String email;
	private int amount;
	private Date lastRejectionDate;
	
	
	public Alert() {

	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public Date getLastRejectionDate() {
		return lastRejectionDate;
	}


	public void setLastRejectionDate(Date lastRejectionDate) {
		this.lastRejectionDate = lastRejectionDate;
	}

	
	

}
