package dao;

import entities.CourseStudent;
import entities.CourseTrainer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseTrainerDao extends Dao{
    
    private final String getTrainersPerCourse = "select*from courseTrainer order by cid;";
    private final String insertCourseTrainer = "insert into courseTrainer(tid,cid) values(?, ?)";
    
    public List<CourseTrainer> getTrainerPerCourse() {

        List<CourseTrainer> studentsPerCourse = new ArrayList();
        CourseDao cdao=new CourseDao();
        TrainerDao sdao=new TrainerDao();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getTrainersPerCourse);
            while (rs.next()) {
                CourseTrainer s = new CourseTrainer();
                s.setCtid(rs.getInt(1));
                s.setCourse(cdao.getCourseById(rs.getInt(3)));
                s.setTrainer(sdao.getTrainerById(rs.getInt(2)));
                studentsPerCourse.add(s);
            }

            closeConnections(rs, st);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentsPerCourse;
    }
    
    public boolean insertCourseStudent(CourseTrainer cs) {
        boolean inserted = false;
        if (cs != null) {
            try {
                PreparedStatement pst = getConnection().prepareStatement(insertCourseTrainer);
                pst.setInt(1, cs.getTrainer().getTid());
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
    
}
