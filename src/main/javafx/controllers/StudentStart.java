package javafx.controllers;

import view.RegistrationSystem;
import view.MyReaderClass;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Course;
import model.Student;
import model.Teacher;
import repository.CourseRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentStart implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField idStudentName;

    @FXML
    private Text idCourses;

    @FXML
    private Text idTotalCreditsText;

    @FXML
    private Text idRegister;

    @FXML
    private TextField idCourse;

    private StudentRepository studRepo;
    private CourseRepository courseRepo;
    private RegistrationSystem registrationS;

    /**
     * This method logs a student.
     * @throws IOException if any problems occur by loading the XML file
     */
    @FXML
    private void logInButtonAction() throws IOException {
        FileWriter fileWriter = new FileWriter("..\\Lari_Maria_L3\\src\\lab3\\studentId.txt");
        FileWriter fileWriter2 = new FileWriter("..\\Lari_Maria_L3\\src\\lab3\\courseDetails.txt");

        //if we find the student's name in the list of students, he/she can log in
        for(Student student:studRepo.getstudentList())
        {
            if(idStudentName.getText().equals(student.getFirstName() + " " + student.getLastName()))
            {
                //we open the student's menu
                AnchorPane pane = FXMLLoader.load(getClass().getResource("StudentMenu.fxml"));
                rootPane.getChildren().setAll(pane);

                //we store the student's id in a file
                fileWriter.write(Long.toString(student.getStudentId()));
            }
        }

        StringBuilder courses = new StringBuilder(); //we store here the courses
        for(Course course:courseRepo.getCourseList())
        {
            courses.append("Course Name: ").append(course.getName()).append("\n").append("Students:\n");
            for (Student student: course.getStudentsEnrolled()){
                courses.append(student.getFirstName()).append(" ").append(student.getLastName()).append("\n");
            }
            courses.append("\n");
        }
        fileWriter2.write(courses.toString()); //we write the list of courses in a file

        fileWriter.close();
        fileWriter2.close();
    }

    /**
     * This method shows the available courses.
     */
    @FXML
    private void showAvailableCoursesAction(){
        StringBuilder courses = new StringBuilder(); //we will store the courses here
        for(Course course:courseRepo.getCourseList())
        {
            courses.append("Course Name: ").append(course.getName()).append("\n").append("Course Id: ").append(course.getCourseId()).append("\n").append("Teacher: ").append(course.getTeacher().getFirstName()).append(" ").append(course.getTeacher().getLastName()).append("\n").append("Number of credits: ").append(course.getCredits()).append("\n").append("\n");
        }
        idCourses.setText(courses.toString());
    }

    /**
     * This method shows the total number of credits of a student.
     * @throws FileNotFoundException if the file doesn't exist
     */
    @FXML
    private void showTotalCreditsAction() throws FileNotFoundException {
        //we search the student by id and print the number of credits
        for(Student student:studRepo.getstudentList())
        {
            if(student.getStudentId()==Long.parseLong(MyReaderClass.idReader()))
            {
                idTotalCreditsText.setText(Integer.toString(student.getTotalCredits()));
                idTotalCreditsText.setFill(Color.WHITE);
            }
        }
    }

    /**
     * This method registers a student to a course if possible.
     * @throws IOException for fileWriter
     */
    @FXML
    private void registerStudentAction() throws IOException {
        //temporary variables
        Course tempCourse = null;
        Student tempStudent = null;

        //we search the course by id and save it
        for (Course course : courseRepo.getCourseList())
        {
            if (course.getCourseId() == Long.parseLong(idCourse.getText()))
                tempCourse = course;
        }

        //we search the student by id and save it
        for (Student student : studRepo.getstudentList())
        {
            if (student.getStudentId() == Long.parseLong(MyReaderClass.idReader()))
                tempStudent = student;
        }

        assert tempCourse != null;
        assert tempStudent != null;

        //we print a message to see if the student was registered or not
        if(registrationS.register(tempCourse, tempStudent)) {
            idRegister.setText("Successfully Enrolled!");

            //we write the list of courses with the students enrolled in a file
            FileWriter fileWriter = new FileWriter("..\\Lari_Maria_L3\\src\\lab3\\courseDetails.txt");
            String courses = ""; //we store here the courses
            for(Course course:courseRepo.getCourseList())
            {
                courses = courses + "Course Name: " + course.getName() + "\n" + "Students:\n";
                for (Student student: course.getStudentsEnrolled()){
                    courses = courses + student.getFirstName() + " " + student.getLastName() + "\n";
                }
                courses = courses + "\n";
            }
            fileWriter.write(courses);
            fileWriter.close();
        }
        else
            idRegister.setText("Not Successfully Enrolled!");

        registrationS.register(tempCourse,tempStudent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //list of courses for students
        List<Course> coursesStudent1 = new ArrayList<>();
        List<Course> coursesStudent2 = new ArrayList<>();
        List<Course> coursesStudent3 = new ArrayList<>();

        //students
        Student student1 = new Student("Larisa", "Pargea", 1, 30, coursesStudent1);
        Student student2 = new Student("Maria", "Papuc", 2, 20, coursesStudent2);
        Student student3 = new Student("Mihai", "Oancea", 3, 5, coursesStudent3);

        //list of courses for teacher
        List<Course> coursesTeacher = new ArrayList<>();

        //teacher
        Teacher teacher = new Teacher("Diana","Cristea",1,coursesTeacher);

        //list of students for courses
        List<Student> studentsForCourse1 = new ArrayList<>();
        List<Student> studentsForCourse2 = new ArrayList<>();

        //courses
        Course course1 = new Course("BD",teacher,1,studentsForCourse1,5,1);
        Course course2 = new Course("SGBD",teacher,20,studentsForCourse2,5,2);

        //add the courses to the teacher
        coursesTeacher.add(course1);
        coursesTeacher.add(course2);

        //repo for students
        List<Student> students = new ArrayList<>();
        studRepo = new StudentRepository(students);
        studRepo.getstudentList().add(student1);
        studRepo.getstudentList().add(student2);
        studRepo.getstudentList().add(student3);

        //repo for teachers
        List<Teacher> teachers = new ArrayList<>();
        TeacherRepository teacherRepo = new TeacherRepository(teachers);
        teacherRepo.getTeacherList().add(teacher);

        //repo for courses
        List<Course> courses = new ArrayList<>();
        courseRepo = new CourseRepository(courses);
        courseRepo.getCourseList().add(course1);
        courseRepo.getCourseList().add(course2);

        registrationS = new RegistrationSystem(courseRepo);
    }
}