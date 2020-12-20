package p.zolotaya.touristagency.data.service.impl;

import p.zolotaya.touristagency.data.dao.DAOException;
import p.zolotaya.touristagency.data.dao.VoyageDAO;
import p.zolotaya.touristagency.data.entity.Voyage;
import p.zolotaya.touristagency.data.service.VoyageService;

import java.util.ArrayList;

public class VoyageServiceImpl implements VoyageService {

    private VoyageDAO voyageDAO;
    public VoyageServiceImpl(VoyageDAO voyageDAO){
        this.voyageDAO = voyageDAO;
    }

    @Override
    public void setVoyage(Voyage voyage) {
        voyageDAO.createVoyage(voyage);
    }

    @Override
    public boolean changeDateOfVoyage(int idVoyage, String newDate) throws DAOException {
        return voyageDAO.changeDate(idVoyage, newDate);
    }

    @Override
    public void deleteVoyageById(int idVoyage) throws DAOException {
        voyageDAO.deleteVoyageById(idVoyage);
    }

    @Override
    public ArrayList<Voyage> getVoyageOfTouristByID(int touristId) throws DAOException {
        return voyageDAO.getVoyageOfTouristByID(touristId);
    }


}
