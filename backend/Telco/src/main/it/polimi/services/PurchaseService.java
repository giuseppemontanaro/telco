package main.it.polimi.services;

import main.it.polimi.dto.PurchaseDTO;
import main.it.polimi.dto.RejectedDTO;
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
		Purchase prevOrder = null;
		prevOrder = em.find(Purchase.class, purchaseDTO.getOrderId());
		if (prevOrder != null && !order.isRejected()) {
			prevOrder.setIsRejected(false);
			em.merge(order);
			return;
		}

		User user = em.find(User.class, purchaseDTO.getUser().getId());
		if(order.isRejected()) {
			user.setInsolvent(true);
			em.merge(user);
		}
		ServicePackage s = em.createNamedQuery("GetPackage", ServicePackage.class).setParameter(1, purchaseDTO.getChosenPackage().getName()).getSingleResult();
		ValidityPeriod v = em.find(ValidityPeriod.class, purchaseDTO.getValidityPeriod().getID());
		
		List<Product> products = new ArrayList<>();
		for (Product elem : purchaseDTO.getChosenPackage().getProducts()) {
			Product product = em.createNamedQuery("GetProduct", Product.class).setParameter(1, elem.getName()).getSingleResult();
			products.add(product);
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

	public List<RejectedDTO> getRejectedOrders(User user) {
		List<Purchase> result = em.createQuery("SELECT p FROM Purchase p JOIN FETCH p.validityPeriod v WHERE p.user = " + user.getId() + " and p.isRejected = true", Purchase.class).getResultList();
		if (result == null) {
			List<RejectedDTO> empty = Collections.<RejectedDTO>emptyList();
			return empty;
		}

		List<RejectedDTO> rejectedDTOList = new ArrayList<>();
		for (Purchase purchase : result) {
			ServicePackage servicePackage = em.createQuery("SELECT s FROM ServicePackage s, Purchase p WHERE p.id = " + purchase.getID() + " and p.service_pkg = s.id", ServicePackage.class).getSingleResult();
			ValidityPeriod validityPeriod = em.createQuery("SELECT v FROM ValidityPeriod v, Purchase p WHERE p.id = " + purchase.getID() + " and p.validityPeriod = v.id", ValidityPeriod.class).getSingleResult();
			RejectedDTO rejectedDTO = new RejectedDTO(purchase, servicePackage, validityPeriod);
			rejectedDTOList.add((rejectedDTO));
		}
		return rejectedDTOList;
	}
}
