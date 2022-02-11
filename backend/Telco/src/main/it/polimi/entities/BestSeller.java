package main.it.polimi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BestSeller {
	
	@Id
	private int ID;
	private String name;
	private int monthly_fee;
	
	
	public BestSeller() {
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
	public int getMonthly_fee() {
		return monthly_fee;
	}
	public void setMonthly_fee(int monthly_fee) {
		this.monthly_fee = monthly_fee;
	}
	
	
	

}
