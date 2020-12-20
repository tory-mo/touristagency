package p.zolotaya.touristagency.data.dao.impl;

import p.zolotaya.touristagency.data.DbConnection;
import p.zolotaya.touristagency.data.dao.DAOException;
import p.zolotaya.touristagency.data.dao.VoucherDAO;
import p.zolotaya.touristagency.data.entity.Offer;
import p.zolotaya.touristagency.data.entity.Tourist;
import p.zolotaya.touristagency.data.entity.Voucher;
import p.zolotaya.touristagency.data.entity.Voyage;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class VoucherDaoImpl implements VoucherDAO {



    enum SQLVoucher {
        GETWHEREvoucher("SELECT voucher.voucherID, voucher.voyageID, voucher.totalCost, voucher.isPaid, " +
                "voyage.voyageID, voyage.offerID, voyage.nights, voyage.touristID, voyage.datestart, " +
                "offer.offerID, offer.country, offer.city, offer.dayPrice, offer.isHot, " +
                "tourist.touristID, tourist.name, tourist.surname, tourist.passportId, tourist.isVisa " +
                "FROM voucher JOIN voyage ON voyage.voyageID = voucher.voyageID " +
                "JOIN offer ON offer.offerID = voyage.offerID " +
                "JOIN tourist ON tourist.touristID = voyage.touristID " +
                "WHERE voucher.voucherID = (?)"),
        GETWHEREtourist("SELECT voucher.voucherID, voucher.voyageID, voucher.totalCost, voucher.isPaid, " +
                "voyage.voyageID, voyage.offerID, voyage.nights, voyage.touristID, voyage.datestart, " +
                "offer.offerID, offer.country, offer.city, offer.dayPrice, offer.isHot, " +
                "tourist.touristID, tourist.name, tourist.surname, tourist.passportId, tourist.isVisa " +
                "FROM voucher JOIN voyage ON voyage.voyageID = voucher.voyageID " +
                "JOIN offer ON offer.offerID = voyage.offerID " +
                "JOIN tourist ON tourist.touristID = voyage.touristID " +
                "WHERE tourist.touristID = (?)"),
        INSERT("INSERT INTO voucher (voucherID, voyageID, totalCost, isPaid) VALUES (DEFAULT, (?), (?), (?)) RETURNING voucherID"),
        DELETE("DELETE FROM voucher WHERE voucherID = (?) RETURNING voucherID"),
        UPDATEcost("UPDATE voucher SET totalCost = (?) WHERE voucherID = (?) RETURNING voucherID"),
        UPDATEstatus("UPDATE voucher SET isPaid = (?) WHERE voucherID = (?) RETURNING voucherID"),
        JOINALLTABLES("SELECT voucher.voucherID, voucher.voyageID, voucher.totalCost, voucher.isPaid, " +
                "voyage.voyageID, voyage.offerID, voyage.nights, voyage.touristID, voyage.datestart, " +
                "offer.offerID, offer.country, offer.city, offer.dayPrice, offer.isHot, " +
                "tourist.touristID, tourist.name, tourist.surname, tourist.passportId, tourist.isVisa " +
                "FROM voucher JOIN voyage ON voyage.voyageID = voucher.voyageID " +
                "JOIN offer ON offer.offerID = voyage.offerID " +
                "JOIN tourist ON tourist.touristID = voyage.touristID");

        String QUERY;

        SQLVoucher(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    @Override
    public boolean createVoucher(int voyageID) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(VoucherDaoImpl.SQLVoucher.INSERT.QUERY)){
            statement.setInt(1, voyageID);
            statement.setInt(2, 0);
            statement.setBoolean(3, false);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateTotalCost(int idVoucher, int cost) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(VoucherDaoImpl.SQLVoucher.UPDATEcost.QUERY)){
            statement.setInt(1, cost);
            statement.setInt(2, idVoucher);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean changeVoucherStatus(int idVoucher, boolean status) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(VoucherDaoImpl.SQLVoucher.UPDATEstatus.QUERY)) {
            statement.setBoolean(1, status);
            statement.setInt(2, idVoucher);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean removeVoucherById(int idVoucher) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(VoucherDaoImpl.SQLVoucher.DELETE.QUERY)) {
            statement.setInt(1, idVoucher);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Voucher getVoucherById(int idVoucher) throws DAOException {
        Voucher result = new Voucher();
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(VoucherDaoImpl.SQLVoucher.GETWHEREvoucher.QUERY)) {
            statement.setInt(1, idVoucher);
            ResultSet rs = statement.executeQuery();
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
                        rs.getInt("dayPrice"),
                        rs.getBoolean("isHot"));
                anOffer.setId(rs.getInt("offerID"));

                Voyage aVoyage = new Voyage(
                        anOffer, Integer.parseInt(rs.getString("nights")),
                        aTourist, rs.getString("datestart"));
                aVoyage.setId(rs.getInt("voyageID"));

                result.setId(rs.getInt("voucherID"));
                result.setVoyage(aVoyage);
                result.setTotalCost(rs.getInt("totalCost"));
                result.setIsPaid(rs.getBoolean("isPaid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Voucher> getAllVouchers() throws DAOException {
        ArrayList<Voucher> results = new ArrayList<>();
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(VoucherDaoImpl.SQLVoucher.JOINALLTABLES.QUERY)) {
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

                Voucher aVoucher = new Voucher(
                        aVoyage, Integer.parseInt(rs.getString("totalCost")),
                        rs.getBoolean("isPaid"));
                aVoucher.setId(rs.getInt("voucherID"));
                results.add(aVoucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public ArrayList<Voucher> getVouchersByTouristID(int touristID) throws DAOException {
        ArrayList<Voucher> results = new ArrayList<>();
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(SQLVoucher.GETWHEREtourist.QUERY)) {
            statement.setInt(1, touristID);
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

                Voucher aVoucher = new Voucher(
                        aVoyage, Integer.parseInt(rs.getString("totalCost")),
                        rs.getBoolean("isPaid"));
                aVoucher.setId(rs.getInt("voucherID"));
                results.add(aVoucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

}
