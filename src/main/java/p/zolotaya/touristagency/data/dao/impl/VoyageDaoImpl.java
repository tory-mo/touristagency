package p.zolotaya.touristagency.data.dao.impl;

import p.zolotaya.touristagency.data.DbConnection;
import p.zolotaya.touristagency.data.dao.DAOException;
import p.zolotaya.touristagency.data.dao.VoyageDAO;
import p.zolotaya.touristagency.data.entity.Offer;
import p.zolotaya.touristagency.data.entity.Tourist;
import p.zolotaya.touristagency.data.entity.Voyage;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class VoyageDaoImpl implements VoyageDAO {



    enum SQLVoyage {
        GETWHEREtourist("SELECT voyage.voyageID, voyage.offerID, voyage.nights, voyage.touristID, voyage.datestart, " +
                "offer.offerID, offer.country, offer.city, offer.dayPrice, offer.isHot, " +
                "tourist.touristID, tourist.name, tourist.surname, tourist.passportId, tourist.isVisa " +
                "FROM voyage JOIN offer ON offer.offerID = voyage.offerID " +
                "JOIN tourist ON tourist.touristID = voyage.touristID " +
                "WHERE tourist.touristID = (?)"),
        GETWHEREvoyage("SELECT voyage.voyageID, voyage.offerID, voyage.nights, voyage.touristID, voyage.datestart, " +
                "offer.offerID, offer.country, offer.city, offer.dayPrice, offer.isHot, " +
                "tourist.touristID, tourist.name, tourist.surname, tourist.passportId, tourist.isVisa " +
                "FROM voyage JOIN offer ON offer.offerID = voyage.offerID " +
                "JOIN tourist ON tourist.touristID = voyage.touristID " +
                "WHERE voyage.voyageID = (?)"),
        INSERT("INSERT INTO voyage (voyageID, offerID, nights, touristID, datestart) VALUES (DEFAULT, (?), (?), (?), (?)) RETURNING voyageID"),
        DELETE("DELETE FROM voyage WHERE voyageID = (?) RETURNING voyageID"),
        UPDATEDATE("UPDATE voyage SET datestart = (?) WHERE voyageID = (?) RETURNING voyageID"),
        JOINALLTABLES("SELECT voyage.voyageID, voyage.offerID, voyage.nights, voyage.touristID, voyage.datestart, " +
                "offer.offerID, offer.country, offer.city, offer.dayPrice, offer.isHot, " +
                "tourist.touristID, tourist.name, tourist.surname, tourist.passportId, tourist.isVisa " +
                "FROM voyage JOIN offer ON offer.offerID = voyage.offerID " +
                "JOIN tourist ON tourist.touristID = voyage.touristID");

        String QUERY;

        SQLVoyage(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    @Override
    public boolean createVoyage(Voyage voyage) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(VoyageDaoImpl.SQLVoyage.INSERT.QUERY)){
            statement.setInt(1, voyage.getOffer().getId());
            statement.setInt(2, voyage.getNights());
            statement.setInt(3, voyage.getTourist().getId());
            statement.setString(4, voyage.getDateStart());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean changeDate(int idVoyage, String newDate) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(VoyageDaoImpl.SQLVoyage.UPDATEDATE.QUERY)) {
            statement.setString(1, newDate);
            statement.setInt(2, idVoyage);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteVoyageById(int idVoyage) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(VoyageDaoImpl.SQLVoyage.DELETE.QUERY)) {
            statement.setInt(1, idVoyage);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Voyage> getAllVoyages() throws DAOException {
        ArrayList<Voyage> results = new ArrayList<>();
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(VoyageDaoImpl.SQLVoyage.JOINALLTABLES.QUERY)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Tourist aTourist = new Tourist(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("passportId"),
                        rs.getBoolean("isVisa"));
                aTourist.setId(rs.getInt("touristID"));

                Offer anOffer = new Offer(
                        rs.getString("country"),
                        rs.getString("city"),
                        Integer.parseInt(rs.getString("dayPrice")),
                        rs.getBoolean("isHot"));
                anOffer.setId(rs.getInt("offerID"));

                Voyage aVoyage = new Voyage(
                        anOffer, Integer.parseInt(rs.getString("nights")),
                        aTourist, rs.getString("datestart"));
                aVoyage.setId(rs.getInt("voyageID"));

                results.add(aVoyage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public ArrayList<Voyage> getVoyageOfTouristByID(int touristId) throws DAOException {
        ArrayList<Voyage> results = new ArrayList<>();
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(VoyageDaoImpl.SQLVoyage.GETWHEREtourist.QUERY)) {
            statement.setInt(1, touristId);
            final ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Tourist aTourist = new Tourist(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("passportId"),
                        rs.getBoolean("isVisa"));
                aTourist.setId(rs.getInt("touristID"));

                Offer anOffer = new Offer(
                        rs.getString("country"),
                        rs.getString("city"),
                        Integer.parseInt(rs.getString("dayPrice")),
                        rs.getBoolean("isHot"));
                anOffer.setId(rs.getInt("offerID"));

                Voyage aVoyage = new Voyage(
                        anOffer, Integer.parseInt(rs.getString("nights")),
                        aTourist, rs.getString("datestart"));
                aVoyage.setId(rs.getInt("voyageID"));

                results.add(aVoyage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public Voyage getVoyageById(int idVoyage) throws DAOException {
        Voyage result = new Voyage();
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(VoyageDaoImpl.SQLVoyage.GETWHEREvoyage.QUERY)) {
            statement.setInt(1, idVoyage);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Tourist aTourist = new Tourist(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("passportId"),
                        rs.getBoolean("isVisa"));
                aTourist.setId(rs.getInt("touristID"));

                Offer anOffer = new Offer(
                        rs.getString("country"),
                        rs.getString("city"),
                        Integer.parseInt(rs.getString("dayPrice")),
                        rs.getBoolean("isHot"));
                anOffer.setId(rs.getInt("offerID"));

                result.setOffer(anOffer);
                result.setTourist(aTourist);
                result.setNights(rs.getInt("nights"));
                result.setDateStart(rs.getString("datestart"));
                result.setId(rs.getInt("voyageID"));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
