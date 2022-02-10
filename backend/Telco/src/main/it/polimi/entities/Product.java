package main.it.polimi.entities;


import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@NamedQuery(name = "AllProduct", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	private int monthly_fee;
	private String name;
	
	@JsonBackReference
	@ManyToMany(mappedBy="products")
	private Collection<ServicePackage> servicePackages;
	
	
	public Product() {
	}
	
	public Product(int ID, int monthly_fee, String name) {
		this.ID = ID;
		this.monthly_fee = monthly_fee;
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getMonthly_fee() {
		return monthly_fee;
	}

	public void setMonthly_fee(int monthly_fee) {
		this.monthly_fee = monthly_fee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<ServicePackage> getServicePackages() {
		return servicePackages;
	}

	public void setServicePackages(Collection<ServicePackage> servicePackages) {
		this.servicePackages = servicePackages;
	}

	@Override
	public String toString() {
		return "Product{" +
				"ID=" + ID +
				", monthly_fee=" + monthly_fee +
				", name='" + name + '\'' +
				", servicePackages=" + servicePackages +
				'}';
	}
}
