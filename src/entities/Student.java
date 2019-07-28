package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Student {
    
    private int sid;
    private String firstName;
    private String lastName;
    private double tuitionFees;
    private LocalDate dob;

    public Student() {
    }

    public Student(String firstName, String lastName, double tuitionFees, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tuitionFees = tuitionFees;
        this.dob = dob;
    }

    public Student(int sid, String firstName, String lastName, double tuitionFees, LocalDate dob) {
        this.sid = sid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tuitionFees = tuitionFees;
        this.dob = dob;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public double getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(double tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.firstName);
        hash = 59 * hash + Objects.hashCode(this.lastName);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.tuitionFees) ^ (Double.doubleToLongBits(this.tuitionFees) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.dob);
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
        final Student other = (Student) obj;
//        if (Double.doubleToLongBits(this.tuitionFees) != Double.doubleToLongBits(other.tuitionFees)) {
//            return false;
//        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!(this.dob.isEqual(other.dob))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + "sid:" + sid + ", firstName: " + firstName + ", lastName: " + lastName + ", tuitionFees: " + tuitionFees + ", dob: " + dob + '}';
    }
    
    
    
}
