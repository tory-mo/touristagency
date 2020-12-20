package p.zolotaya.touristagency.data.dao;



import p.zolotaya.touristagency.data.entity.Tourist;

import java.util.ArrayList;

public interface TouristDAO {

    boolean addTourist(Tourist tourist) throws DAOException;
    boolean deleteTourist(Tourist tourist) throws DAOException;
    ArrayList<Tourist> getAllTourists() throws DAOException;
    Tourist getTouristBySurname(String surname) throws DAOException;

}
