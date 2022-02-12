package main.it.polimi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="insolvent_users_view")

public class InsolventUser {
	
	@Id 
	private int id;
	private String username;
	private String email;
	
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
		return email;
	}

	public void seteMail(String eMail) {
		this.email = eMail;
	}
	
	
	
	
	
	
	
	

}
