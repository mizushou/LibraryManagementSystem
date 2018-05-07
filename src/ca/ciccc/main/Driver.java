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

        Book b1 = new Book("Hurry Potter", a1, 1997, 1, Genre.Children, 5);
        Book b2 = new Book("Romeo and Juliet", a2, 1994, 4, Genre.Fiction, 3);
        Book b3 = new Book("Steve Jobs", a3, 2011, 3, Genre.Biography, 2);

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

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
            System.out.println("1 : Catalogue");
            System.out.println("2 : Customer");
            System.out.println("3 : Borrowing");
            System.out.println("0 : Exit");
            System.out.println();

            int option = input.getIntInput("Input option");

            switch (option) {
                case 1: //Book
                    System.out.println();
                    System.out.println("-------------");
                    System.out.println("Catalogue");
                    System.out.println("-------------");
                    System.out.println("1 : Add");
                    System.out.println("2 : Display Catalogue");
                    System.out.println("3 : Display Catalogue With ID");
                    System.out.println("4 : Remove");
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

                            Book book = new Book(title, author, publishedYear, editon, genre, numOfCopies);
                            library.addBook(book);
                            break;
                        case 2: //Book - Display
                            library.displayBook();
                            break;
                        case 3:
                            library.displayBookWithId();
                            break;
                        case 4: //Book - Remove
                            String isbn = input.getStringInput("Input Book ISBN");
                            library.removeBook(isbn);
                            break;
                    }
                    break;
                case 2: //Customer
                    System.out.println();
                    System.out.println("-------------");
                    System.out.println("Customer");
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
                            String[] isbns = new String[numOfBorrowingBooks];
                            for (int i = 0; i < numOfBorrowingBooks; i++) {
                                isbns[i] = input.getStringInput();
                            }
                            library.borrowAllBooks(customerId, isbns);
                            break;
                        case 2: //Borrowing - Display
                            library.displayBorrowings();
                            break;
                        case 3: //Borrowing - Return
                            String id = input.getStringInput("Input Customer ID");
                            int numOfReturnBooks = input.getIntInput("Input Number of books to return");
                            String[] isbns2 = new String[numOfReturnBooks];
                            for (int i = 0; i < numOfReturnBooks; i++) {
                                isbns2[i] = input.getStringInput();
                            }
                            library.returnAllBooks(id, isbns2);
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
