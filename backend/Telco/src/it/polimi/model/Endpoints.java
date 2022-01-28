package it.polimi.model;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.polimi.model.entities.Order;
import it.polimi.model.entities.Service;
import it.polimi.model.entities.ServicePackage;

import it.polimi.model.entities.User;
import it.polimi.model.exceptions.CredentialsException;
import it.polimi.model.exceptions.UserNotFound;
import it.polimi.model.services.OrderService;
import it.polimi.model.services.SrvService;
import it.polimi.model.services.SrvServicePackage;
import it.polimi.model.services.UserService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class Endpoints {
	
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Telco");	
			
			EntityManager em = emf.createEntityManager();
			SrvService service = new SrvService(em);
			OrderService rd = new OrderService(em);
			UserService usersrv = new UserService(em);
			SrvServicePackage srvpck = new SrvServicePackage(em);
			
			
			@RequestMapping(
				    value = "/auth/signUp", 
				    method = RequestMethod.POST)
			public void signUp(@RequestParam Map<String, String> payload){
				System.out.println("Created " + payload.get("test"));

				//User user = usersrv.createUser(body.getId(), body.getUsername(), body.getPassword(), false, body.isInsolvent(), body.geteMail());
				//System.out.println("Created " + user);
			}
			
			@PostMapping("/auth/login")		
			@ResponseBody
			public List<User> test(@RequestBody User user) {
				
				try {

					List<User> userLogin = usersrv.checkCredentials(user.getUsername(), user.getPassword());
					return (userLogin);
					
				} catch (NonUniqueResultException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CredentialsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UserNotFound e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;	 
				
			}
			
			
			@GetMapping("/package/details")
			public List<ServicePackage> packagesDetails() {
				List<ServicePackage> list;
				list = srvpck.findAllServicePackages();
				return list;
			}
			
			
			@GetMapping("/packages")
			public List<String> Packages() {
				List<String> list;
				list = srvpck.findAllServicePackageNames();
	            return list;

			} 
			
			
			
			
			
			
			
			
			//Service srv; 
			//srv = service.findService(2); 
			//System.out.println("Found " + srv.getTitle());
			
			//service.createService(2, "test2");

			// FUNZIONA RECUPERO ORDINI RELATIVI A USER ID
			//List<Order> ord;
			//ord = rd.findOrderByUserId(1); 
			//for (Order order : ord) {
			//	System.out.println("Found order " + order.getId());
			//}
			
			
			//CREAZIONE DI UN NUOVO ORDINE
			//LocalDate date = LocalDate.now();    
			//rd.CreateOrder(5, 10, "ok", date, date, 1, 1, 1);
			
			
		

			 // FUNZIONA RICERCA USER PER ID
			//User user = usersrv.findUser(1);
			//System.out.println("Found user " + user);
			
			// FUNZIONA RICERCA UTENTE PER USRN E PASSWD
				/*
				 * try { User user = usersrv.checkCredentials("test20", "test222");
				 * System.out.println("Credenziali ok per " + user);
				 * 
				 * } catch (NonUniqueResultException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); } catch (CredentialsException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); } catch (UserNotFound e) { //
				 * TODO Auto-generated catch block e.printStackTrace(); }
				 */

			
			
			/*
			*/
			
			
			/*ServicePackage srvp; 
			srvp = srvpck.findPackage("service2"); 
			System.out.println("Found " + srvp.getUser().getId()); //al di là del fatto che funziona, con quel sysout stampo id. Se tolgo getId mi ridà l'oggetto user
			*/
			
			
			//MODIFICA MANY_TO_MANY TABLE TRA SERVICEPACKAGE E PRODUCT
			/*ServicePackage srvp; 
			srvp = srvpck.addSrvPkg(3,2);
			System.out.println(srvp);
			*/

			


	

}

