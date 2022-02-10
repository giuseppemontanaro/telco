package main.it.polimi.services;

import main.it.polimi.dto.ReportDTO;

import javax.persistence.EntityManager;

public class ReportService {

    private EntityManager em;

    public ReportService(EntityManager em) {
        this.em = em;
    }

    public ReportDTO getReport() {

    }
}
