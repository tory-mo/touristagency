package p.zolotaya.touristagency.data.dao.impl;



import p.zolotaya.touristagency.data.DbConnection;
import p.zolotaya.touristagency.data.dao.DAOException;
import p.zolotaya.touristagency.data.dao.OfferDAO;
import p.zolotaya.touristagency.data.entity.Offer;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class OfferDaoImpl implements OfferDAO {



    enum SQLOffer {
        INSERT("INSERT INTO offer (offerID, country, city, dayPrice, isHot) VALUES (DEFAULT, (?), (?), (?), (?)) RETURNING offerID"),
        DELETE("DELETE FROM offer WHERE country = (?) AND city = (?) AND dayPrice = (?) AND isHot = (?) RETURNING offerID"),
        GETLIST("SELECT * FROM offer"),
        GETLISTWHERE("SELECT * FROM offer WHERE city = (?)"),
        UPDATEhot("UPDATE offer SET isHot = (?) WHERE offerID = (?) RETURNING offerID"),
        UPDATEprice("UPDATE offer SET dayPrice = (?) WHERE offerID = (?) RETURNING offerID");

        String QUERY;

        SQLOffer(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    @Override
    public ArrayList<Offer> getOfferList() throws DAOException {
        ArrayList<Offer> results = new ArrayList<>();
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(OfferDaoImpl.SQLOffer.GETLIST.QUERY)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Offer anOffer = new Offer(
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getInt("dayPrice"),
                        rs.getBoolean("isHot"));
                anOffer.setId(rs.getInt("offerID"));
                results.add(anOffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public boolean createOffer(@NotNull final Offer offer) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(OfferDaoImpl.SQLOffer.INSERT.QUERY)){
            statement.setString(1, offer.getCountry());
            statement.setString(2, offer.getCity());
            statement.setInt(3, offer.getDayPrice());
            statement.setBoolean(4, offer.getIsHot());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean changeHotStatus(int idOffer, boolean isHot) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(OfferDaoImpl.SQLOffer.UPDATEhot.QUERY)) {
            statement.setBoolean(1, isHot);
            statement.setInt(2, idOffer);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updatePrice(int idOffer, int newPrice) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(OfferDaoImpl.SQLOffer.UPDATEprice.QUERY)) {
            statement.setInt(1, newPrice);
            statement.setInt(2, idOffer);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean removeOffer(Offer offer) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(OfferDaoImpl.SQLOffer.DELETE.QUERY)) {
            statement.setString(1, offer.getCountry());
            statement.setString(2, offer.getCity());
            statement.setInt(3, offer.getDayPrice());
            statement.setBoolean(4, offer.getIsHot());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Offer> getOffersByCity(String city) throws DAOException {
        ArrayList<Offer> results = new ArrayList<>();
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(OfferDaoImpl.SQLOffer.GETLISTWHERE.QUERY)) {
            statement.setString(1, city);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Offer anOffer = new Offer(
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getInt("dayPrice"),
                        rs.getBoolean("isHot"));
                anOffer.setId(rs.getInt("offerID"));
                results.add(anOffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

}
