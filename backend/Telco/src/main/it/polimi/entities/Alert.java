package main.it.polimi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Alert {
	
	@Id
	private int id;
	private String username;
	private String email;
	private int sumCost;
	private Date maxDate;
	
	
	public Alert() {

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public int getSumCost() {
		return sumCost;
	}


	public void setSumCost(int sumCost) {
		this.sumCost = sumCost;
	}


	public Date getMaxDate() {
		return maxDate;
	}


	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	
	
	
	
	
	
	

}
