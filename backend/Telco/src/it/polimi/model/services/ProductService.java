package it.polimi.model.services;

import javax.persistence.EntityManager;

public class ProductService {

private EntityManager em;	
	
	
	public ProductService(EntityManager em) {
		this.em = em;
	}
	
	
	
	
}
