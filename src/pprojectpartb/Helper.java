package pprojectpartb;

import dao.AssignmentDao;
import dao.CourseDao;
import dao.CourseStreamDao;
import dao.CourseStudentDao;
import dao.CourseTrainerDao;
import dao.StudentAssignmentDao;
import dao.StudentDao;
import dao.TrainerDao;
import dao.UsersDao;
import entities.Assignment;
import entities.Course;
import entities.CourseStudent;
import entities.CourseTrainer;
import entities.Student;
import entities.StudentAssignment;
import entities.Trainer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Helper {

    private static DateTimeFormatter frmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static int testIndex(Scanner sc, String type, List c) {
        int num;
        do {
            System.out.println("Choose " + type + " (1-" + c.size() + ")");
            while (!sc.hasNextInt()) {
                System.out.println("must be a number between 1 and " + c.size());
                sc.next();
            }
            num = sc.nextInt();
        } while (num <= 0 || c.size() < num);
        return num;
    }

    public static boolean isDateValid(String x) {
        try {
            LocalDate date = LocalDate.parse(x, frmt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Student createStudent(Scanner sc) {

        System.out.println("Type in student's first name ");
        String firstname = sc.next();
        System.out.println("Type in student's last name ");
        String lastname = sc.next();
        double fees;
        do {
            System.out.println("Type in student's tuition fees (positive number)");
            while (!sc.hasNextDouble()) {
                System.out.println("must be a number ");
                sc.next();
            }
            fees = sc.nextDouble();
        } while (fees <= 0);
        System.out.println("Type in student's date of birth(dd/mm/yyyy) (e.g. 02/02/1999) ");
        String dob = sc.next();

        while (!Helper.isDateValid(dob)) {
            System.out.println("Not a valid date. Must be in the form : dd/mm/yyyy (e.g. 02/02/1999)");
            dob = sc.next();
        }
        LocalDate sdob = LocalDate.parse(dob, frmt);
        Student s = new Student(firstname, lastname, fees, sdob);
        return s;
    }

    public static Trainer createTrainer(Scanner sc) {
        System.out.println("Type in trainer's first name ");
        String firstname = sc.next();
        System.out.println("Type in trainer's last name ");
        String lastname = sc.next();

        System.out.println("Type in trainer's subject");
        String subject = sc.next();

        Trainer t = new Trainer(firstname, lastname, subject);
        return t;
    }

    public static Course createCourse(Scanner sc) {
        sc.nextLine();
        System.out.println("Type in courses's title ");
        String title = sc.nextLine();
        System.out.println("Type in course's type ");
        String type = sc.nextLine();
        System.out.println("Type in course's start date(dd/mm/yyyy) (e.g. 02/02/2019) ");
        String sdate = sc.next();

        while (!isDateValid(sdate)) {
            System.out.println("Not a valid Date. Must be in the form : dd/mm/yyyy (e.g. 02/02/1999)");
            sdate = sc.next();
        }
        LocalDate csdate = LocalDate.parse(sdate, frmt);
        System.out.println("Type in course's end date(dd/mm/yyyy) (e.g. 02/04/2019) ");
        String edate = sc.next();
        while (!isDateValid(edate)) {
            System.out.println("Not a valid Date. Must be in the form : dd/mm/yyyy (e.g. 02/02/1999)");
            edate = sc.next();
        }
        String stream;
        int streamid = 0;
        do {
            System.out.println("Type in \"1\" for full-time or \"2\" for part-time");
            stream = sc.next();

            switch (stream) {
                case ("1"):
                    streamid = 1;
                    break;
                case ("2"):
                    streamid = 2;
                    break;
                default:
                    System.out.println("Not an option\n");
            }
        } while (!stream.equals("1") && !stream.equals("2"));

        LocalDate cedate = LocalDate.parse(edate, frmt);
        CourseStreamDao streamDao = new CourseStreamDao();
        Course c = new Course(title, type, csdate, cedate, streamDao.getStreamById(streamid));
        return c;
    }

    public static Assignment createAssignment(Scanner sc) {
        sc.nextLine();
        System.out.println("Type in assignment's title ");
        String title = sc.nextLine();
        System.out.println("Type in assignment's description ");
        String descr = sc.nextLine();
        System.out.println("Type in assignment's submission date(dd/mm/yyyy) (e.g. 02/02/2019) ");
        String subdate = sc.next();

        while (!isDateValid(subdate)) {
            System.out.println("Not a valid Date. Must be in the form : dd/mm/yyyy (e.g. 02/02/1999)");
            subdate = sc.next();
        }
        LocalDate asubdate = LocalDate.parse(subdate, frmt);
        CourseDao cdao = new CourseDao();
        List<Course> c = cdao.getCourse();
        System.out.println("\nCOURSES: ");
        for (int i = 0; i < c.size(); i++) {
            System.out.println("[" + (i + 1) + "]-" + c.get(i).getTitle());
        }
        int courseid = testIndex(sc, "Course", c);
        Assignment a = new Assignment(title, descr, asubdate, cdao.getCourseById(courseid));
        return a;
    }

    public static CourseStudent selectCourseStudent(Scanner sc) {
        CourseStudent cs = null;
        CourseDao cdao = new CourseDao();
        StudentDao sdao = new StudentDao();
        CourseStudentDao csdao = new CourseStudentDao();
        List<Course> c = cdao.getCourse();
        System.out.println("\nCOURSES: ");
        for (int i = 0; i < c.size(); i++) {
            System.out.println("[" + (i + 1) + "]-" + c.get(i).getTitle());
        }
        int courseid = testIndex(sc, "Course", c);
        Course course = cdao.getCourseById(courseid);
        List<Student> students = sdao.getStudent();
        List<Student> studentsWithCourse = new ArrayList();
        List<CourseStudent> studentsPerCourse = csdao.getStudentPerCourse();
        System.out.println("\nSTUDENTS:");
        int counter = 1;
        for (CourseStudent cstu : studentsPerCourse) {
            if (cstu.getCourse().equals(course)) {
                studentsWithCourse.add(cstu.getStudent());
            }
        }
        students.removeAll(studentsWithCourse);
        for (Student s : students) {
            System.out.println("[" + (counter) + "]-" + s.getfirstName()
                    + " " + s.getlastName());
            counter++;
        }

        if (students.isEmpty()) {
            System.out.println("\nNo Students to add\n");
        } else {
            int studentid = testIndex(sc, "Student", students);
            Student student = students.get(studentid - 1);
            cs = new CourseStudent(course, student);
        }
        return cs;
    }

    public static CourseTrainer selectCourseTrainer(Scanner sc) {
        CourseTrainer cs = null;
        CourseDao cdao = new CourseDao();
        TrainerDao sdao = new TrainerDao();
        CourseTrainerDao csdao = new CourseTrainerDao();
        List<Course> c = cdao.getCourse();
        System.out.println("\nCOURSES: ");
        for (int i = 0; i < c.size(); i++) {
            System.out.println("[" + (i + 1) + "]-" + c.get(i).getTitle());
        }
        int courseid = testIndex(sc, "Course", c);
        Course course = cdao.getCourseById(courseid);
        List<Trainer> trainers = sdao.getTrainer();
        List<Trainer> trainersWithCourse = new ArrayList();
        List<CourseTrainer> trainersPerCourse = csdao.getTrainerPerCourse();
        System.out.println("\nTRAINERS:");
        int counter = 1;
        for (CourseTrainer cstu : trainersPerCourse) {
            if (cstu.getCourse().equals(course)) {
                trainersWithCourse.add(cstu.getTrainer());
            }
        }
        trainers.removeAll(trainersWithCourse);
        for (Trainer s : trainers) {
            System.out.println("[" + (counter) + "]-" + s.getfirstName()
                    + " " + s.getlastName());
            counter++;
        }

        if (trainers.isEmpty()) {
            System.out.println("\nNo Trainers to add\n");
        } else {
            int studentid = testIndex(sc, "Trainer", trainers);
            Trainer student = trainers.get(studentid - 1);
            cs = new CourseTrainer(course, student);
        }

        return cs;
    }

    public static StudentAssignment selectStudentAssignment(Scanner sc) {
        CourseDao cdao = new CourseDao();
        CourseStudentDao csdao = new CourseStudentDao();
        StudentAssignmentDao sadao = new StudentAssignmentDao();
        StudentAssignment sa = null;
        Student student = null;
        AssignmentDao adao = new AssignmentDao();
        Assignment assignment = null;
        List<Course> c = cdao.getCourse();
        System.out.println("\nCOURSES: ");
        for (int i = 0; i < c.size(); i++) {
            System.out.println("[" + (i + 1) + "]-" + c.get(i).getTitle());
        }
        int courseid = testIndex(sc, "Course", c);
        Course course = cdao.getCourseById(courseid);
        List<Student> students = csdao.getStudentsByCourse(course);
        System.out.println("\nSTUDENTS:");
        int counter = 1;
        for (Student s : students) {
            System.out.println("[" + (counter) + "]-" + s.getfirstName()
                    + " " + s.getlastName());
            counter++;
        }
        if (students.isEmpty()) {
            System.out.println("\nNo Students to select\n");
        } else {
            int studentid = testIndex(sc, "Student", students);
            student = students.get(studentid - 1);
            List<Assignment> assignmentsOfStudent = sadao.getAssignmentByStudent(student);
            List<Assignment> assignments = adao.getAssignment();
            List<Assignment> finalaslist = new ArrayList();
            assignments.removeAll(assignmentsOfStudent);

            int counter2 = 1;
            System.out.println("\nASSIGNMENTS: ");
            for (Assignment a : assignments) {
                if (a.getCourse().equals(course)) {
                    System.out.println("[" + (counter2) + "]-" + a.getTitle());
                    counter2++;
                    finalaslist.add(a);
                }
            }
            if (finalaslist.isEmpty()) {
                System.out.println("\nStudent already has all the course's assignments\n");
            } else {
                int assignmentid = testIndex(sc, "Assignment", finalaslist);
                assignment = finalaslist.get(assignmentid - 1);
                sa = new StudentAssignment(student, assignment);
            }

        }
        return sa;
    }

    public static void login(Scanner sc) {
        System.out.println("------- WELCOME TO PERSONAL PROJECT PART B --------\n");
        UsersDao udao = new UsersDao();
        String username;
        int count = 1;
        do {
            if (count == 4) {
                System.out.println("    You do not belong here");
                System.exit(0);
            }
            System.out.println("\nType in your username,  (tries left: " + (4 - count) + ")\n");
            username = sc.next();

            if (!username.toLowerCase().equals(udao.getUserNameByName(username).toLowerCase())) {
                System.out.println("User not found.");
            }
            count++;
        } while (!username.toLowerCase().equals(udao.getUserNameByName(username).toLowerCase()));
        String password;
        count = 01;
        LocalDateTime loginDate = LocalDateTime.now();
        DateTimeFormatter newdtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        boolean isBefore;
        try {
            LocalDateTime bantime = LocalDateTime.parse(udao.getUserBanDateByName(username), newdtf);
            isBefore = loginDate.isBefore(bantime);
        } catch (Exception e) {
            isBefore = false;
        }

        if (!isBefore || udao.getUserBanDateByName(username) == null) {
            do {
                if (count == 4) {
                    LocalDateTime date = LocalDateTime.now();
                    udao.updateDate(username, date.plusDays(1));
                    System.out.println("    Identity theft is no joke.");
                    System.exit(0);
                }
                System.out.println("\nHello " + username + ", please type in your password,"
                        + "  (tries left: " + (4 - count) + ") "
                        + "\n\n   -CAUTION! Three failed attemps will result in a 24hour ban!\n");
                password = sc.next();

                if (!password.toLowerCase().equals(udao.getUserPassByName(username).toLowerCase())) {
                    System.out.println("Incorrect password.");
                }
                count++;
            } while (!password.toLowerCase().equals(udao.getUserPassByName(username).toLowerCase()));

            System.out.println("\nWELCOME " + username + "!");
        } else {
            System.out.println("BAN ON USER:" + username + " IS STILL ACTIVE TRY AGAIN LATER");
            System.exit(0);

        }

    }

}
