package it.polimi.model.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Service {
	
	
	@Id
	private int ID;
	private String Title;
	private int gigabytes;
	private float gigabytes_extra_fee;
	private int SMS;
	private float SMS_extra_fee;
	private int minutes;
	private float minutes_extra_fee;
	private String category;
	
	@JsonBackReference
	@ManyToMany(mappedBy="services")
	private Collection<ServicePackage> servicePackages;
	
	public Service() {
		
	}
	
	public Service(int id) {
        this.ID = id;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public int getGigabytes() {
		return gigabytes;
	}

	public void setGigabytes(int gigabytes) {
		this.gigabytes = gigabytes;
	}

	public float getGigabytes_extra_fee() {
		return gigabytes_extra_fee;
	}

	public void setGigabytes_extra_fee(float gigabytes_extra_fee) {
		this.gigabytes_extra_fee = gigabytes_extra_fee;
	}

	public int getSMS() {
		return SMS;
	}

	public void setSMS(int sMS) {
		SMS = sMS;
	}

	public float getSMS_extra_fee() {
		return SMS_extra_fee;
	}

	public void setSMS_extra_fee(float sMS_extra_fee) {
		SMS_extra_fee = sMS_extra_fee;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public float getMinutes_extra_fee() {
		return minutes_extra_fee;
	}

	public void setMinutes_extra_fee(float minutes_extra_fee) {
		this.minutes_extra_fee = minutes_extra_fee;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Collection<ServicePackage> getServicePackages() {
		return servicePackages;
	}

	public void setServicePackages(Collection<ServicePackage> servicePackages) {
		this.servicePackages = servicePackages;
	}
	
	
	
	

    
}
