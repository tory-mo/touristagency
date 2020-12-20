package p.zolotaya.touristagency.data.dao;

import p.zolotaya.touristagency.data.entity.Offer;

import java.util.ArrayList;

public interface OfferDAO {

    boolean createOffer(Offer offer) throws DAOException;
    boolean changeHotStatus(int idOffer, boolean isHot) throws DAOException;
    boolean updatePrice(int idOffer, int newPrice) throws DAOException;
    boolean removeOffer(Offer offer) throws DAOException;
    ArrayList<Offer> getOffersByCity(String city) throws DAOException;
    ArrayList<Offer> getOfferList() throws DAOException;
}
