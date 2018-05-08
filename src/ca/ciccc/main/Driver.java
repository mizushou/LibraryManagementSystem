package ca.ciccc.main;

import ca.ciccc.controller.Library;
import ca.ciccc.model.*;
import ca.ciccc.view.LibraryInputReader;

import java.time.LocalDate;

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

        Customer c1 = new Customer("Mark", "Zuckerberg", LocalDate.parse("1984-05-14"));
        Customer c2 = new Customer("Jeffrey", "Bezos", LocalDate.parse("1964-01-12"));
        Customer c3 = new Customer("Drew", "Houston", LocalDate.parse("1983-03-04"));

        library.addCustomer(c1);
        library.addCustomer(c2);
        library.addCustomer(c3);

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

            int option = input.getIntInput("Input option");

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

                    int option1 = input.getIntInput("Input option");

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
                            System.out.println("5 : Children");
                            System.out.println();
                            int genreNum = input.getIntInput();
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

                            Author author = new Author(firstName, lastName, pseudonym, genre);

                            int publishedYear = input.getIntInput("Input Published Year [yyyy]");
                            int editon = input.getIntInput("Input Number of Edition [ex) 2nd -> 2]");
                            int numOfCopies = input.getIntInput("Input Number of copies");

                            Book[] books = new Book[numOfCopies];
                            for (int i = 0; i < numOfCopies; i++) {
                                books[i] = new Book(title, author, publishedYear, editon, genre);
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
                            System.out.println("1 : No sort");
                            System.out.println("2 : Sort by edition");
                            System.out.println("3 : Sort by year");
                            int diplayNum = input.getIntInput("Input Number of copies");
                            library.displayCatalogue(diplayNum);
                            break;
                        case 4: //Book - Remove
                            int id = input.getIntInput("Input Book ID");
                            library.removeBook(id);
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
                        case 3: //Customer - Activate
                            String activateId = input.getStringInput("Input Customer ID");
                            library.activateCustomer(activateId);
                            break;
                        case 4: //Customer - Inactivate
                            String inActivateId = input.getStringInput("Input Customer ID");
                            library.inActivateCustomer(inActivateId);
                            break;
                        case 5: //Customer - Remove
                            String removeId = input.getStringInput("Input Customer ID");
                            library.removeCustomer(removeId);
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

                    int option3 = input.getIntInput("Input option");

                    switch (option3) {
                        case 1: //Borrowing - Borrow
                            String customerId = input.getStringInput("Input Customer ID");
                            int numOfBorrowingBooks = input.getIntInput("Input Number of books to borrow [Max : 5 books ]");
                            if (numOfBorrowingBooks > 5) {
                                System.out.println("more than max");
                                break;
                            }
                            int[] bookIds = new int[numOfBorrowingBooks];
                            for (int i = 0; i < numOfBorrowingBooks; i++) {
                                bookIds[i] = input.getIntInput();
                            }
                            library.borrowBooks(customerId, bookIds);
                            break;
                        case 2: //Borrowing - Display
                            library.displayBorrowings();
                            break;
                        case 3: //Borrowing - Return
                            String id = input.getStringInput("Input Customer ID");
                            int numOfReturnBooks = input.getIntInput("Input Number of books to return");
                            int[] bookIds2 = new int[numOfReturnBooks];
                            for (int i = 0; i < numOfReturnBooks; i++) {
                                bookIds2[i] = input.getIntInput();
                            }
                            library.returnAllBooks(id, bookIds2);
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
