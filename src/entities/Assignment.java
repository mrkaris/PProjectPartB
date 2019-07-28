package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Assignment {

    private int asid;
    private String title;
    private String descr;
    private LocalDate subDate;
    private Course course;

    public Assignment() {
    }

    public Assignment(String title, String descr, LocalDate subDate, Course course) {
        this.title = title;
        this.descr = descr;
        this.subDate = subDate;
        this.course = course;
    }

    public Assignment(int asid, String title, String descr, LocalDate subDate, Course course) {
        this.asid = asid;
        this.title = title;
        this.descr = descr;
        this.subDate = subDate;
        this.course = course;
    }

    public int getAsid() {
        return asid;
    }

    public void setAsid(int asid) {
        this.asid = asid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public LocalDate getSubDate() {
        return subDate;
    }

    public void setSubDate(LocalDate subDate) {
        this.subDate = subDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.asid;
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + Objects.hashCode(this.descr);
        hash = 23 * hash + Objects.hashCode(this.subDate);
        hash = 23 * hash + Objects.hashCode(this.course);
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
        final Assignment other = (Assignment) obj;
        if (this.asid != other.asid) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.descr, other.descr)) {
            return false;
        }
        if (!Objects.equals(this.subDate, other.subDate)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Assignment{" + "asid: " + asid + ", title: " + title + ", descr: " + descr + ", subDate: " + subDate + ", course: " + course + '}';
    }

}
