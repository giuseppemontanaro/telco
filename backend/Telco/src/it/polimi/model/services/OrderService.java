package it.polimi.model.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.model.entities.Order;
import it.polimi.model.entities.User;

public class OrderService {
	
	
	@PersistenceContext(unitName = "Telco")
	private EntityManager em;
	
	public OrderService(EntityManager em) {
		this.em = em;
	}
	
	public Order findOrderByUserId(int userID) {
		User user = em.find(User.class, userID);
		return em.find(Order.class, user.getId());
	}
	
	public Order findOrder(int id) {
    	
        return em.find(Order.class, id);
    }
	
	


}
