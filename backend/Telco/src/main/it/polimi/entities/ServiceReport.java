package main.it.polimi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ServiceReport {
	
	@Id
	private int ID;
	private String name;
	
	
	
	public ServiceReport(int iD, String name) {
		super();
		ID = iD;
		this.name = name;
	}
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
