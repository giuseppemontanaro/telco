package it.polimi.model.services;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.model.entities.Purchase;
import it.polimi.model.entities.Product;
import it.polimi.model.entities.ServicePackage;
import it.polimi.model.entities.User;
import it.polimi.model.entities.ValidityPeriod;

public class OrderService {
	
	
	@PersistenceContext(unitName = "Telco")
	private EntityManager em;
	
	public OrderService(EntityManager em) {
		this.em = em;
	}
	
	public List<Purchase> findOrderByUserId(int userID) {
		User user = em.find(User.class, userID);
		List<Purchase> orders = user.getOrders();
		return orders;
	}
	
	public Purchase findOrder(int id) {
    	
        return em.find(Purchase.class, id);
    }
	
	
	public void CreateOrder(int ID, float total, String status, LocalDate subscription_date, LocalDate date, int servicepkg, int userid) {
		ServicePackage service_pkg = em.find(ServicePackage.class, servicepkg);
		User user = em.find(User.class, userid);
		
		Purchase order = new Purchase(ID, total, status, subscription_date, date, service_pkg, user);

		em.getTransaction().begin();

		em.persist(order);
		em.getTransaction().commit();
	}
	
	
	public Purchase addProduct(int productid, int orderid) {
		Product p = em.find(Product.class, productid);
		Purchase s = em.find(Purchase.class, orderid);
		if (!s.getProducts().contains(p))
			s.addProduct(p);
		
		em.getTransaction().begin();

		em.persist(s);
		em.getTransaction().commit();
		return s;
	}
	


}
