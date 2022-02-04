package it.polimi.model;

import it.polimi.model.dto.PurchaseDTO;
import it.polimi.model.entities.*;
import it.polimi.model.services.*;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.polimi.model.exceptions.CredentialsException;
import it.polimi.model.exceptions.UserNotFound;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;


@RestController
public class Endpoints {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Telco");

    EntityManager em = emf.createEntityManager();
    SrvService srvService = new SrvService(em);
    PurchaseService orderService = new PurchaseService(em);
    UserService userService = new UserService(em);
    SrvServicePackage srvServicePackage = new SrvServicePackage(em);
    ProductService productService = new ProductService(em);

//    AUTH ENDPOINTS

    @PostMapping("/auth/signUp")
    @ResponseBody
    public void signUp(@RequestBody User user) {

        System.out.println(user.getId() + user.getUsername() + user.getPassword() + user.getIsEmployee() + user.getIsInsolvent() + user.geteMail());

        userService.createUser(user);
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public List<User> test(@RequestBody User user) {

        try {

            List<User> userLogin = userService.checkCredentials(user.getUsername(), user.getPassword());
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


//    PACKAGE ENDPOINTS


    @GetMapping("/packages")
    public List<String> getPackages() {
        List<String> list;
        list = srvServicePackage.findAllServicePackageNames();
        return list;
    }

    @GetMapping("/package/package")
    public ServicePackage getPackages(@RequestParam String packageName) {
        ServicePackage result;
        result = srvServicePackage.findPackage(packageName);
        return result;
    }

    @PostMapping("/package/add")
    @ResponseBody
    public void addPackage(@RequestBody ServicePackage servicePackage) {
        srvServicePackage.addServicePackage(servicePackage);
    }


//    ORDER ENDPOINTS

    @PostMapping("/order/create")
    @ResponseBody
    public void createOrder(@RequestBody PurchaseDTO purchaseDTO) {
        orderService.createOrder(purchaseDTO);
    }

    @PostMapping("/order/rejected")
    @ResponseBody
    public List<Purchase> getRejectedOrders(@RequestBody User user) {
        return orderService.getRejectedOrders(user);
    }


//    OPTIONALS ENDPOINTS

    @GetMapping("/optionalproducts")
    public List<Product> getOptionalProducts() {
        return productService.getAllOptionalProducts();
    }

    @PostMapping("/optionalproducts/add")
    public void addOptionalProducts(@RequestBody Product product) {
        productService.addOptionalProducts(product);
    }


    @GetMapping("/services")
    public List<Service> getServices() {
        return srvService.findAllEmployees();
    }
//      case url.endsWith('/salesreport') && method === 'GET':


}

