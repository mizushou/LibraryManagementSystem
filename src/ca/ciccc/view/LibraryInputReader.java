package ca.ciccc.view;

import java.time.LocalDate;
import java.util.Scanner;

public class LibraryInputReader extends InputReader {
    private Scanner scanner;

    public LibraryInputReader() {
        scanner = new Scanner(System.in);
    }

    public int getIntInput(String message) {
        System.out.println(message);
        return super.getIntInput();
    }

    public double getDoubleInput(String message) {
        System.out.println(message);
        return super.getDoubleInput();
    }

    public String getStringInput(String message) {
        System.out.println(message);
        return super.getStringInput();
    }

    public LocalDate getLocalDate(String message) {
        System.out.println(message);
        String dateString = super.getStringInput();
        /* ToDo validate date format */
        return LocalDate.parse(dateString);
    }

}
