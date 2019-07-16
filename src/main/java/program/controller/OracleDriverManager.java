package program.controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class OracleDriverManager {

    private static OracleDriverManager oracleDriverManager;

    private OracleDriverManager() {
    }

    public static OracleDriverManager getInstance() {
        if (oracleDriverManager == null) {
            oracleDriverManager = new OracleDriverManager();
            Locale.setDefault(Locale.ENGLISH);
        }
        return oracleDriverManager;
    }

    public Connection openOracleConnection() {
        try {
            Driver driver = createOracleDriver();
            DriverManager.registerDriver(driver);

            Connection connection = getConnection();
            return connection;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Driver createOracleDriver() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (Driver) Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:oracle:thin:@sql.edu-netcracker.com:1251:xe", "TLT_5", "TLT_5");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
