package it.polimi.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "User", schema = "Telco")
@NamedQuery(name = "checkCredentials", query = "SELECT r FROM User r  WHERE r.Username = ?1 and r.Password = ?2")

public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//To generate automatically primary keys
	private int id;
	private String Username;
	private String Password;
	private boolean isInsolvent;
	private boolean isEmployee;
	private String eMail;
	
	//Eager because orders will be few for a user
	@JsonManagedReference(value="user-orders")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
	private List<Purchase> orders;
	

	public User() {
	}


	public User(int id) {
		this.id = id;
		
	}
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return Username;
	}


	public void setUsername(String username) {
		Username = username;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}




	public boolean getIsInsolvent() {
		return isInsolvent;
	}


	public void setInsolvent(boolean isInsolvent) {
		this.isInsolvent = isInsolvent;
	}


	public boolean getIsEmployee() {
		return isEmployee;
	}


	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}


	public String geteMail() {
		return eMail;
	}


	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public List<Purchase> getOrders() {
		return this.orders;
	}


	public void setOrders(List<Purchase> orders) {
		this.orders = orders;
	}
	
	
	
	
	

}
