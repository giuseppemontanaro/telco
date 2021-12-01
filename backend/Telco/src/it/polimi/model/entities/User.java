package it.polimi.model.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "User", schema = "Telco")

public class User{
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)		//To generate automatically primary keys
	private int id;
	private String Username;
	private String Password;
	private boolean isInsolvent;
	private boolean isEmployee;
	private String eMail;
	
	// Unidirectional many-to-one association to Mission
			/*			COPIEDDDDDDDDDDDDDDDDDDDDDD
			 	* Fetch type EAGER allows resorting the relationship list content also in the
			 	* client Web servlet after the creation of a new mission. If you leave the
				* default LAZY policy, the relationship is sorted only at the first access but
				* then adding a new mission does not trigger the reloading of data from the
				* database and thus the sort method in the client does not actually re-sort the
				* list of missions. MERGE is not cascaded because we will modify and merge only
				* username and surname attributes of the user and do not want to cascade
				* detached changes to relationship.
			*/
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
	@JoinColumn(name="orderid")
	private Order order;

	public User() {
	}


	public User(int id) {
		this.id = id;
		
		// TODO Auto-generated constructor stub
	}
	


	public User(int id, String username, String password, boolean isInsolvent, boolean isEmployee, String email, Order order) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.Username = username;
		this.Password = password;
		this.isInsolvent = isInsolvent;
		this.isEmployee = isEmployee;
		this.eMail = email;
		this.order = order;
		
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
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




	public boolean isInsolvent() {
		return isInsolvent;
	}


	public void setInsolvent(boolean isInsolvent) {
		this.isInsolvent = isInsolvent;
	}


	public boolean isEmployee() {
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
	
	
	
	
	

}
