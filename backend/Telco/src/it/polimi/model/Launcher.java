package it.polimi.model;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//This is the client that interacts with the entity manager --> it is not properly a launcher


import it.polimi.model.entities.User;
import it.polimi.model.services.OrderService;
import it.polimi.model.services.SrvService;
import it.polimi.model.services.UserService;

public class Launcher{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Telco");	
		EntityManager em = emf.createEntityManager();
		SrvService service = new SrvService(em);
		OrderService rd = new OrderService(em);
		UserService usersrv = new UserService(em);
		
		
		
		//OKOKOKOKOK
		//Service srv; 
		//srv = service.findService(1021); 
		//System.out.println("Found " + srv.getTitle());
		
		//service.createService(2, "test2");

		//OKOKOKOKO
		//Order ord;
		//ord = rd.findOrderByUserId(1); 
		//System.out.println("Found order " + ord.getId());
		
		
		
		User user = usersrv.createUser(40, "test40", "test3", true, true, "test40@mail.it", 2);
		System.out.println("Created " + user);

		 //OKOKOKOKOK
		//User user = usersrv.findUser(1);
		//System.out.println("Found user " + user.geteMail());
		

		
		
	}
	
	

}
