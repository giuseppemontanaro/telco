package it.polimi.model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Service {
	
	
	@Id
	private int ID;
	private String Title;
	
	public Service() {
		
	}
	
	public Service(int id) {
        this.ID = id;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

    
}
