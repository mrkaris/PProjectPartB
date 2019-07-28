package dao;

import entities.Course;
import entities.CourseStudent;
import entities.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseStudentDao extends Dao {

    private final String getStudentsPerCourse = "select*from courseStudent order by cid;";
    private final String getStudentsByCourse = "select stuid from courseStudent where cid=? order by stuid";
    private final String insertCourseStudent = "insert into courseStudent(stuid,cid) values(?, ?)";

    public List<CourseStudent> getStudentPerCourse() {

        List<CourseStudent> studentsPerCourse = new ArrayList();
        CourseDao cdao = new CourseDao();
        StudentDao sdao = new StudentDao();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getStudentsPerCourse);
            while (rs.next()) {
                CourseStudent s = new CourseStudent();
                s.setCsid(rs.getInt(1));
                s.setCourse(cdao.getCourseById(rs.getInt(3)));
                s.setStudent(sdao.getStudentById(rs.getInt(2)));
                studentsPerCourse.add(s);
            }

            closeConnections(rs, st);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentsPerCourse;
    }

    public boolean insertCourseStudent(CourseStudent cs) {
        boolean inserted = false;
        if (cs != null) {
            try {
                PreparedStatement pst = getConnection().prepareStatement(insertCourseStudent);
                pst.setInt(1, cs.getStudent().getSid());
                pst.setInt(2, cs.getCourse().getcid());
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

    public List<Student> getStudentsByCourse(Course c) {

        List<Student> students = new ArrayList();
        try {
            PreparedStatement pst = getConnection().prepareStatement(getStudentsByCourse);
            pst.setInt(1, c.getcid());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                StudentDao sdao = new StudentDao();
                students.add(sdao.getStudentById(rs.getInt(1)));
            }

            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;
    }

}
