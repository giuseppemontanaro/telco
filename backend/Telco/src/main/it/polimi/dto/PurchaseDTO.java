package main.it.polimi.dto;

import main.it.polimi.entities.Purchase;
import main.it.polimi.entities.User;
import main.it.polimi.entities.ValidityPeriod;

public class PurchaseDTO {

    Purchase purchase;
    String packageName;
    User user;
    ValidityPeriod validityPeriod;

    public PurchaseDTO(Purchase purchase, String packageName, User user, ValidityPeriod validityPeriod) {
        this.purchase = purchase;
        this.packageName = packageName;
        this.user = user;
        this.validityPeriod = validityPeriod;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(ValidityPeriod validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    @Override
    public String toString() {
        return "PurchaseDTO{" +
                "purchase=" + purchase +
                ", packageName='" + packageName + '\'' +
                ", user=" + user +
                ", validityPeriod=" + validityPeriod +
                '}';
    }
}
