package main.it.polimi.services;

import javax.persistence.EntityManager;

import main.it.polimi.entities.ValidityPeriod;

public class ValPeriodService {
	
	
	private EntityManager em;	
	
	
	public ValPeriodService(EntityManager em) {
		this.em = em;
	}
	
	public ValidityPeriod findValPeriodById(int id) {
        return em.find(ValidityPeriod.class, id);	
	}
	
	
	public void addValidityPeriod(ValidityPeriod v) {
		
		em.getTransaction().begin();

		em.persist(v);
		em.getTransaction().commit();
		
	}

	
}
