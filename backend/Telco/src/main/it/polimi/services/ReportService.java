package main.it.polimi.services;

import main.it.polimi.dto.ReportDTO;
import main.it.polimi.entities.Alert;
import main.it.polimi.entities.InsolventUser;
import main.it.polimi.entities.ServicePackage;
import main.it.polimi.entities.SuspendedPurchase;

import java.util.List;

import javax.persistence.EntityManager;

public class ReportService {

    private EntityManager em;

    public ReportService(EntityManager em) {
        this.em = em;
    }

    public ReportDTO getReport() {
    	List<InsolventUser> insolventUsers = null;
    	insolventUsers = em.createQuery("SELECT i FROM InsolventUser", InsolventUser.class).getResultList();
    	
    	List<SuspendedPurchase> suspendedPurchases = null;
    	suspendedPurchases = em.createQuery("SELECT s FROM SuspendedPurchase", SuspendedPurchase.class).getResultList();
    	
    	List<Alert> alerts = null;
    	alerts = em.createQuery("SELECT a FROM Alerts", Alert.class).getResultList();
    	
    	
    	ReportDTO r = new ReportDTO();
    	r.setInsolventUsers(insolventUsers);
    	r.setSuspendedOrders(suspendedPurchases);
    	r.setAlerts(alerts);
    	
		return r;

    }
}
