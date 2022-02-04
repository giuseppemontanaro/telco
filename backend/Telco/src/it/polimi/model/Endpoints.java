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

import it.polimi.model.entities.Purchase;
import it.polimi.model.entities.Service;
import it.polimi.model.entities.ServicePackage;

import it.polimi.model.entities.User;
import it.polimi.model.exceptions.CredentialsException;
import it.polimi.model.exceptions.UserNotFound;
import it.polimi.model.services.PurchaseService;
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
			PurchaseService rd = new PurchaseService(em);
			UserService usersrv = new UserService(em);
			SrvServicePackage srvpck = new SrvServicePackage(em);
			
			
			@PostMapping("/auth/signUp")		
			@ResponseBody
			public void signUp(@RequestBody User user){

				usersrv.createUser(user);
				System.out.println("Created");
			}
						
			
			@PostMapping("/auth/createOrder")		
			@ResponseBody
			public void newOrder(){
				LocalDate date = LocalDate.now();    

				rd.CreateOrder(100, 100, "ok",  date, date , 2, 1000);
			}
					
			
			@PostMapping("/auth/login")		
			@ResponseBody
			public List<User> login(@RequestBody User user) {
				
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
	

}

