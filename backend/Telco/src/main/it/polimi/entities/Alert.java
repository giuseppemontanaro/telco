package main.it.polimi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alert_view")

public class Alert {
	
	@Id
	private int user_id;
	private String username;
	private String email;
	private int amount;
	private Date last_rejection_date;
	
	
	public Alert() {

	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
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


	public Date getLast_rejection_date() {
		return last_rejection_date;
	}


	public void setLast_rejection_date(Date last_rejection_date) {
		this.last_rejection_date = last_rejection_date;
	}

	
	

}
