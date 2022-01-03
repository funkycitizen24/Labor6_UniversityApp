package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyReaderClass {
    /**
     * This method reads the id of the student who is logged in from file.
     * @return id as String
     * @throws FileNotFoundException if the file doesn't exist
     */
    public static String idReader() throws FileNotFoundException {
        File myObj = new File("C:\\Users\\mirun\\OneDrive\\Desktop\\MAP\\UniApp\\src\\main\\StudentID.txt");
        Scanner myReader = new Scanner(myObj);
        String data = myReader.nextLine();
        myReader.close();
        return data;
    }

    /**
     * This method reads line by line the file with courses in order to form a string so that it can be used to set the text in the window.
     * @return list of courses with students as String
     * @throws FileNotFoundException if the file doesn't exist
     */
    public static String coursesReader() throws FileNotFoundException {
        File myObj = new File("C:\\Users\\mirun\\OneDrive\\Desktop\\MAP\\UniApp\\src\\main\\CourseDetails.txt");
        Scanner myReader = new Scanner(myObj);
        StringBuilder details= new StringBuilder(); //we store here the content of the file
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            details.append(data).append("\n");
        }
        myReader.close();
        return details.toString();
    }
}