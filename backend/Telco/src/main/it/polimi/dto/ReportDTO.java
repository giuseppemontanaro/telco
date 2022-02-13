package main.it.polimi.dto;

import main.it.polimi.entities.*;
import main.it.polimi.services.ReportService;

import java.util.List;

public class ReportDTO {

    List<ServiceReport> report;
    List<InsolventUser> insolventUsers;
    List<SuspendedPurchase> suspendedOrders; //add user e service field
    List<Alert> alerts;
    BestSeller bestSeller;
    
    
	public ReportDTO() {
	}


	public List<InsolventUser> getInsolventUsers() {
		return insolventUsers;
	}


	public void setInsolventUsers(List<InsolventUser> insolventUsers) {
		this.insolventUsers = insolventUsers;
	}


	public List<SuspendedPurchase> getSuspendedOrders() {
		return suspendedOrders;
	}


	public void setSuspendedOrders(List<SuspendedPurchase> suspendedOrders) {
		this.suspendedOrders = suspendedOrders;
	}


	public List<Alert> getAlerts() {
		return alerts;
	}


	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}


	public BestSeller getBestSeller() {
		return bestSeller;
	}


	public void setBestSeller(BestSeller bestSeller) {
		this.bestSeller = bestSeller;
	}


	public List<ServiceReport> getReport() {
		return report;
	}


	public void setReport(List<ServiceReport> report) {
		this.report = report;
	}


	@Override
	public String toString() {
		return "ReportDTO{" +
				"report=" + report +
				", insolventUsers=" + insolventUsers +
				", suspendedOrders=" + suspendedOrders +
				", alerts=" + alerts +
				", bestSeller=" + bestSeller +
				'}';
	}
}
