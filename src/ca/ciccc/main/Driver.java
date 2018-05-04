package ca.ciccc.main;

import ca.ciccc.controller.Library;
import ca.ciccc.model.Author;
import ca.ciccc.model.Book;
import ca.ciccc.model.Customer;
import ca.ciccc.model.Genre;
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
                    System.out.println("2 : Display");
                    System.out.println("3 : Remove");
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
                        case 3: //Book - Remove
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
                case 3:
                    break;
                case 0:
                    isRunning = false;
                    break;
            }

        }
    }
}
