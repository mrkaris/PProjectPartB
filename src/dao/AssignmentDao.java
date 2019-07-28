package dao;

import entities.Assignment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssignmentDao extends Dao {

    private final String getAssignments = "select*from assignment;";
    private final String getAssignmentsById = "select * from assignment where asid= ?";
    private final String insertAssignment = "insert into assignment(astitle,asdescr,subdate,cid) values(?, ?, ?, ?)";

    public List<Assignment> getAssignment() {

        List<Assignment> assignments = new ArrayList();
        CourseDao cdao = new CourseDao();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAssignments);
            while (rs.next()) {
                Assignment s = new Assignment();
                s.setAsid(rs.getInt(1));
                s.setTitle(rs.getString(2));
                s.setDescr(rs.getString(3));
                s.setSubDate(rs.getDate(4).toLocalDate());
                s.setCourse(cdao.getCourseById(rs.getInt(5)));
                assignments.add(s);
            }

            closeConnections(rs, st);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return assignments;

    }

    public Assignment getAssignmentById(int id) {
        Assignment s = null;
        CourseDao cdao = new CourseDao();
        try {

            PreparedStatement pst = getConnection().prepareStatement(getAssignmentsById);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            s = new Assignment(rs.getInt(1),
                    rs.getString(2), 
                    rs.getString(3), 
                    rs.getDate(4).toLocalDate(), 
                    cdao.getCourseById(rs.getInt(5)));
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }

    public boolean insertAssignment(Assignment s) {
        boolean inserted = false;

        try {
            PreparedStatement pst = getConnection().prepareStatement(insertAssignment);
            pst.setString(1, s.getTitle());
            pst.setString(2, s.getDescr());
            pst.setString(3, s.getSubDate().toString());
            pst.setInt(4, s.getCourse().getcid());
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Assignment inserted successfully");
            } else {
                System.out.println("Assignment not inserted");
            }
            pst.close();
            closeConnections(pst);

        } catch (SQLException ex) {
            System.out.println("Something went wrong..");
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return inserted;
    }

}
