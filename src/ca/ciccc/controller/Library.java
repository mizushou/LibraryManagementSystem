package ca.ciccc.controller;

import ca.ciccc.model.Book;
import ca.ciccc.model.Borrowing;
import ca.ciccc.model.Customer;
import ca.ciccc.util.IdGenerator;
import ca.ciccc.util.PseudoIsbnGenerator;
import com.sun.istack.internal.NotNull;


import java.time.LocalDate;
import java.util.*;

public class Library {

    private String name;
    private HashMap<String, Book> colOfBooks;
    private HashMap<String, Customer> colOfCutomers;
    private ArrayList<Borrowing> colOfBorrowings;

    final private int LENDINGPERIODDAYS = 14;

    public Library() {
    }

    public Library(String name) {
        this.name = name;
        this.colOfBooks = new HashMap<>();
        this.colOfCutomers = new HashMap<>();
        this.colOfBorrowings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCustomer(Customer customer) {

        String id;
        boolean isDuplicated = false;

        do {
            id = new IdGenerator().generateId();
            isDuplicated = colOfCutomers.keySet().contains(id);
        } while (isDuplicated);

        // assign id and activate
        customer.setId(id);
        customer.setActive(true);

        colOfCutomers.put(id, customer);
    }

    public void removeCustomer(String id) {

        // remove customer from customer collection
        colOfCutomers.remove(id);
    }

    public void displayCustomers() {


        String outputTitle = String.format("|%-5s|%-20s|%-20s|%-20s|%-20s|%n", "ID", "First Name", "Last Name", "Date of Birth", "State");
        String outputHRule = String.format("+%-5s+%s+%s+%s+%s+%n", "-----", "--------------------", "--------------------", "--------------------", "--------------------");
        System.out.println();
        System.out.printf(outputHRule);
        System.out.printf(outputTitle);
        System.out.printf(outputHRule);

        Iterator<String> it = colOfCutomers.keySet().iterator();

        while (it.hasNext()) {
            Customer c = colOfCutomers.get(it.next());
            String outputRecord = String.format(
                    "|%-5s|%-20s|%-20s|%-20s|%-20s|%n",
                    c.getId(),
                    c.getFirstName(),
                    c.getLastName(),
                    c.getDateOfBirth().toString(),
                    c.isActive() ? "active" : "inactive"
            );
            System.out.printf(outputRecord);
        }

        System.out.printf(outputHRule);

    }

    public void addBook(Book book) {

        String isbn;
        boolean isDuplicated = false;

        do {
            isbn = new PseudoIsbnGenerator().generatePseudoIsbn(book.getEdition());
            isDuplicated = colOfBooks.keySet().contains(isbn);
        } while (isDuplicated);

        // assign isbn and set available
        book.setIsbn(isbn);
        book.setAvailable(book.getNumOfCopies());

        colOfBooks.put(isbn, book);
    }

    public void removeBook(String isbn) {

        colOfBooks.remove(isbn);

    }

    public void displayBook() {

        String outputTitle = String.format("|%-22s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%n", "ISBN", "Title", "Author", "Published Year", "Edition", "Genre", "Available");
        String outputHRule = String.format("+%s+%s+%s+%s+%s+%s+%s+%n", "----------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------");
        System.out.println();
        System.out.printf(outputHRule);
        System.out.printf(outputTitle);
        System.out.printf(outputHRule);

        Iterator<String> it = colOfBooks.keySet().iterator();

        while (it.hasNext()) {
            Book b = colOfBooks.get(it.next());
            String outputRecord = String.format(
                    "|%-22s|%-20s|%-20s|%20s|%20s|%-20s|%20s|%n",
                    b.getIsbn(), b.getTitle(),
                    b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName(),
                    b.getPublishedYear(), b.getEdition(),
                    b.getGenre(),
                    b.getAvailable() + "/" + b.getNumOfCopies()
            );
            System.out.printf(outputRecord);
        }

        System.out.printf(outputHRule);

    }

    public void borrowBook(String id, String[] isbns) {

        Customer c = colOfCutomers.get(id);

        //1. check customer active
        if (!c.isActive()) {
            return;
        }
        //2. check book available
        for (String isbn : isbns) {
            if (colOfBooks.get(isbn).getAvailable() > 0) continue;
            return;
        }

        //3. add borrow
        LocalDate borrowedDate = LocalDate.now();
        LocalDate returnDate = borrowedDate.plusDays(LENDINGPERIODDAYS);
        Borrowing borrow = new Borrowing(c, borrowedDate, returnDate);
        for (int i = 0; i < isbns.length; i++) {
            borrow.getBooks()[i] = colOfBooks.get(isbns[i]);
        }

        colOfBorrowings.add(borrow);

        //4. update the value of copies available from the book
        for (String isbn : isbns) {
            colOfBooks.get(isbn).setAvailable(colOfBooks.get(isbn).getAvailable() - 1);
        }
    }

    public void displayBorrowings() {

        String outputTitle = String.format("|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%n", "Borrowed Date", "Customer ID", "Name", "Number of Books", "Return Date", "Finished");
        String outputHRule = String.format("+%s+%s+%s+%s+%s+%s+%n", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------");
        System.out.println();
        System.out.printf(outputHRule);
        System.out.printf(outputTitle);
        System.out.printf(outputHRule);

        Iterator<Borrowing> it = colOfBorrowings.iterator();

        while (it.hasNext()) {
            Borrowing borrow = it.next();

            int numOfBorrowingBook = 0;
            for (int i = 0; i < borrow.getBooks().length; i++) {
                if (borrow.getBooks()[i] == null) {
                    break;
                }
                numOfBorrowingBook++;
            }

            String outputRecord = String.format(
                    "|%-20s|%-20s|%-20s|%20s|%-20s|%-20s|%n",
                    borrow.getBorrowedDate().toString(),
                    borrow.getCustomer().getId(),
                    borrow.getCustomer().getFirstName() + " " + borrow.getCustomer().getLastName(),
                    numOfBorrowingBook,
                    borrow.getReturnDate(),
                    borrow.isFinished()
            );
            System.out.printf(outputRecord);
        }

        System.out.printf(outputHRule);

    }

    public void returnBook(String id, String[] isbns) {

        // 1. get the customer's borrowing list
        ArrayList<Borrowing> borrwingList = new ArrayList<>();
        borrwingList = getBorrowingList(id);

        // 2. get the customer's borrowing books list

    }

    private ArrayList<Borrowing> getBorrowingList(String id) {

        ArrayList<Borrowing> borrwingList = new ArrayList<>();
        for (Borrowing borrow : colOfBorrowings) {
            if (!borrow.getCustomer().getId().equals(id)) continue;
            borrwingList.add(borrow);
        }

        return borrwingList;

    }

//    private void getBorrowingBookList(ArrayList<Borrowing> borrwingList, String isbn) {
//
//        HashMap<String, Book> borroingBookList = new HashMap<>();
//
//        for (Borrowing borrow : borrwingList) {
//
//            for (int i = 0; i <; i++) {
//
//                if (borrow.getBooks()[i] == null) break;
//
//                if (!borrow.getBooks()[i].getIsbn().equals(isbn)) continue;
//                borrow.getBooks()[i] = null;
//                colOfBooks.get(isbn).setAvailable(colOfBooks.get(isbn).getAvailable() + 1);
//
//            }
//
//        }
//
//    }


    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Book> getColOfBooks() {
        return colOfBooks;
    }

    public void setColOfBooks(HashMap<String, Book> colOfBooks) {
        this.colOfBooks = colOfBooks;
    }

    public HashMap<String, Customer> getColOfCutomers() {
        return colOfCutomers;
    }

    public void setColOfCutomers(HashMap<String, Customer> colOfCutomers) {
        this.colOfCutomers = colOfCutomers;
    }

    public ArrayList<Borrowing> getColOfBorrowings() {
        return colOfBorrowings;
    }

    public void setColOfBorrowings(ArrayList<Borrowing> colOfBorrowings) {
        this.colOfBorrowings = colOfBorrowings;
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                '}';
    }
}
