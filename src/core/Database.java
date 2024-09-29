package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    //Singleton desIng pattern
    // sabıtlerın tutumu bu tasarımda tutorial
    private static Database instance= null;
    private  Connection connection =null;
    private final String DB_URL = "jdbc:mysql://localhost:3306/customermanage";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "appserv4646";

    private Database() {

        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    // instance tanımlıysa connectionu dönderir.
    private Connection getConnection() {
        return connection;
    }

    // public class sayesinde private class dışardan belirli miktar erişilir.
    public static Connection getInstance() {
        try {
            if(instance == null || getInstance().isClosed()) {
                instance = new Database();    // instance database tanımlanır.(dolu olarak işaretlenir)
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return instance.getConnection();
    }


}
