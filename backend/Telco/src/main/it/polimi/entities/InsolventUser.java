package main.it.polimi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="Telco.insolvent_user")
public class InsolventUser {
	
	@Id 
	@Column (name="id", nullable=false)
	private int id;
	@Column (name="username", nullable=false)
	private String username;
	@Column (name="email", nullable=false)
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
