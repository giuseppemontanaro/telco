package it.polimi.model.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "service_package", schema = "Telco")
@NamedQuery(name = "AllServicePackage", query = "SELECT p FROM ServicePackage p")
@NamedQuery(name = "AllServicePackageNames", query = "SELECT p.name FROM ServicePackage p")
@NamedQuery(name = "GetPackage", query = "SELECT p FROM ServicePackage p where p.name = ?1")


public class ServicePackage {
	
	@Id
	private int ID;
	private String name;
	

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
		CascadeType.REFRESH })
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToMany 
	@JoinTable(name="service_package_product",
			joinColumns=@JoinColumn(name="service_package_fk"),
			inverseJoinColumns=@JoinColumn(name="product_fk"))
	private Collection<Product> products;
	
	
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
	
	
	public void addProduct(Product p) {
		this.getProducts().add(p);
	}
	

}
