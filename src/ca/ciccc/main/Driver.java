package ca.ciccc.main;

import ca.ciccc.controller.Library;
import ca.ciccc.model.Customer;
import ca.ciccc.view.LibraryInputReader;

import java.time.LocalDate;

public class Driver {

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.run();
    }

    public void run() {

        boolean isRunning = true;
        Library library = new Library("VPL");

        // Views
        LibraryInputReader input = new LibraryInputReader();


        while (isRunning) {

            System.out.println();
            System.out.println("-------------");
            System.out.println("Main menu");
            System.out.println("-------------");
            System.out.println("1 : Book");
            System.out.println("2 : Customer");
            System.out.println("3 : Borrowing");
            System.out.println("0 : Exit");
            System.out.println();

            int option = input.getIntInput("Input option");


            switch (option) {
                case 1:
                    break;
                case 2: //Customer
                    System.out.println();
                    System.out.println("-------------");
                    System.out.println("Customer menu");
                    System.out.println("-------------");
                    System.out.println("1 : Add");
                    System.out.println("2 : Display");
                    System.out.println("3 : Remove");
                    System.out.println();

                    int option2 = input.getIntInput("Input option");

                    switch (option2) {
                        case 1: //Customer - Add
                            String firstName = input.getStringInput("Input First Name");
                            String lastName = input.getStringInput("Input Last Name");
                            LocalDate localDate = input.getLocalDate("Input Birth of date [yyyy-mm-dd]");

                            Customer customer = new Customer(firstName, lastName, localDate);
                            library.addCustomer(customer);
                            break;
                        case 2: //Customer - Display
                            library.displayCustomers();
                            break;
                        case 3: //Customer - Remove
                            String id = input.getStringInput("Input Customer ID");
                            library.removeCustomer(id);
                            break;
                    }
                case 3:
                    break;
                case 0:
                    isRunning = false;
                    break;
            }

        }
    }
}
