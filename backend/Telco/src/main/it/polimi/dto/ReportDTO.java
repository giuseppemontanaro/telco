package main.it.polimi.dto;

import main.it.polimi.entities.Product;
import main.it.polimi.entities.Purchase;
import main.it.polimi.entities.User;

import java.util.List;

public class ReportDTO {

    List<ServiceReport>;
    List<InsolventUser> insolventUsers;
    List<SuspendedPurchase> suspendedOrders;
    List<Alert> alerts;
    Product bestSeller;

}
