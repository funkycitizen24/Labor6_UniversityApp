package exceptions;

public class TeacherNotFoundException extends Exception {
    // Constructor that accepts a message
    public TeacherNotFoundException()
    {
        super("Teacher not found in repository.");
    }
}
