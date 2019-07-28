package pprojectpartb;

import dao.AssignmentDao;
import dao.CourseDao;
import dao.CourseStudentDao;
import dao.CourseTrainerDao;
import dao.StudentAssignmentDao;
import dao.StudentDao;
import dao.TrainerDao;
import entities.Assignment;
import entities.Course;
import entities.CourseStudent;
import entities.CourseTrainer;
import entities.Student;
import entities.StudentAssignment;
import entities.Trainer;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void mainMenu(Scanner sc) {
        String input;
        do {
            System.out.println("\n\n--------PERSONAL PROJECT PART B ----------"
                    + "\n------------ MAIN MENU -------------------"
                    + "\n\n1.Print Menu"
                    + "\n2.Insert Menu"
                    + "\n0.Exit"
                    + "\n\n       type in your option and press enter \n");
            input = sc.next();
            switch (input) {
                case ("1"):
                    printMenu(sc);
                    continue;
                case ("2"):
                    insertMenu(sc);
                    continue;
                case ("0"):
                    break;
            }
        } while (!input.equals("0"));
        System.out.println("Bye");

    }

    public static void printMenu(Scanner sc) {
        String input;
        do {
            System.out.println("\n\n    - PRINT MENU -"
                    + "\n\n1.Print all students"
                    + "\n2.Print all trainers"
                    + "\n3.Print all assignments"
                    + "\n4.Print all courses"
                    + "\n5.Print all the students per course"
                    + "\n6.Print all the trainers per course"
                    + "\n7.Print all the assignments per course"
                    + "\n8.Print all the assignments per course per student"
                    + "\n9.Print all students that belong to more than one course"
                    + "\n0.Back to Main menu"
                    + "\n\n       type in your option and press enter");
            input = sc.next();
            switch (input) {
                case ("1"):
                    System.out.println("\nSTUDENTS:\n");
                    StudentDao sdao = new StudentDao();
                    List<Student> students = sdao.getStudent();
                    for (Student s : students) {
                        System.out.println(s);
                    }
                    continue;
                case ("2"):
                    System.out.println("\nTRAINERS:\n");
                    TrainerDao tdao = new TrainerDao();
                    List<Trainer> trainers = tdao.getTrainer();
                    for (Trainer t : trainers) {
                        System.out.println(t);
                    }
                    continue;
                case ("3"):
                    System.out.println("\nASSIGNMENT:\n");
                    AssignmentDao adao = new AssignmentDao();
                    List<Assignment> assignments = adao.getAssignment();
                    for (Assignment a : assignments) {
                        System.out.println("id=" + a.getAsid() + ", Title=" + a.getTitle() + ", Descr="
                                + a.getDescr() + ", SubDate=" + a.getSubDate() + ", Course=" + a.getCourse().getTitle());
                    }
                    continue;
                case ("4"):
                    System.out.println("\nCOURSES:\n");
                    CourseDao cdao = new CourseDao();
                    List<Course> courses = cdao.getCourse();
                    for (Course c : courses) {
                        System.out.println(c);
                    }
                    continue;
                case ("5"):
                    System.out.println("\nSTUDENTS PER COURSE:\n");
                    CourseStudentDao cstdao = new CourseStudentDao();
                    List<CourseStudent> studentsPerCourse = cstdao.getStudentPerCourse();
                    for (CourseStudent cs : studentsPerCourse) {
                        System.out.println(cs.getCourse().getTitle() + ": "
                                + cs.getStudent().getfirstName() + " "
                                + cs.getStudent().getlastName());
                    }
                    continue;
                case ("6"):
                    System.out.println("TRAINERS PER COURSE:");
                    CourseTrainerDao ctdao = new CourseTrainerDao();
                    List<CourseTrainer> trainersPerCourse = ctdao.getTrainerPerCourse();
                    for (CourseTrainer cs : trainersPerCourse) {
                        System.out.println(cs.getCourse().getTitle() + ": "
                                + cs.getTrainer().getfirstName() + " "
                                + cs.getTrainer().getlastName());
                    }
                    continue;
                case ("7"):
                    System.out.println("ASSIGNMENT PER COURSE:");
                    AssignmentDao adao2 = new AssignmentDao();
                    List<Assignment> assignmentPerCourse = adao2.getAssignment();
                    for (Assignment a : assignmentPerCourse) {
                        System.out.println(a.getCourse().getTitle() + ": " + a.getTitle());
                    }
                    continue;
                case ("8"):
                    System.out.println("ASSIGNMENTS PER COURSE PER STUDENT:");
                    StudentAssignmentDao sadao = new StudentAssignmentDao();
                    List<StudentAssignment> asmntsPerStuPerCrs = sadao.getAsmntsPerSdntPerCourse();
                    for (StudentAssignment sa : asmntsPerStuPerCrs) {
                        System.out.println(sa.getStudent().getfirstName() + " " + sa.getStudent().getlastName()
                                + ": " + sa.getAssignment().getCourse().getTitle()
                                + ", " + sa.getAssignment().getTitle()+", oralMark:"
                                +(sa.getOralMark()!=0 ? sa.getOralMark():"notMarkedYet")
                                +", totalMark:"+(sa.getTotalMark()!=0 ? sa.getTotalMark():"notMarkedYet"));
                    }
                    
                    continue;
                case ("9"):
                    System.out.println("STUDENTS WITH MORE THAN ONE ");
                    StudentDao sdao2 = new StudentDao();
                    List<Student> studentsWithCourses = sdao2.getStudentsWithCourses();
                    for (Student s : studentsWithCourses) {
                        System.out.println(s.getfirstName() + " " + s.getlastName());
                    }
                    continue;
                case ("0"):
                    break;
                default:
                    System.out.println("Not an option");
            }
        } while (!input.equals("0"));
    }

    public static void insertMenu(Scanner sc) {
        String input;
        do {
            System.out.println("\n\n    - INSERT MENU -"
                    + "\n\n1.Insert student"
                    + "\n2.Insert trainer"
                    + "\n3.Insert course"
                    + "\n4.Insert assignment"
                    + "\n5.Add students to course"
                    + "\n6.Add trainers to course"
                    + "\n7.Add assignments to student"
                    + "\n0.Back to Main menu"
                    + "\n\n       type in your option and press enter");
            input = sc.next();
            switch (input) {
                case ("1"):
                    StudentDao sdao = new StudentDao();
                    Student s = Helper.createStudent(sc);
                    sdao.insertStudent(s);
                    continue;
                case ("2"):
                    TrainerDao tdao = new TrainerDao();
                    Trainer t = Helper.createTrainer(sc);
                    tdao.insertTrainer(t);
                    continue;
                case ("3"):
                    CourseDao cdao = new CourseDao();
                    Course c = Helper.createCourse(sc);
                    cdao.insertCourse(c);
                    continue;
                case ("4"):
                    AssignmentDao adao = new AssignmentDao();
                    Assignment a = Helper.createAssignment(sc);
                    adao.insertAssignment(a);
                    continue;
                case ("5"):
                    CourseStudentDao csdao = new CourseStudentDao();
                    CourseStudent cs = Helper.selectCourseStudent(sc);
                    csdao.insertCourseStudent(cs);
                    continue;
                case ("6"):
                    CourseTrainerDao ctdao = new CourseTrainerDao();
                    CourseTrainer ct = Helper.selectCourseTrainer(sc);
                    ctdao.insertCourseStudent(ct);
                    continue;
                case ("7"):
                    StudentAssignmentDao sadao = new StudentAssignmentDao();
                    StudentAssignment sa = Helper.selectStudentAssignment(sc);
                    sadao.insertCourseStudent(sa);
                    continue;
                case ("0"):
                    break;
                default:
                    System.out.println("Not an option");
            }
        } while (!input.equals("0"));
    }

}
