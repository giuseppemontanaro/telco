package it.polimi.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ordering", schema = "Telco")

public class Order{
	//		//To generate automatically primary keys

	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	private String Date;	//TO BE DEFINED
	private float total;
	private String status;
	private String subscription_date;
	//private int val_period;
	//private int service_pkg;
	
	public Order() {
	}
	
	public Order(int id) {
		this.ID = id;
	}
	
	
	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}


	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getSubscription_date() {
		return subscription_date;
	}

	public void setSubscription_date(String subscription_date) {
		this.subscription_date = subscription_date;
	}
	
	
	

	
	

}
