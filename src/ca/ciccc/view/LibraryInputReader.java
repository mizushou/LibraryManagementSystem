package ca.ciccc.view;

import ca.ciccc.exception.InValidBookIdException;
import ca.ciccc.exception.InValidCustomerIdException;
import ca.ciccc.exception.InValidOptionException;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryInputReader extends InputReader {
    private Scanner scanner;

    public LibraryInputReader() {

        scanner = new Scanner(System.in);

    }

    public int getIntInput(String message) {
        System.out.println(message);
        System.out.print("> ");
        return super.getIntInput();
    }

    public double getDoubleInput(String message) {
        System.out.println(message);
        System.out.print("> ");
        return super.getDoubleInput();
    }

    public String getStringInput(String message) {
        System.out.println(message);
        System.out.print("> ");
        return super.getStringInput();
    }

    public LocalDate getLocalDateInput(String message) {
        System.out.println(message);
        System.out.print("> ");
        String dateString = super.getStringInput();
        return LocalDate.parse(dateString);
    }

    public String getCustomerIdInput(String message) throws InValidCustomerIdException {

        System.out.println(message);
        System.out.print("> ");
        String customrId = super.getStringInput();
        if (isValidCustomerId(customrId)) {
            return customrId;
        } else {
            throw new InValidCustomerIdException("Invalid Customer ID");
        }

    }

    private boolean isValidCustomerId(String customerId) {
        String regex = "[A-Z]{2}[0-9]{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(customerId);
        return matcher.matches();
    }

    public int getOptionInput(String message, String min, String max) throws InValidOptionException {

        System.out.println(message);
        System.out.print("> ");
        int option = super.getIntInput();
        if (isValidOption(option, min, max)) {
            return option;
        } else {
            throw new InValidOptionException("Invalid Option");
        }

    }

    private boolean isValidOption(int option, String min, String max) {
        String regex = "[" + min + "-" + max + "]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(option));
        return matcher.matches();
    }

    public int getBookIdInput(String message) throws InValidBookIdException {

        System.out.println(message);
        System.out.print("> ");
        int bookId = super.getIntInput();
        if (isValidBookId(bookId)) {
            return bookId;
        } else {
            throw new InValidBookIdException("Invalid BookID");
        }
    }

    private boolean isValidBookId(int bookId) {

        String regex = "[1-9][0-9]{4,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(bookId));
        return matcher.matches();

    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
