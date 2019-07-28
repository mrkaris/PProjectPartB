
package entities;

import java.util.Objects;

public class CourseStudent {
    
    private int csid;
    private Course course;
    private Student student;

    public CourseStudent() {
    }

    public CourseStudent(Course course, Student student) {
        this.course = course;
        this.student = student;
    }

    public CourseStudent(int csid, Course course, Student student) {
        this.csid = csid;
        this.course = course;
        this.student = student;
    }

    public int getCsid() {
        return csid;
    }

    public void setCsid(int csid) {
        this.csid = csid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.csid;
        hash = 47 * hash + Objects.hashCode(this.course);
        hash = 47 * hash + Objects.hashCode(this.student);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CourseStudent other = (CourseStudent) obj;
        if (this.csid != other.csid) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CourseStudent{" + "csid=" + csid + ", course=" + course + ", student=" + student + '}';
    }
    
    

    }
