package p.zolotaya.touristagency.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    private static Connection connection;
    public static final String USER = "postgres";
    public static final String PASSWORD = "qwerty1!";
    public static final String URL = "jdbc:postgresql://localhost:5432/touristagency";

    public static Connection getInstance(){
        if(connection == null)
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        return connection;
    }
}
