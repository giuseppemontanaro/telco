package main.it.polimi.services;

import main.it.polimi.dto.ReportDTO;
import main.it.polimi.entities.Alert;
import main.it.polimi.entities.BestSeller;
import main.it.polimi.entities.InsolventUser;
import main.it.polimi.entities.Product;
import main.it.polimi.entities.ServicePackage;
import main.it.polimi.entities.ServiceReport;
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
    	
    	List<ServiceReport> services = null;
    	services = em.createQuery("SELECT serv FROM ServiceReport", ServiceReport.class).getResultList();
    	
    	
    	Product best = em.createQuery("SELECT best FROM BestSeller", Product.class).getSingleResult();
    	
    	
    	ReportDTO r = new ReportDTO();
    	r.setInsolventUsers(insolventUsers);
    	r.setSuspendedOrders(suspendedPurchases);
    	r.setAlerts(alerts);
    	r.setReport(services);
    	r.setBestSeller(best);
    	
		return r;

    }
}
