package main.it.polimi.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "purchase", schema = "Telco")
public class Purchase implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
    private Date date; 
	private float total;
	private boolean isRejected;
	private Date subscription_date;
	
	
	@JsonBackReference(value="svp-orders")
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
		@JoinColumn(name="service_pkg_fk")
	private ServicePackage service_pkg;
	
	@JsonBackReference(value="user-orders")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="user_foreignk")
	private User user;

	@ManyToMany 
	@JoinTable(name="order_product",
			joinColumns=@JoinColumn(name="order_fk"),
			inverseJoinColumns=@JoinColumn(name="product_fk"))
	private Collection<Product> products;

	@JsonBackReference(value="val-orders")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="validity_period_fk")
	private ValidityPeriod validityPeriod;


	public Purchase(int ID, float total, Date subscription_date, Date date, ServicePackage service_pkg, User user) {
		this.ID = ID;
		this.date = date;
		this.total = total; 
		this.subscription_date = subscription_date;
		this.service_pkg = service_pkg;
		this.user = user;
	}
	
	public Purchase() {
	}
	
	public Purchase(int id) {
		this.ID = id;
	}

	public boolean isRejected() {
		return isRejected;
	}

	public void setRejected(boolean rejected) {
		isRejected = rejected;
	}

	public void setValidityPeriod(ValidityPeriod validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public ValidityPeriod getValidityPeriod() {
		return validityPeriod;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
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

	public Boolean getIsRejected() {
		return isRejected;
	}

	public void setIsRejected(Boolean isRejected) {
		this.isRejected = isRejected;
	}

	@Override
	public String toString() {
		return "Purchase{" +
				"ID=" + ID +
				", date=" + date +
				", total=" + total +
				", isRejected=" + isRejected +
				", subscription_date=" + subscription_date +
				", service_pkg=" + service_pkg +
				", user=" + user +
				", products=" + products +
				'}';
	}
}
