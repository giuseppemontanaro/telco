package main.it.polimi.dto;

import main.it.polimi.entities.Purchase;
import main.it.polimi.entities.ServicePackage;
import main.it.polimi.entities.ValidityPeriod;

public class RejectedDTO {

    Purchase purchase;
    ServicePackage servicePackage;
    ValidityPeriod validityPeriod;

    public RejectedDTO(Purchase purchase, ServicePackage servicePackage, ValidityPeriod validityPeriod) {
        this.purchase = purchase;
        this.servicePackage = servicePackage;
        this.validityPeriod = validityPeriod;
    }

    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(ValidityPeriod validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public ServicePackage getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(ServicePackage servicePackage) {
        this.servicePackage = servicePackage;
    }
}
