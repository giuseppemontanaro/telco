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
    	insolventUsers = em.createQuery("SELECT i FROM InsolventUser i", InsolventUser.class).getResultList();
    	
    	List<SuspendedPurchase> suspendedPurchases = null;
    	suspendedPurchases = em.createQuery("SELECT s FROM SuspendedPurchase s", SuspendedPurchase.class).getResultList();
    	
    	List<Alert> alerts = null;
    	alerts = em.createQuery("SELECT a FROM Alert a", Alert.class).getResultList();
    	
    	List<ServiceReport> services = null;
    	services = em.createQuery("SELECT serv FROM ServiceReport serv", ServiceReport.class).getResultList();


		BestSeller best = em.createQuery("SELECT best FROM BestSeller best", BestSeller.class).getSingleResult();
    	
    	
    	ReportDTO r = new ReportDTO();
    	r.setInsolventUsers(insolventUsers);
    	r.setSuspendedOrders(suspendedPurchases);
    	r.setAlerts(alerts);
    	r.setReport(services);
    	r.setBestSeller(best);
		System.out.println(r);
		return r;

    }
}
