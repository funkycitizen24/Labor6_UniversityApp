package controller;

import model.Course;
import model.Person;
import model.Student;
import view.CourseView;
import java.util.List;

public class CourseController {
    private Course model;
    private CourseView view;

    //Constructor
    public CourseController(Course model, CourseView view){
        this.model = model;
        this.view = view;
    }

    //Getters and Setters

    public long getCourseCourseId(){
        return model.getCourseId();
    }

    public void setCourseCourseId(long courseId){
        model.setCourseId(courseId);
    }

    public String getCourseName(){
        return model.getName();
    }

    public void setCourseName(String name){
        model.setName(name);
    }

    public Person getCourseTeacher() {
        return model.getTeacher();
    }

    public void setCourseTeacher(Person teacher) {
        model.setTeacher(teacher);
    }

    public int getCourseMaxEnrollment() {
        return model.getMaxEnrollment();
    }

    public void setCourseMaxEnrollment(int maxEnrollment) {
        model.setMaxEnrollment(maxEnrollment);
    }

    public List<Student> getCourseStudentsEnrolled() {
        return model.getStudentsEnrolled();
    }

    public void setCourseStudentsEnrolled(List<Student> studentsEnrolled) {
        model.setStudentsEnrolled(studentsEnrolled);
    }

    public int getCourseCredits() {
        return model.getCredits();
    }

    public void setCourseCredits(int credits) {
        model.setCredits(credits);
    }

    //Course's details
    public void updateView(){
        view.printCourseDetails(model);
    }
}