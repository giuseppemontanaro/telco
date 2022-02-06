package it.polimi.model.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "service_package", schema = "Telco")
@NamedQuery(name = "AllServicePackage", query = "SELECT p FROM ServicePackage p")
@NamedQuery(name = "AllServicePackageNames", query = "SELECT p.name FROM ServicePackage p")
@NamedQuery(name = "GetPackage", query = "SELECT p FROM ServicePackage p where p.name = ?1")


public class ServicePackage {
	
	@Id
	private int ID;
	private String name;
	
	
	@JsonBackReference(value="user-svp")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
		CascadeType.REFRESH })
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonManagedReference(value="svp-orders")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "service_pkg", cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
	private List<Purchase> purchaseList;
	
	
	@ManyToMany 
	@JoinTable(name="service_package_product",
			joinColumns=@JoinColumn(name="service_package_fk"),
			inverseJoinColumns=@JoinColumn(name="product_fk"))
	private Collection<Product> products;
	
	
	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable(name="service_package_service",
			joinColumns=@JoinColumn(name="service_package_fk"),
			inverseJoinColumns=@JoinColumn(name="service_fk"))
	private Collection<Service> services;
	
	//@JsonManagedReference
	@ManyToMany 
	@JoinTable(name="service_package_validity_period",
			joinColumns=@JoinColumn(name="service_package_fk"),
			inverseJoinColumns=@JoinColumn(name="validity_period_fk"))
	private Collection<ValidityPeriod> periods;
	
	public ServicePackage() {
		
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}



	public Collection<Product> getProducts() {
		return products;
	}



	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	
	
	
	public Collection<Service> getServices() {
		return services;
	}


	public void setServices(Collection<Service> services) {
		this.services = services;
	}



	public Collection<ValidityPeriod> getPeriods() {
		return periods;
	}



	public List<Purchase> getPurchaseList() {
		return purchaseList;
	}


	public void setPurchaseList(List<Purchase> purchaseList) {
		this.purchaseList = purchaseList;
	}


	public void setPeriods(Collection<ValidityPeriod> periods) {
		this.periods = periods;
	}


	public void addProduct(Product p) {
		this.getProducts().add(p);
	}
	
	public void addService(Service s) {
		this.getServices().add(s);
	}
	
	public void addPeriod(ValidityPeriod s) {
		this.getPeriods().add(s);
	}
	
	
	
	

}
