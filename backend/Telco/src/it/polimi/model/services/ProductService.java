package it.polimi.model.services;

import it.polimi.model.entities.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductService {

private EntityManager em;	
	
	
	public ProductService(EntityManager em) {
		this.em = em;
	}


	public List<Product> getAllOptionalProducts() {
		List<Product> list;
		list = em.createNamedQuery("AllProduct", Product.class).getResultList();
		return list;
	}

	public void addOptionalProducts(Product product) {
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
	}
}
