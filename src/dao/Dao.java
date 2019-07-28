package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dao {

    private final String URL = "jdbc:mysql://localhost:3306/personal_project?serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASS = "therootisonfire";
    protected Connection conn;
    
    protected Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    protected void closeConnections(ResultSet rs, Statement st) {
        try {
            rs.close();
            st.close();
            conn.close(); //st.getConnection().close(); //samething
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void closeConnections(Statement st) {
        try {
            st.close();
            conn.close(); //st.getConnection().close(); //samething
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
