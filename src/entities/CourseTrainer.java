package entities;

import java.util.Objects;

public class CourseTrainer {
    
    private int ctid;
    private Course course;
    private Trainer trainer;

    public CourseTrainer() {
    }

    public CourseTrainer(Course course, Trainer trainer) {
        this.course = course;
        this.trainer = trainer;
    }

    public CourseTrainer(int ctid, Course course, Trainer trainer) {
        this.ctid = ctid;
        this.course = course;
        this.trainer = trainer;
    }

    public int getCtid() {
        return ctid;
    }

    public void setCtid(int ctid) {
        this.ctid = ctid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.ctid;
        hash = 67 * hash + Objects.hashCode(this.course);
        hash = 67 * hash + Objects.hashCode(this.trainer);
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
        final CourseTrainer other = (CourseTrainer) obj;
        if (this.ctid != other.ctid) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.trainer, other.trainer)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CourseTrainer{" + "ctid=" + ctid + ", course=" + course + ", trainer=" + trainer + '}';
    }
    
    
    
}
