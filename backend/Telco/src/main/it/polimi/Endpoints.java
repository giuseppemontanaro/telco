package main.it.polimi;

import main.it.polimi.dto.PurchaseDTO;
import main.it.polimi.dto.ReportDTO;
import main.it.polimi.entities.*;
import main.it.polimi.exceptions.CredentialsException;
import main.it.polimi.exceptions.UserNotFound;
import main.it.polimi.services.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class Endpoints {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Telco");

    EntityManager em = emf.createEntityManager();
    SrvService srvService = new SrvService(em);
    PurchaseService orderService = new PurchaseService(em);
    UserService userService = new UserService(em);
    SrvServicePackage srvServicePackage = new SrvServicePackage(em);
    ProductService productService = new ProductService(em);
    ReportService reportService = new ReportService(em);

//    AUTH ENDPOINTS

    @PostMapping("/auth/signUp")
    @ResponseBody
    public void signUp(@RequestBody User user) {

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
    public List<ServicePackage> getPackages() {
        List<ServicePackage> list;
        list = srvServicePackage.findAllServicePackages();
        return list;
    }

    @GetMapping("/packages/package")
    public ServicePackage getPackages(@RequestParam String packageName) {
        ServicePackage result;
        result = srvServicePackage.findPackage(packageName);
        return result;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, value = "/packages/add")
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

    @PostMapping("/order/rejected")				// NON C'E' COME CAMPO, è STATUS??????
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
        return srvService.findAllServices();
    }

    /*@GetMapping("/salesreport")
    public ReportDTO getReport() {
        return reportService.getReport();
    }*/
	
}

