package it.polimi.model.services;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.model.entities.Order;
import it.polimi.model.entities.ServicePackage;
import it.polimi.model.entities.User;
import it.polimi.model.entities.ValidityPeriod;

public class OrderService {
	
	
	@PersistenceContext(unitName = "Telco")
	private EntityManager em;
	
	public OrderService(EntityManager em) {
		this.em = em;
	}
	
	public List<Order> findOrderByUserId(int userID) {
		User user = em.find(User.class, userID);
		List<Order> orders = user.getOrders();
		return orders;
	}
	
	public Order findOrder(int id) {
    	
        return em.find(Order.class, id);
    }
	
	
	public void CreateOrder(int ID, float total, String status, LocalDate subscription_date, LocalDate date, int valperiod, int servicepkg, int userid) {
		ServicePackage service_pkg = em.find(ServicePackage.class, servicepkg);
		User user = em.find(User.class, userid);
		ValidityPeriod val_period = em.find(ValidityPeriod.class, valperiod);
		
		Order order = new Order(ID, total, status, subscription_date, date, val_period, service_pkg, user);

		em.getTransaction().begin();

		em.persist(order);
		em.getTransaction().commit();
		
		
		
		
		
		
		
		
		
	}
	
	

	
	
	
	
	
	
	
	

}
