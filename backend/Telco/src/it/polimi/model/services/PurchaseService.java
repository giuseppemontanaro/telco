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
		User user = em.find(User.class, purchaseDTO.getUser());
		ServicePackage s = em.find(ServicePackage.class, purchaseDTO.getSvpkgID());
		
		System.out.println( purchaseDTO.getSvpkgID());

		
		

		//			MANCA CONTROLLO SUL DUPLICATO SERVICEPACKAGE. CIOE SE C'è GIA, NON POSSO METTERE UN ALTRO USER ID
		
		
		em.getTransaction().begin();
		
		/*if (s.getUser()== null)
			em.createQuery(
			        "UPDATE ServicePackage s "
			        + "SET s.user = '" + purchaseDTO.getUser() + "' "
			        + "WHERE s.ID = '" + purchaseDTO.getSvpkgID() + "' ")
			        .executeUpdate();
			 */

		order.setUser(user);
		order.setService_pkg(s);
		
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
