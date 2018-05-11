package ca.ciccc.main;

import ca.ciccc.controller.Library;
import ca.ciccc.exception.*;
import ca.ciccc.model.*;
import ca.ciccc.view.LibraryInputReader;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class Driver {

    private Library library;
    private LibraryInputReader input;

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.initialize();
        driver.run();
    }

    public void initialize() {

        // Views
        input = new LibraryInputReader();

        // Settings
        System.out.println("Setting");
        String nameOfLibrary = input.getStringInput("Input Library name");
        library = new Library("nameOfLibrary");

        System.out.println("=============");
        System.out.println("[" + nameOfLibrary + "] SYSTEM");
        System.out.println("=============");


        try {
            Author a1 = new Author("Joanne", "Rowling", "J. K. Rowling", Genre.Fiction);
            Author a2 = new Author("William", "Shakespeare", "", Genre.Fiction);
            Author a3 = new Author("Walter", "Isaacson", "", Genre.NonFiction);
            Book[] books1 = new Book[3];
            Book[] books2 = new Book[2];
            Book[] books3 = new Book[4];
            books1[0] = new Book("Hurry Potter", a1, 1997, 1, Genre.Children);
            books1[1] = new Book("Hurry Potter", a1, 1997, 1, Genre.Children);
            books1[2] = new Book("Hurry Potter", a1, 1997, 1, Genre.Children);
            books2[0] = new Book("Romeo and Juliet", a2, 1994, 4, Genre.Fiction);
            books2[1] = new Book("Romeo and Juliet", a2, 1994, 4, Genre.Fiction);
            books3[0] = new Book("Steve Jobs", a3, 2011, 3, Genre.Biography);
            books3[1] = new Book("Steve Jobs", a3, 2011, 3, Genre.Biography);
            books3[2] = new Book("Steve Jobs", a3, 2011, 3, Genre.Biography);
            books3[3] = new Book("Steve Jobs", a3, 2011, 3, Genre.Biography);

            library.addBook(books1);
            library.addBook(books2);
            library.addBook(books3);
        } catch (InValidArgumentException | InValidDateOfBirthException | InValidPublishedYearException e) {
            System.out.println(e.getMessage());
        }

        try {
            Customer c1 = new Customer("Mark", "Zuckerberg", LocalDate.parse("1984-05-14"));
            Customer c2 = new Customer("Jeffrey", "Bezos", LocalDate.parse("1964-01-12"));
            Customer c3 = new Customer("Drew", "Houston", LocalDate.parse("1983-03-04"));
            library.addCustomer(c1);
            library.addCustomer(c2);
            library.addCustomer(c3);
        } catch (InValidArgumentException | InValidDateOfBirthException e) {
            System.out.println(e.getMessage());
        }

    }

    public void run() {

        boolean isRunning = true;

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

            int option = 99999;
            try {
                option = input.getOptionInput("Input option", "0", "3");
            } catch (InValidOptionException e) {
                System.out.println(e.getMessage());
            }


            switch (option) {
                case 1: //Book
                    System.out.println();
                    System.out.println("-------------");
                    System.out.println("Book");
                    System.out.println("-------------");
                    System.out.println("1 : Add Book");
                    System.out.println("2 : Display All Books");
                    System.out.println("3 : Display Catalogue");
                    System.out.println("4 : Remove Book");
                    System.out.println();

                    int option1;
                    try {
                        option1 = input.getOptionInput("Input option", "1", "4");
                    } catch (InValidOptionException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    switch (option1) {

                        case 1: //Book - Add

                            String title = input.getStringInput("Input Title");
                            String firstName = input.getStringInput("Input Author's First Name");
                            String lastName = input.getStringInput("Input Author's Last Name");
                            String pseudonym = input.getStringInput("Input Author's Pseudonym");

                            System.out.println("Input Author's Specialty[Genre]");
                            System.out.println("-------------");
                            System.out.println("Select Genre");
                            System.out.println("-------------");
                            System.out.println("1 : Fiction");
                            System.out.println("2 : Non Fiction");
                            System.out.println("3 : Biography");
                            System.out.println("4 : History");
                            System.out.print("5 : Children");

                            int genreNum;
                            try {
                                genreNum = input.getOptionInput("", "1", "5");
                            } catch (InValidOptionException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                            Genre genre = null;
                            switch (genreNum) {
                                case 1:
                                    genre = Genre.Fiction;
                                    break;
                                case 2:
                                    genre = Genre.NonFiction;
                                    break;
                                case 3:
                                    genre = Genre.Biography;
                                    break;
                                case 4:
                                    genre = Genre.History;
                                    break;
                                case 5:
                                    genre = Genre.Children;
                                    break;
                            }

                            Author author = null;
                            try {
                                author = new Author(firstName, lastName, pseudonym, genre);
                            } catch (InValidArgumentException | InValidDateOfBirthException e) {
                                e.getMessage();
                                break;
                            }

                            int publishedYear = input.getIntInput("Input Published Year [yyyy]");
                            int edition = input.getIntInput("Input Number of Edition [ex) 2nd -> 2]");
                            int numOfCopies = input.getIntInput("Input Number of copies");

                            if (numOfCopies <= 0) {
                                System.out.println("invalid number");
                                break;
                            }

                            Book[] books = new Book[numOfCopies];
                            try {
                                for (int i = 0; i < numOfCopies; i++) {

                                    books[i] = new Book(title, author, publishedYear, edition, genre);
                                }
                            } catch (InValidPublishedYearException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                            library.addBook(books);
                            break;
                        case 2: //Book - Display
                            library.displayBook();
                            break;
                        case 3:
                            System.out.println("Select Display option");
                            System.out.println("-------------");
                            System.out.println("Display Option");
                            System.out.println("-------------");
                            System.out.println("1 : Sort by edition");
                            System.out.println("2 : Sort by year");

                            int displayNum;
                            try {
                                displayNum = input.getOptionInput("Input display option", "1", "2");
                            } catch (InValidOptionException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                            library.displayCatalogue(displayNum);
                            break;
                        case 4: //Book - Remove

                            int bookId;
                            try {
                                bookId = input.getBookIdInput("Input Book ID");
                            } catch (InValidBookIdException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                            library.removeBook(bookId);
                            break;
                    }
                    break;
                case 2: //Customer
                    System.out.println();
                    System.out.println("-------------");
                    System.out.println("Customer");
                    System.out.println("-------------");
                    System.out.println("1 : Add Customer");
                    System.out.println("2 : Display Customers");
                    System.out.println("3 : Activate Customers");
                    System.out.println("4 : Inactivate Customers");
                    System.out.println("5 : Remove Customer");
                    System.out.println();

                    int option2;
                    try {
                        option2 = input.getOptionInput("Input option", "1", "5");
                    } catch (InValidOptionException e) {
                        System.out.println(e.getMessage());
                        break;
                    }


                    switch (option2) {
                        case 1: //Customer - Add

                            //First Name
                            String firstName = input.getStringInput("Input First Name");
                            //Last Name
                            String lastName = input.getStringInput("Input Last Name");

                            //Birth of date
                            LocalDate localDate = null;
                            try {
                                localDate = input.getLocalDateInput("Input Birth of date [yyyy-mm-dd]");
                            } catch (DateTimeParseException e) {
                                System.out.println("Date Format Error");
                                System.out.println("Format is " + "[yyyy-mm-dd]");
                                break;
                            }

                            //Create Customer instance
                            Customer customer;
                            try {
                                customer = new Customer(firstName, lastName, localDate);
                            } catch (InValidArgumentException | InValidDateOfBirthException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                            //Add Customer
                            library.addCustomer(customer);
                            break;
                        case 2: //Customer - Display
                            library.displayCustomers();
                            break;
                        case 3: //Customer - Activate
                            String activateCustomerId = null;
                            try {
                                activateCustomerId = input.getCustomerIdInput("Input Customer ID");
                            } catch (InValidCustomerIdException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            library.activateCustomer(activateCustomerId);
                            break;
                        case 4: //Customer - Inactivate
                            String inActivateCustomerId = null;
                            try {
                                inActivateCustomerId = input.getCustomerIdInput("Input Customer ID");
                            } catch (InValidCustomerIdException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            library.inActivateCustomer(inActivateCustomerId);
                            break;
                        case 5: //Customer - Remove
                            String removeCustomerId = null;
                            try {
                                removeCustomerId = input.getCustomerIdInput("Input Customer ID");
                            } catch (InValidCustomerIdException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            library.removeCustomer(removeCustomerId);
                            break;
                    }
                    break;
                case 3: //Borrowing
                    System.out.println();
                    System.out.println("-------------");
                    System.out.println("Borrowing");
                    System.out.println("-------------");
                    System.out.println("1 : Borrow");
                    System.out.println("2 : Display");
                    System.out.println("3 : Return");
                    System.out.println();

                    int option3;
                    try {
                        option3 = input.getOptionInput("Input option", "1", "3");
                    } catch (InValidOptionException e) {
                        System.out.println(e.getMessage());
                        break;
                    }


                    switch (option3) {
                        case 1: //Borrowing - Borrow
                            String borrowingCustomerId = null;
                            try {
                                borrowingCustomerId = input.getCustomerIdInput("Input Customer ID");
                            } catch (InValidCustomerIdException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            int numOfBorrowingBooks = input.getIntInput("Input Number of books to borrow [Max : 5 books ]");
                            if (numOfBorrowingBooks > 5) {
                                System.out.println("More than max");
                                break;
                            }
                            int[] bookIds = new int[numOfBorrowingBooks];
                            System.out.println("Input [" + numOfBorrowingBooks + "] books ID");
                            try {
                                for (int i = 0; i < numOfBorrowingBooks; i++) {
                                    bookIds[i] = input.getBookIdInput("");
                                }
                            } catch (InValidBookIdException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                            library.borrowBooks(borrowingCustomerId, bookIds);
                            break;
                        case 2: //Borrowing - Display
                            library.displayBorrowings();
                            break;
                        case 3: //Borrowing - Return
                            String returnCustomerId = null;
                            try {
                                returnCustomerId = input.getCustomerIdInput("Input Customer ID");
                            } catch (InValidCustomerIdException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            int numOfReturnBooks = input.getIntInput("Input Number of books to return");
                            int[] bookIds2 = new int[numOfReturnBooks];
                            System.out.println("Input [" + numOfReturnBooks + "] books ID");
                            try {
                                for (int i = 0; i < numOfReturnBooks; i++) {
                                    bookIds2[i] = input.getBookIdInput("");
                                }
                            } catch (InValidBookIdException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            library.returnAllBooks(returnCustomerId, bookIds2);
                            break;
                    }
                    break;
                case 0:
                    isRunning = false;
                    break;
            }

        }
    }
}
