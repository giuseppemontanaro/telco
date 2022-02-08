package it.polimi.model.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "validity_period", schema = "Telco")

public class ValidityPeriod {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	private int month_number;
	private int monthly_fee;
	
	/*@JsonBackReference
	//Eager because we load the period and it's useful to get immediately the package related to
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.REFRESH })
		@JoinColumn(name="package_id")
		private ServicePackage srvpackage;
	*/
	@JsonBackReference
	@ManyToMany(mappedBy="periods")
	private Collection<ServicePackage> servicePackages;
	
	
	public ValidityPeriod() {
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public int getMonth_number() {
		return month_number;
	}


	public void setMonth_number(int month_number) {
		this.month_number = month_number;
	}


	public int getMonthly_fee() {
		return monthly_fee;
	}


	public void setMonthly_fee(int monthly_fee) {
		this.monthly_fee = monthly_fee;
	}


	public Collection<ServicePackage> getServicePackages() {
		return servicePackages;
	}


	public void setServicePackages(Collection<ServicePackage> servicePackages) {
		this.servicePackages = servicePackages;
	}


	/*public ServicePackage getSrvpackage() {
		return srvpackage;
	}


	public void setSrvpackage(ServicePackage srvpackage) {
		this.srvpackage = srvpackage;
	}
*/
	
	
	
	
	
	
	
}
