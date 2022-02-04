package it.polimi.model;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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


@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        // TODO Auto-generated method stub


        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("Telco");
        SpringApplication.run(Launcher.class, args);
        Endpoints server = new Endpoints();
        //EntityManager em = emf.createEntityManager();
		/*
		SrvService service = new SrvService(em);
		
		UserService usersrv = new UserService(em);
		
		*/
        //OrderService rd = new OrderService(em);
        //SrvServicePackage srvpck = new SrvServicePackage(em);


        //OKOKOKOKOK
        /*
         * Service srv;
         * srv = service.findService(2);
         * System.out.println("Found " + srv.getTitle());*/

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


        // FUNZIONA CREAZIONE USER
        //User user = usersrv.createUser(401, "test401", "test31", true, true, "test401@mail.it");
        //System.out.println("Created " + user);

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

		
		
		/*List<ServicePackage> list;
		list = srvpck.findAllServicePackages();
		for (ServicePackage pkg : list) {
			System.out.println("Found service " + pkg.getName());
		}
		*/
		
		
		/*ServicePackage srvp; 
		srvp = srvpck.findPackage("service2"); 
		System.out.println("Found " + srvp.getUser().getId()); //al di là del fatto che funziona, con quel sysout stampo id. Se tolgo getId mi ridà l'oggetto user
		*/


        //MODIFICA MANY_TO_MANY TABLE
		/*ServicePackage srvp; 
		srvp = srvpck.addSrvPkg(3,2);
		System.out.println(srvp);
		*/


        //Order r;
        //r = rd.addOrder(1,3);
        //System.out.println(r);


        //ServicePackage s;
        //s = srvpck.addService(2, 2);
        //System.out.println(s);

        //ServicePackage s;
        //s = srvpck.addPeriod(1, 2);
        //System.out.println(s);

    }


}
