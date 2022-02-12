package main.it.polimi.dto;

import main.it.polimi.entities.Purchase;
import main.it.polimi.entities.ServicePackage;
import main.it.polimi.entities.User;
import main.it.polimi.entities.ValidityPeriod;

public class PurchaseDTO {

    Purchase purchase;
    ServicePackage chosenPackage;
    User user;
    ValidityPeriod validityPeriod;

    public PurchaseDTO(Purchase purchase, ServicePackage packageName, User user, ValidityPeriod validityPeriod) {
        this.purchase = purchase;
        this.chosenPackage = packageName;
        this.user = user;
        this.validityPeriod = validityPeriod;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    

    public ServicePackage getChosenPackage() {
		return chosenPackage;
	}

	public void setChosenPackage(ServicePackage chosenPackage) {
		this.chosenPackage = chosenPackage;
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
                ", packageName='" + chosenPackage + '\'' +
                ", user=" + user +
                ", validityPeriod=" + validityPeriod +
                '}';
    }
}
