package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Course {
    
    private int cid;
    private String title;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private CourseStream stream;

    public Course() {
    }

    public Course(String title, String type, LocalDate startDate, LocalDate endDate, CourseStream stream) {
        this.title = title;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stream = stream;
    }

    public Course(int cid, String title, String type, LocalDate startDate, LocalDate endDate, CourseStream stream) {
        this.cid = cid;
        this.title = title;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stream = stream;
    }

    public int getcid() {
        return cid;
    }

    public void setcid(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }

    public LocalDate getstartDate() {
        return startDate;
    }

    public void setstartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getendDate() {
        return endDate;
    }

    public void setendDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public CourseStream getStream() {
        return stream;
    }

    public void setStream(CourseStream stream) {
        this.stream = stream;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.cid;
        hash = 97 * hash + Objects.hashCode(this.title);
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.startDate);
        hash = 97 * hash + Objects.hashCode(this.endDate);
        hash = 97 * hash + Objects.hashCode(this.stream);
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
        final Course other = (Course) obj;
        if (this.cid != other.cid) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.stream, other.stream)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + "cid: " + cid + ", title: " + title + ", type: " + type + ", startDate: " + startDate + ", endDate: " + endDate + ", stream: " + stream + '}';
    }
    
    
    
    
}
