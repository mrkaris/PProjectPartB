package dao;

import entities.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersDao extends Dao {

    private final String getUserByName = "select username from users where username= ?";
    private final String getUserpassByName = "select userpassword from users where username= ?";
    private final String getUserBanDateByName = "select bandate from users where username= ?";
    private final String updateDate = "update users set bandate= ? where username = ?;";

    public String getUserNameByName(String name) {
        String s = null;
        try {

            PreparedStatement pst = getConnection().prepareStatement(getUserByName);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.isBeforeFirst()) {
                rs.next();
                s = rs.getString("username");
            }

            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }
    public String getUserPassByName(String name) {
        String s = null;
        try {

            PreparedStatement pst = getConnection().prepareStatement(getUserpassByName);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.isBeforeFirst()) {
                rs.next();
                s = rs.getString("userpassword");
            }

            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }
    public boolean updateDate(String username, LocalDateTime date) {
        boolean update=false;
        try {
            PreparedStatement pst = getConnection().prepareStatement(updateDate);
            pst.setString(1,date.toString());
            pst.setString(2, username);
           
            int result = pst.executeUpdate();

            update=true;
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
    }
    public String getUserBanDateByName(String name) {
        String s = null;
        try {

            PreparedStatement pst = getConnection().prepareStatement(getUserBanDateByName);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.isBeforeFirst()) {
                rs.next();
                s = rs.getString("bandate");
            }

            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }
}
