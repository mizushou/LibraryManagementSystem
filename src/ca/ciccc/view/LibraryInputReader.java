package ca.ciccc.view;

import ca.ciccc.exception.InValidCustomerIdException;

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
        return LocalDate.parse(dateString);
    }

    public String getCustomerIdInput(String message) throws InValidCustomerIdException {

        System.out.println(message);
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

}
