package main.it.polimi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InsolventUser {
	
	@Id 
	private int id;
	private String username;
	private String eMail;
	
	public InsolventUser() {
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

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	
	
	
	
	
	
	

}
