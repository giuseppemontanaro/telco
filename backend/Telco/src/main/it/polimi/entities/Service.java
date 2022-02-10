package main.it.polimi.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Service implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//To generate automatically primary keys

	private int ID;
	private String Title;
	private Integer gigabytes;
	private Float gigabytes_extra_fee;
	private Integer SMS;
	private Float SMS_extra_fee;
	private Integer minutes;
	private Float minutes_extra_fee;
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

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public Integer getGigabytes() {
		return gigabytes;
	}

	public void setGigabytes(Integer gigabytes) {
		this.gigabytes = gigabytes;
	}

	public Float getGigabytes_extra_fee() {
		return gigabytes_extra_fee;
	}

	public void setGigabytes_extra_fee(Float gigabytes_extra_fee) {
		this.gigabytes_extra_fee = gigabytes_extra_fee;
	}

	public Integer getSMS() {
		return SMS;
	}

	public void setSMS(Integer sMS) {
		SMS = sMS;
	}

	public Float getSMS_extra_fee() {
		return SMS_extra_fee;
	}

	public void setSMS_extra_fee(Float sMS_extra_fee) {
		SMS_extra_fee = sMS_extra_fee;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Float getMinutes_extra_fee() {
		return minutes_extra_fee;
	}

	public void setMinutes_extra_fee(Float minutes_extra_fee) {
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
