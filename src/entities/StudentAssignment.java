package entities;

import java.util.Objects;

public class StudentAssignment {
    
    private int said;
    private Student student;
    private Assignment assignment;
    private int oralMark;
    private int totalMark;

    public int getOralMark() {
        return oralMark;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    public StudentAssignment(int said, Student student, Assignment assignment, int oralMark, int totalMark) {
        this.said = said;
        this.student = student;
        this.assignment = assignment;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public StudentAssignment() {
    }

    public StudentAssignment(Student student, Assignment assignment) {
        this.student = student;
        this.assignment = assignment;
    }

    public StudentAssignment(int said, Student student, Assignment assignment) {
        this.said = said;
        this.student = student;
        this.assignment = assignment;
    }

    public int getSaid() {
        return said;
    }

    public void setSaid(int said) {
        this.said = said;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.said;
        hash = 17 * hash + Objects.hashCode(this.student);
        hash = 17 * hash + Objects.hashCode(this.assignment);
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
        final StudentAssignment other = (StudentAssignment) obj;
        if (this.said != other.said) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StudentAssignment{" + "said=" + said + ", student=" + student + ", assignment=" + assignment + '}';
    }
    
    
    
}
