package main.it.polimi.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import main.it.polimi.entities.Service;

public class SrvService {

	@PersistenceContext(unitName = "Telco")
	private EntityManager em;
	
    public SrvService(EntityManager em) {
    	this.em = em;
    }
    
    public Service createService(int id, String title) {
        Service srv = new Service(id);
        srv.setTitle(title);
        em.persist(srv);
        return srv;
    }
    
    public Service findService(int id) {
        return em.find(Service.class, id);
    }
    
    public List<Service> findAllEmployees() {
        TypedQuery query = em.createQuery("SELECT s FROM Service s", Service.class);
        return query.getResultList();
    }
	
}
