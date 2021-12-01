package it.polimi.model.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;


import it.polimi.model.entities.Order;
import it.polimi.model.entities.User;
import it.polimi.model.exceptions.CredentialsException;
import it.polimi.model.exceptions.UpdateProfileException;


public class UserService {
	
	@PersistenceContext(unitName = "Telco")
	private EntityManager em;	
	
	
	
	public UserService(EntityManager em) {
		this.em = em;
	}
	
	
	public User createUser(int id, String username, String password, boolean isInsolvent, boolean isEmployee, String email, int order_id) {
		Order order = em.find(Order.class, order_id);
		System.out.println("Is object managed?  " + em.contains(order));

		User user = new User(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setInsolvent(isInsolvent);
		user.setEmployee(isEmployee);
		user.seteMail(email);
		user.setOrder(order);
		em.getTransaction().begin();

		em.persist(user);
		em.getTransaction().commit();
		//em.flush();
		

		return user;
		
	}
	
	
	public User findUser(int id) {
		
        return em.find(User.class, id);
		
	}
	
	
	public User checkCredentials(String usrn, String pwd) throws CredentialsException, CredentialsException, NonUniqueResultException {
		List<User> uList = null;
		try {
			uList = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, usrn).setParameter(2, pwd)
					.getResultList();
		} catch (PersistenceException e) {
			throw new CredentialsException("Could not verify credentals");
		
		}
		if (uList.isEmpty())
			return null;
		else if (uList.size() == 1)
			return uList.get(0);
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
