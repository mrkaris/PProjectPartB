package dao;

import entities.Student;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDao extends Dao {

    private final String getStudents = "select*from student;";
    private final String getStudentById = "select * from student where sid= ?";
    private final String studentsWithCourses = "select * from MoreThanTwoCourses;";
    private final String insertStudent = "insert into student(sfname,slname,tuitionfees,sdob) values(?, ?, ?, ?)";
    private final String getStudentbyAtts = "select * from student where sfname= ? and slname= ? and tuitionfees = ? and sdob= ?";

    public Student getStudentByAtts(Student sinput) {
        Student s = null;
        try {

            PreparedStatement pst = getConnection().prepareStatement(getStudentbyAtts);
            pst.setString(1, sinput.getfirstName());
            pst.setString(2, sinput.getlastName());
            pst.setDouble(3, sinput.getTuitionFees());
            pst.setString(4, sinput.getDob().toString());

            ResultSet rs = pst.executeQuery();
            if (rs.isBeforeFirst()) {
                rs.next();
                s = new Student(
                        rs.getString("sfname"),
                        rs.getString("slname"),
                        rs.getDouble("tuitionfees"),
                        rs.getDate("sdob").toLocalDate());
            }

            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public List<Student> getStudent() {

        List<Student> students = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(getStudents);
            while (rs.next()) {
                Student s = new Student();
                s.setSid(rs.getInt(1));
                s.setfirstName(rs.getString(2));
                s.setlastName(rs.getString(3));
                s.setTuitionFees(rs.getDouble(4));
                s.setDob(rs.getDate(5).toLocalDate());
                students.add(s);
            }

            closeConnections(rs, st);

        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;

    }

    public Student getStudentById(int id) {
        Student s = null;
        try {

            PreparedStatement pst = getConnection().prepareStatement(getStudentById);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5).toLocalDate());
            closeConnections(rs, pst);

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }

    public List<Student> getStudentsWithCourses() {

        List<Student> students = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(studentsWithCourses);
            while (rs.next()) {
                Student s = getStudentById(rs.getInt(1));
                students.add(s);
            }

            closeConnections(rs, st);

        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;

    }

    public boolean insertStudent(Student s) {
        boolean inserted = false;
        //check if student already exists by searching for him with a query
        if (getStudentByAtts(s) != null) {
            System.out.println("Student Already Exists");
            return inserted;
        }

        try {
            PreparedStatement pst = getConnection().prepareStatement(insertStudent);
            pst.setString(1, s.getfirstName());
            pst.setString(2, s.getlastName());
            pst.setDouble(3, s.getTuitionFees());
            pst.setString(4, s.getDob().toString());
//            pst.setDate(4, Date.valueOf(s.getDob()));
            int result = pst.executeUpdate();
            if (result > 0) {
                inserted = true;
                System.out.println("Student inserted successfully");
            } else {
                System.out.println("Student not inserted");
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
