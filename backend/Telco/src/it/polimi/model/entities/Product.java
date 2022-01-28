package it.polimi.model.entities;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Product {
	
	@Id
	private int ID;
	private int monthly_fee;
	private String name;
	
	
	
	public Product() {
	}
	
	public Product(int ID, int monthly_fee, String name) {
		this.ID = ID;
		this.monthly_fee = monthly_fee;
		this.name = name;
	}

}
