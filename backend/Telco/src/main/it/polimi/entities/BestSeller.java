package main.it.polimi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="best_seller_view")
public class BestSeller {
	
	@Id
	private String name;
	private int purchases;
	
	
	public BestSeller() {
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public int getPurchases() {
		return purchases;
	}


	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}
	
	
	
	
	

}
