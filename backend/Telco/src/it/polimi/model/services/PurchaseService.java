package it.polimi.model.services;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.model.dto.PurchaseDTO;
import it.polimi.model.entities.*;

public class PurchaseService {
	
	
	@PersistenceContext(unitName = "Telco")
	private EntityManager em;
	
	public PurchaseService(EntityManager em) {
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
	
	
	public void createOrder(PurchaseDTO purchaseDTO) {
		Purchase order = purchaseDTO.getPurchase();
		order.setUser(purchaseDTO.getUser());
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


	public List<Purchase> getRejectedOrders(User user) {
		TypedQuery query = em.createQuery("SELECT e FROM purchase p, user u WHERE p.fk_user = u.id and p.isRejected = true", Purchase.class);
		return query.getResultList();
	}
}
