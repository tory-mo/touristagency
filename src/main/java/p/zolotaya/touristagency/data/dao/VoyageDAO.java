package p.zolotaya.touristagency.data.dao;

import p.zolotaya.touristagency.data.entity.Voyage;

import java.util.ArrayList;

public interface VoyageDAO {

    boolean createVoyage(Voyage voyage) throws DAOException;
    boolean changeDate(int idVoyage, String newDate) throws DAOException;
    boolean deleteVoyageById(int idVoyage) throws DAOException;
    ArrayList<Voyage> getVoyageOfTouristByID(int touristId) throws DAOException;
    ArrayList<Voyage> getAllVoyages() throws DAOException;
    Voyage getVoyageById(int idVoyage) throws DAOException;

}
