package main.it.polimi.dto;

import main.it.polimi.entities.Purchase;

public class PurchaseDTO {

    Purchase purchase;
    boolean isRejected;
    int svpkgID;
    int user;

    public PurchaseDTO(Purchase purchase, boolean isRejected, int svpkgID, int user) {
        this.purchase = purchase;
        this.isRejected = isRejected;
        this.user = user;
        this.svpkgID = svpkgID;
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

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getSvpkgID() {
		return svpkgID;
	}

	public void setSvpkgID(int svpkgID) {
		this.svpkgID = svpkgID;
	}
	
	

    
}
