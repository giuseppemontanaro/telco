package it.polimi.model.dto;

import it.polimi.model.entities.Purchase;
import it.polimi.model.entities.User;

public class PurchaseDTO {

    Purchase purchase;
    boolean isRejected;
    User user;

    public PurchaseDTO(Purchase purchase, boolean isRejected, User user) {
        this.purchase = purchase;
        this.isRejected = isRejected;
        this.user = user;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public boolean isRejected() {
        return isRejected;
    }

    public void setRejected(boolean rejected) {
        isRejected = rejected;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
