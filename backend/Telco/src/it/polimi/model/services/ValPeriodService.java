package it.polimi.model.services;

import javax.persistence.EntityManager;

import it.polimi.model.entities.ValidityPeriod;

public class ValPeriodService {
	
	
	private EntityManager em;	
	
	
	public ValPeriodService(EntityManager em) {
		this.em = em;
	}
	
	public ValidityPeriod findValPeriodById(int id) {
        return em.find(ValidityPeriod.class, id);	
	}

	
}
