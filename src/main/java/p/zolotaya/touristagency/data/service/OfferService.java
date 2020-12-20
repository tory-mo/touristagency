package p.zolotaya.touristagency.data.service;


import p.zolotaya.touristagency.data.dao.DAOException;
import p.zolotaya.touristagency.data.entity.Offer;

import java.util.ArrayList;

public interface OfferService {
    ArrayList<Offer> getHotOffers() throws DAOException;
    ArrayList<Offer> getOffersByCity(String city) throws DAOException;
    ArrayList<Offer> getOfferList() throws DAOException;
    boolean addOffer(Offer offer) throws DAOException;
    boolean changeHotStatus(int idOffer, boolean isHot) throws DAOException;
    boolean updatePrice(int idOffer, int newPrice) throws DAOException;
    boolean removeOffer(Offer offer) throws DAOException;

}
