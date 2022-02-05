package it.polimi.model.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "purchase", schema = "Telco")

public class Purchase{
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
    private Date date; 
	private float total;
	private String status;
	
	private Date subscription_date;
	
	
	// Eager because when we check a purchase, we also wanto to know what and who
	@JsonBackReference(value="svp-orders")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
		@JoinColumn(name="service_pkg_fk")
	private ServicePackage service_pkg;
	
	@JsonBackReference(value="user-orders")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
		CascadeType.REFRESH })
	@JoinColumn(name="user_foreignk")
	private User user;
	
	
	@ManyToMany 
	@JoinTable(name="order_product",
			joinColumns=@JoinColumn(name="order_fk"),
			inverseJoinColumns=@JoinColumn(name="product_fk"))
	private Collection<Product> products;
	
	
	public Purchase(int ID, float total, String status, Date subscription_date, Date date, ServicePackage service_pkg, User user) {
		this.ID = ID;
		this.date = date;
		this.total = total; 
		this.status = status;
		this.subscription_date = subscription_date;
		this.service_pkg = service_pkg;
		this.user = user;
	}
	
	public Purchase() {
	}
	
	
	
	public Purchase(int id) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date datet) {
		date = datet;
	}

	public Date getSubscription_date() {
		return subscription_date;
	}

	public void setSubscription_date(Date subscription_date) {
		this.subscription_date = subscription_date;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ServicePackage getService_pkg() {
		return service_pkg;
	}

	public void setService_pkg(ServicePackage service_pkg) {
		this.service_pkg = service_pkg;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
}
