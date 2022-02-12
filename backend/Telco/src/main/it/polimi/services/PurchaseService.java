package main.it.polimi.services;

import main.it.polimi.dto.PurchaseDTO;
import main.it.polimi.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PurchaseService {
	
	
	@PersistenceContext(unitName = "Telco")
	private EntityManager em;
	
	public PurchaseService(EntityManager em) {
		this.em = em;
	}
	
	/*public List<Purchase> findOrderByUserId(int userID) {
		User user = em.find(User.class, userID);
		List<Purchase> orders = user.getOrders();
		return orders;
	}*/
	
	public Purchase findOrder(int id) {
    	
        return em.find(Purchase.class, id);
    }
	
	
	public void createOrder(PurchaseDTO purchaseDTO) {
		Purchase order = purchaseDTO.getPurchase();
		User user = em.find(User.class, purchaseDTO.getUser().getId());
		if(order.isRejected() == true) {
			user.setInsolvent(true);
		}
		ServicePackage s = em.createNamedQuery("GetPackage", ServicePackage.class).setParameter(1, purchaseDTO.getChosenPackage().getName()).getSingleResult();
		ValidityPeriod v = em.find(ValidityPeriod.class, purchaseDTO.getValidityPeriod().getID());
		
		List<Product> products = new ArrayList<Product>();
		
		
		for (Product elem : s.getProducts()) {
			
			products.add(em.createNamedQuery("GetProduct", Product.class).setParameter(1, elem.getName()).getSingleResult());
		}

		
		em.getTransaction().begin();
		order.setUser(user);
		order.setService_pkg(s);
		order.setValidityPeriod(v);
		order.setProducts(products);
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
		List<Purchase> query = em.createQuery("SELECT p FROM Purchase p WHERE p.user = '" + user.getId() + "' and p.isRejected = true", Purchase.class).getResultList();
		if (query == null) {
			List<Purchase> empty = Collections.<Purchase>emptyList();  
			return empty;
		}else {
			return query;

		}
		
	}
}
