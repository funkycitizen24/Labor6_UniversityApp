package exceptions;

public class StudentNotFoundException extends Exception{

    // Constructor that accepts a message
    public StudentNotFoundException()
    {
        super("Student not found in repository.");
    }
}
