package dao;

import entities.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDao extends Dao {

    private final String getCourses = "select*from course;";
    private final String getCourseById = "select * from course where cid= ?";
    private final String insertCourse = "insert into course(ctitle,ctype,cstartdate,cenddate,cstreamid) values(?, ?, ?, ?,?)";

    public List<Course> getCourse() {

        List<Course> courses = new ArrayList();
        CourseStreamDao csdao = new CourseStreamDao();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getCourses);
            while (rs.next()) {
                Course s = new Course();
                s.setcid(rs.getInt(1));
                s.setTitle(rs.getString(2));
                s.settype(rs.getString(3));
                s.setstartDate(rs.getDate(4).toLocalDate());
                s.setendDate(rs.getDate(5).toLocalDate());
                s.setStream(csdao.getStreamById(rs.getInt(6)));
                courses.add(s);
            }

            closeConnections(rs, st);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;

    }

    public Course getCourseById(int id) {
        Course s = null;
        try {

            PreparedStatement pst = getConnection().prepareStatement(getCourseById);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            CourseStreamDao csdao = new CourseStreamDao();
            s = new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), csdao.getStreamById(rs.getInt(6)));
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }

    public boolean insertCourse(Course s) {
        boolean inserted = false;

        try {
            PreparedStatement pst = getConnection().prepareStatement(insertCourse);
            pst.setString(1, s.getTitle());
            pst.setString(2, s.gettype());
            pst.setString(3, s.getstartDate().toString());
            pst.setString(4, s.getendDate().toString());
            pst.setInt(5, s.getStream().getStreamid());
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Course inserted successfully");
            } else {
                System.out.println("Course not inserted");
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
