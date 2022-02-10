package main.it.polimi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SuspendedPurchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
    private Date date; 
	private float total;
	private boolean isRejected;
	private Date subscription_date;
	private int user;
	private int servicePackage;
	
	
	
	public SuspendedPurchase() {
	}



	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public float getTotal() {
		return total;
	}



	public void setTotal(float total) {
		this.total = total;
	}



	public boolean isRejected() {
		return isRejected;
	}



	public void setRejected(boolean isRejected) {
		this.isRejected = isRejected;
	}



	public Date getSubscription_date() {
		return subscription_date;
	}



	public void setSubscription_date(Date subscription_date) {
		this.subscription_date = subscription_date;
	}



	public int getUser() {
		return user;
	}



	public void setUser(int user) {
		this.user = user;
	}



	public int getServicePackage() {
		return servicePackage;
	}



	public void setServicePackage(int servicePackage) {
		this.servicePackage = servicePackage;
	}
	
	
	
	
	
	
	
	
	

}
