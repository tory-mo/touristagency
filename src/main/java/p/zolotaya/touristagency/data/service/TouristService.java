package p.zolotaya.touristagency.data.service;


import p.zolotaya.touristagency.data.dao.DAOException;
import p.zolotaya.touristagency.data.entity.Tourist;

import java.util.ArrayList;

public interface TouristService {

    void registerTourist(Tourist tourist);
    void deleteTourist(Tourist tourist) throws DAOException;
    ArrayList<Tourist> getAllTourists() throws DAOException;
    Tourist getTouristBySurname(String surname) throws DAOException;
}
