package main.it.polimi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="service_package_view")
public class ServiceReport {
	
	@Id
	private int service_pkg_id;
	private int total;
	private int total_val_per_12;
	private int total_val_per_24;
	private int total_val_per_36;
	private float sales_pkg_only;
	private float sales_with_opt_prods;
	private float avg_prods;
	
	
	public ServiceReport() {
		
	}


	public int getService_pkg_id() {
		return service_pkg_id;
	}


	public void setService_pkg_id(int service_pkg_id) {
		this.service_pkg_id = service_pkg_id;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public int getTotal_val_per_12() {
		return total_val_per_12;
	}


	public void setTotal_val_per_12(int total_val_per_12) {
		this.total_val_per_12 = total_val_per_12;
	}


	public int getTotal_val_per_24() {
		return total_val_per_24;
	}


	public void setTotal_val_per_24(int total_val_per_24) {
		this.total_val_per_24 = total_val_per_24;
	}


	public int getTotal_val_per_36() {
		return total_val_per_36;
	}


	public void setTotal_val_per_36(int total_val_per_36) {
		this.total_val_per_36 = total_val_per_36;
	}


	public float getSales_pkg_only() {
		return sales_pkg_only;
	}


	public void setSales_pkg_only(float sales_pkg_only) {
		this.sales_pkg_only = sales_pkg_only;
	}


	public float getSales_with_opt_prods() {
		return sales_with_opt_prods;
	}


	public void setSales_with_opt_prods(float sales_with_opt_prods) {
		this.sales_with_opt_prods = sales_with_opt_prods;
	}


	public float getAvg_prods() {
		return avg_prods;
	}


	public void setAvg_prods(float avg_prods) {
		this.avg_prods = avg_prods;
	}
	
	
	
	

}
