
package dao;

import entities.CourseStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseStreamDao extends Dao{
    
    private final String getStreamsById = "select * from coursestream where streamid= ?";
    
    public CourseStream getStreamById(int id) {
        CourseStream s = null;
        try {

            PreparedStatement pst = getConnection().prepareStatement(getStreamsById);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            s = new CourseStream(rs.getInt(1), rs.getString(2));
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }
    
}
