package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/*
import model.Course;
import model.Student;
import model.Teacher;
import repository.CourseRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import java.util.ArrayList;
import java.util.List;
 */

/**
 * Main class where program starts.
 */
public class StartApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
        primaryStage.setTitle("MENU");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    /**
     * Start point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        //remove the "/*...*/" from main and from import in case you want to test the lambda expression
        /*
        System.out.println("UNIVERSITY APPLICATION");
        //list of courses for students
        List<Course> coursesStudent1 = new ArrayList<Course>();
        List<Course> coursesStudent2 = new ArrayList<Course>();
        List<Course> coursesStudent3 = new ArrayList<Course>();
        //students
        Student student1 = new Student("Miruna", "Lupas", 1, 30, coursesStudent1);
        Student student2 = new Student("Monika", "Popescu", 2, 20, coursesStudent2);
        Student student3 = new Student("Andreea", "Vrabie", 3, 5, coursesStudent3);
        //list of courses for teacher
        List<Course> coursesTeacher = new ArrayList<Course>();
        //teacher
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);
        //list of students for courses
        List<Student> studentsForCourse1 = new ArrayList<Student>();
        List<Student> studentsForCourse2 = new ArrayList<Student>();
        //courses
        Course course1 = new Course("BD",teacher,1,studentsForCourse1,5,1);
        Course course2 = new Course("SGBD",teacher,20,studentsForCourse2,5,2);
        //add the courses to the teacher
        coursesTeacher.add(course1);
        coursesTeacher.add(course2);
        //repo for students
        List<Student> students = new ArrayList<Student>();
        StudentRepository studRepo = new StudentRepository(students);
        studRepo.getstudentList().add(student1);
        studRepo.getstudentList().add(student2);
        studRepo.getstudentList().add(student3);
        //repo for teachers
        List<Teacher> teachers = new ArrayList<Teacher>();
        TeacherRepository teacherRepo = new TeacherRepository(teachers);
        teacherRepo.getteacherList().add(teacher);
        //repo for courses
        List<Course> courses = new ArrayList<Course>();
        CourseRepository courseRepo = new CourseRepository(courses);
        courseRepo.getcourseList().add(course1);
        courseRepo.getcourseList().add(course2);
        MenuUniversityApplication menu = new MenuUniversityApplication(courseRepo,teacherRepo,studRepo);
        menu.DisplayMenu();
         */
    }
}