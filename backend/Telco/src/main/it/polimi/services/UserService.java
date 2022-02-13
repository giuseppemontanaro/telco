package main.it.polimi.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;


import main.it.polimi.exceptions.CredentialsException;
import main.it.polimi.exceptions.UpdateProfileException;
import main.it.polimi.entities.User;
import main.it.polimi.exceptions.UserNotFound;


public class UserService {
	
	@PersistenceContext(unitName = "Telco")
	private EntityManager em;	
	
	
	
	public UserService(EntityManager em) {
		this.em = em;
	}
	
	
	public void createUser(User u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}
	
	
	public User findUser(int id) {
        return em.find(User.class, id);	
	}
	
	
	public List<User> checkCredentials(String usrn, String pwd) throws CredentialsException, CredentialsException, NonUniqueResultException, UserNotFound {
		List<User> uList = null;
		try {
			uList = em.createNamedQuery("checkCredentials", User.class).setParameter(1, usrn).setParameter(2, pwd)
					.getResultList();
		} catch (PersistenceException e) {
			throw new CredentialsException("Could not verify credentals");
		}
		if (uList.isEmpty()) {
			throw new UserNotFound("Nessun utente trovato");
		} else if (uList.size() == 1) {
			return uList;
		}
		throw new NonUniqueResultException("More than one user registered with same credentials");

	}
	
	
	public void updateProfile(User u) throws UpdateProfileException {
		try {
			em.merge(u);
		} catch (PersistenceException e) {
			throw new UpdateProfileException("Could not change profile");
		}
	}



}
