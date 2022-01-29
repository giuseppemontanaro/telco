package it.polimi.model.servicesType;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FixedInternet {
	
	@Id
	private int id;
	private int gigabytes;
	private float gigabytes_extra_fee;
	
	
	
	public FixedInternet() {
	}
	

}
