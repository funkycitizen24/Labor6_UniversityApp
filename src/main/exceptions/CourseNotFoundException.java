package exceptions;

public class CourseNotFoundException extends Exception{

    // Constructor that accepts a message
    public CourseNotFoundException()
    {
        super("Course not found in repository.");
    }
}
