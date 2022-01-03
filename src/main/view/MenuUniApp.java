package view;

import controller.StudentController;
import model.Course;
import model.Student;
import repository.CourseRepository;
import repository.StudentRepository;
import repository.TeacherRepository;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MenuUniApp {
    CourseRepository courseRepository;
    StudentRepository studentRepository;
    TeacherRepository teacherRepository;
    private static RegistrationSystem registrationS;

    //Constructor
    public MenuUniApp(CourseRepository courseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.courseRepository =  courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        registrationS = new RegistrationSystem(courseRepository);
    }

    public void DisplayMenu(){
        int option = 1; //user's option
        do {
            Scanner myScanner = new Scanner(System.in); //Scanner class for user input

            //Show menu
            System.out.println("1. Register student to course");
            System.out.println("2. Print all students enrolled in a course");
            System.out.println("3. Display all available courses and their free places");
            System.out.println("4. Show all courses");
            System.out.println("5. Exit");
            System.out.println("Enter your option:");

            option = myScanner.nextInt(); //int value from User

            switch(option){
                case 1:
                    //Student registration for a course
                    //choosing course
                    System.out.println("Please choose a courseID:");
                    for (Course tempcourse:courseRepository.findAll()) //shows all courses with their IDs
                        System.out.println(tempcourse.getCourseId() + " " + tempcourse.getName());
                    Scanner courseId = new Scanner(System.in);
                    Course course = courseRepository.findOne(courseId.nextLong());

                    //choosing student for the course
                    System.out.println("Please choose a studentID:");
                    for (Student tempstud:studentRepository.findAll()) //show all students with their IDs
                        System.out.println(tempstud.getStudentId() + " " + tempstud.getFirstName() + " " + tempstud.getLastName());
                    Scanner studentId = new Scanner(System.in);
                    Student student = studentRepository.findOne(studentId.nextLong());

                    registrationS.register(course, student);
                    break;
                case 2:
                    //showing all students enrolled in a course
                    //choosing a course
                    System.out.println("Please choose a courseID:");
                    for (Course tempcourse:courseRepository.findAll()) //show all courses with their IDs
                        System.out.println(tempcourse.getCourseId() + " " + tempcourse.getName());
                    Scanner courseId2 = new Scanner(System.in);

                    Course course2 = courseRepository.findOne(courseId2.nextLong());
                    showStudents(registrationS.retrieveStudentsEnrolledForACourse(course2));
                    break;
                case 3:
                    //shows all available courses + their free places
                    registrationS.retrieveCoursesWithFreePlaces();
                    break;
                case 4:
                    //shows all courses
                    //new version (with stream/lambda)
                    System.out.println(registrationS.getAllCourses().stream()
                            .map(c -> String.format("Course ID: %s | Course Title: %s",c.getCourseId(),c.getName()))
                            .collect(Collectors.joining("\n")));
                    break;
                case 5:
                    break;
            }
        } while(option != 5);
    }

    /**
     * This method uses the student's view to show the content of students.
     * @param studs List<Student>
     */
    public void showStudents(List<Student> studs) {
        for (Student tempstud: studs) {
            StudentView sview = new StudentView();
            StudentController scontroller = new StudentController(tempstud, sview);
            scontroller.updateView();
        }
    }
}

