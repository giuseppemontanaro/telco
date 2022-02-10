package main.it.polimi.services;

import main.it.polimi.dto.PurchaseDTO;
import main.it.polimi.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
		ServicePackage s = em.createNamedQuery("GetPackage", ServicePackage.class).setParameter(1, purchaseDTO.getPackageName()).getSingleResult();
		ValidityPeriod v = em.find(ValidityPeriod.class, purchaseDTO.getValidityPeriod().getID());

		em.getTransaction().begin();
		order.setUser(user);
		order.setService_pkg(s);
		order.setValidityPeriod(v);
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
		TypedQuery query = em.createQuery("SELECT p FROM Purchase p WHERE p.user = '" + user.getId() + "' and p.isRejected = true", Purchase.class);
		return query.getResultList();
	}
}
