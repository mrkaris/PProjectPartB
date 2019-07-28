package dao;

import entities.Assignment;
import entities.Course;
import entities.CourseTrainer;
import entities.Student;
import entities.StudentAssignment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentAssignmentDao extends Dao {

    private final String getAsmntPerSdntPerCourse = "select*from studentAssignment order by sid;";
    private final String insertStudentAssignmentDao = "insert into studentAssignment(sid,asid) values(?, ?)";
    private final String getAssignmentByStudent ="select asid from studentAssignment where sid=?;";

    public List<StudentAssignment> getAsmntsPerSdntPerCourse() {

        List<StudentAssignment> assignmentsPerStPerCrs = new ArrayList();
        AssignmentDao adao = new AssignmentDao();
        StudentDao sdao = new StudentDao();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getAsmntPerSdntPerCourse);
            while (rs.next()) {
                StudentAssignment s = new StudentAssignment();
                s.setSaid(rs.getInt(1));
                s.setStudent(sdao.getStudentById(rs.getInt(2)));
                s.setAssignment(adao.getAssignmentById(rs.getInt(3)));
                s.setOralMark(rs.getInt(4));
                s.setTotalMark(rs.getInt(5));
                assignmentsPerStPerCrs.add(s);
            }

            closeConnections(rs, st);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return assignmentsPerStPerCrs;
    }
    public boolean insertCourseStudent(StudentAssignment cs) {
        boolean inserted = false;
        if (cs != null) {
            try {
                PreparedStatement pst = getConnection().prepareStatement(insertStudentAssignmentDao);
                pst.setInt(1, cs.getStudent().getSid());
                pst.setInt(2, cs.getAssignment().getAsid());
                int result = pst.executeUpdate();
                if (result > 0) {
                    inserted = true;
                    System.out.println("Entry inserted successfully");
                } else {
                    System.out.println("Entry not inserted");
                }
                pst.close();
                closeConnections(pst);

            } catch (SQLException ex) {
                System.out.println("Something went wrong..");
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);

            }
        }

        return inserted;
    }
    public List<Assignment> getAssignmentByStudent(Student c) {
        CourseDao cdao= new CourseDao();
        List<Assignment> assignments = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getAssignmentByStudent);
            pst.setInt(1, c.getSid());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                AssignmentDao s = new AssignmentDao();
                assignments.add(s.getAssignmentById(rs.getInt(1)));
            }

            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return assignments;
    }

}
