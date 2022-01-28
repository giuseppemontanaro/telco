package it.polimi.model.services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import it.polimi.model.entities.Product;
import it.polimi.model.entities.ServicePackage;


public class SrvServicePackage {
	
	private EntityManager em;
	
	public SrvServicePackage(EntityManager em) {
		this.em = em;
	}

	
	public List<ServicePackage> findAllServicePackages() {
		List<ServicePackage> srvPackage = null;
		
		srvPackage = em.createNamedQuery("AllServicePackage", ServicePackage.class).getResultList();
		
		return srvPackage;
	}
	
	
	public List<String> findAllServicePackageNames() {
		List<String> srvPackage = null;
		
		srvPackage = em.createNamedQuery("AllServicePackageNames", String.class).getResultList();
		
		return srvPackage;
	}
	
	
	public ServicePackage addSrvPkg(int productid, int srvpkgid) {
		Product p = em.find(Product.class, productid);
		ServicePackage s = em.find(ServicePackage.class, srvpkgid);
		if (!s.getProducts().contains(p))
			s.addProduct(p);
		
		em.getTransaction().begin();

		em.persist(s);
		em.getTransaction().commit();
		return s;
	}
	
	
	public ServicePackage findPackage(String name) {
		ServicePackage srvpkg;
		srvpkg = em.createNamedQuery("GetPackage", ServicePackage.class).setParameter(1, name).getSingleResult();
		
		return srvpkg;
        	
	}
	
}
