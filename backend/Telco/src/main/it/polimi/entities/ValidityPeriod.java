package main.it.polimi.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "validity_period", schema = "Telco")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ValidityPeriod implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	private int month_number;
	private int monthly_fee;

	
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

	@Override
	public String toString() {
		return "ValidityPeriod{" +
				"ID=" + ID +
				", month_number=" + month_number +
				", monthly_fee=" + monthly_fee +
				'}';
	}
}
