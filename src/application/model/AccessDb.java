package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessDb {
    static {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection connection = null;

    public static Connection getConnection() {

        //wenn connnection noch nicht befüllt wurde (Connection == null)
        //da es sich um eine statische Variable handelt, ist dieses
        //in allen
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:ucanaccess://D://Schule//itp//2020-21/Ticketsystem_Rama.accdb");
            } catch (SQLException throwables) {

            }
        }
        return connection;
    }
}
