package main.it.polimi.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "user", schema = "Telco")
@NamedQuery(name = "checkCredentials", query = "SELECT r FROM User r  WHERE r.username = ?1 and r.password = ?2")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private boolean isInsolvent;
	private boolean isEmployee;
	private String eMail;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
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
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", isInsolvent=" + isInsolvent +
				", isEmployee=" + isEmployee +
				", eMail='" + eMail + '\'' +
				", orders=" + orders +
				'}';
	}
}
