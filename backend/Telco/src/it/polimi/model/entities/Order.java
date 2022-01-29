package it.polimi.model.entities;

import java.time.LocalDate;
import java.util.Collection;

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

@Entity
@Table(name = "ordering", schema = "Telco")

public class Order{
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
    private LocalDate Date; 
	private float total;
	private String status;
	private LocalDate subscription_date;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
		@JoinColumn(name="val_period_fk")
	private ValidityPeriod val_period;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
		@JoinColumn(name="service_pkg_fk")
	private ServicePackage service_pkg;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
		CascadeType.REFRESH })
	@JoinColumn(name="user_foreignk")
	private User user;
	
	
	@ManyToMany 
	@JoinTable(name="order_product",
			joinColumns=@JoinColumn(name="order_fk"),
			inverseJoinColumns=@JoinColumn(name="product_fk"))
	private Collection<Product> products;
	
	
	public Order(int ID, float total, String status, LocalDate subscription_date, LocalDate date, ValidityPeriod val_period, ServicePackage service_pkg, User user) {
		this.ID = ID;
		this.Date = date;
		this.total = total; 
		this.status = status;
		this.subscription_date = subscription_date;
		this.val_period = val_period;
		this.service_pkg = service_pkg;
		this.user = user;
	}
	
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

	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}

	public LocalDate getSubscription_date() {
		return subscription_date;
	}

	public void setSubscription_date(LocalDate subscription_date) {
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


}
