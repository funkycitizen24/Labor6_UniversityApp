package javafx.controllers;

import view.MyReaderClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Course;
import model.Student;
import model.Teacher;
import repository.TeacherRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherStart implements Initializable {
    List<Teacher> teachers;
    TeacherRepository teacherRepo;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Text idCoursesDetails;

    @FXML
    private TextField idTeacherName;

    /**
     * This method logs a teacher.
     * @throws IOException if any problems occur by loading the XML file
     */
    @FXML
    private void logInButtonAction() throws IOException {
        //if we find the teacher's name in the list of teachers, he/she can log in
        for(Teacher teacher:teacherRepo.getTeacherList())
        {
            if(idTeacherName.getText().equals(teacher.getFirstName() + " " + teacher.getLastName()))
            {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("TeacherMenu.fxml"));
                rootPane.getChildren().setAll(pane);
            }
        }
    }

    /**
     * This method displays the list with courses and their students.
     * @throws FileNotFoundException if the file doesn't exist
     */
    @FXML
    private void showAvailableCoursesAction() throws FileNotFoundException {
        idCoursesDetails.setText(MyReaderClass.coursesReader());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Course> coursesTeacher = new ArrayList<>();
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

        //repo for teachers
        teachers = new ArrayList<>();
        teacherRepo = new TeacherRepository(teachers);
        teacherRepo.getTeacherList().add(teacher);
    }
}