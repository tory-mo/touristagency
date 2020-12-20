package p.zolotaya.touristagency.data.service;

import p.zolotaya.touristagency.data.dao.DAOException;
import p.zolotaya.touristagency.data.entity.Voyage;

import java.util.ArrayList;

public interface VoyageService {

    void setVoyage(Voyage voyage) throws DAOException;
    boolean changeDateOfVoyage(int idVoyage, String newDate) throws DAOException;
    void deleteVoyageById(int idVoyage) throws DAOException;
    ArrayList<Voyage> getVoyageOfTouristByID(int touristId) throws DAOException;

}
