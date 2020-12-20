package p.zolotaya.touristagency.data.dao.impl;


import p.zolotaya.touristagency.data.DbConnection;
import p.zolotaya.touristagency.data.dao.DAOException;
import p.zolotaya.touristagency.data.dao.TouristDAO;
import p.zolotaya.touristagency.data.entity.Tourist;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class TouristDaoImpl implements TouristDAO {




    enum SQLTourist {
        GET("SELECT t.touristID, t.name, t.surname, t.passportId, t.isVisa FROM tourist AS t WHERE t.surname = (?)"),
        INSERT("INSERT INTO tourist (touristID, name, surname, passportId, isVisa) VALUES (DEFAULT, (?), (?), (?), (?)) RETURNING touristID"),
        DELETE("DELETE FROM tourist WHERE name = (?) AND surname = (?) AND passportId = (?) AND isVisa = (?) RETURNING touristID"),
        GETLIST("SELECT * FROM tourist");
        String QUERY;

        SQLTourist(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    @Override
    public boolean addTourist(Tourist tourist) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try(PreparedStatement statement = connection.prepareStatement(TouristDaoImpl.SQLTourist.INSERT.QUERY)){
            statement.setString(1, tourist.getName());
            statement.setString(2, tourist.getSurname());
            statement.setString(3, tourist.getPassportId());
            statement.setBoolean(4, tourist.isVisa());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteTourist(Tourist tourist) throws DAOException {
        boolean result = false;
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(TouristDaoImpl.SQLTourist.DELETE.QUERY)) {
            statement.setString(1, tourist.getName());
            statement.setString(2, tourist.getSurname());
            statement.setString(3, tourist.getPassportId());
            statement.setBoolean(4, tourist.isVisa());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Tourist getTouristBySurname(String surname) throws DAOException {
        Tourist result = new Tourist();
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(TouristDaoImpl.SQLTourist.GET.QUERY)) {
            statement.setString(1, surname);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result.setId(Integer.parseInt(rs.getString("touristID")));
                result.setName(rs.getString("name"));
                result.setSurname(rs.getString("surname"));
                result.setPassportId(rs.getString("passportId"));
                result.setVisa(rs.getBoolean("isVisa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Tourist> getAllTourists() throws DAOException {
        ArrayList<Tourist> results = new ArrayList<>();
        Connection connection = DbConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(TouristDaoImpl.SQLTourist.GETLIST.QUERY)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Tourist aTourist = new Tourist(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("passportId"),
                        rs.getBoolean("isVisa"));
                aTourist.setId(rs.getInt("touristID"));
                results.add(aTourist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return results;
    }

}
