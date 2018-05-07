package ca.ciccc.controller;

import ca.ciccc.model.Book;
import ca.ciccc.model.Borrowing;
import ca.ciccc.model.Customer;
import ca.ciccc.util.IdGenerator;
import ca.ciccc.util.PseudoIsbnGenerator;

import java.time.LocalDate;
import java.util.*;

public class Library {

    private String name;
    private HashMap<String, Book> colOfBooks;
    private HashMap<String, Book> colOfBooksWithId;
    private HashMap<String, Customer> colOfCutomers;
    private ArrayList<Borrowing> colOfBorrowings;

    final private int LENDINGPERIODDAYS = 14;

    public Library() {
    }

    public Library(String name) {
        this.name = name;
        this.colOfBooks = new HashMap<>();
        this.colOfBooksWithId = new HashMap<>();
        this.colOfCutomers = new HashMap<>();
        this.colOfBorrowings = new ArrayList<>();
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

        for (int i = 1; i < book.getNumOfCopies() + 1; i++) {
            String id = book.getIsbn() + "-" + i;
            colOfBooksWithId.put(id, book);
        }

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

    public void displayBookWithId() {

        Set<String> ids = colOfBooksWithId.keySet();

        for (String id : ids) {
            Book b = colOfBooksWithId.get(id);
            String outputRecord = String.format(
                    "|%-22s|%-20s|%-20s|%-20s|%n",
                    id,
                    b.getTitle(),
                    b.getEdition(),
                    b.getAvailable() + "/" + b.getNumOfCopies()
            );
            System.out.printf(outputRecord);
        }

    }

    public void borrowAllBooks(String id, String[] isbns) {

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
        for (String isbn : isbns) {
            if (borrow.getBooks().containsKey(isbn)) {
                borrow.getBooks().put(isbn, borrow.getBooks().get(isbn) + 1);
            } else {
                borrow.getBooks().put(isbn, 1);
            }
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


            int numOfBorrowingBook = getSumOfBorrowingBook(borrow);

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

    public void returnAllBooks(String id, String[] isbns) {

        // 1. get the customer's borrowing list
        ArrayList<Borrowing> borrwingList;
        borrwingList = getBorrowingList(id);

        // 2.
        for (String isbn : isbns) {
            returnBook(isbn, borrwingList);
        }

        // 3.
        for (String isbn : isbns) {
            colOfBooks.get(isbn).setAvailable(colOfBooks.get(isbn).getAvailable() + 1);
        }

        // 4.
        for (Borrowing borrow : borrwingList) {
            if (getSumOfBorrowingBook(borrow) != 0) continue;
            borrow.setFinished(true);
        }

    }

    private int getSumOfBorrowingBook(Borrowing borrow) {

        Iterator<String> it = borrow.getBooks().keySet().iterator();
        int sum = 0;
        while (it.hasNext()) {
            sum += borrow.getBooks().get(it.next());
        }
        return sum;

    }

    private ArrayList<Borrowing> getBorrowingList(String id) {

        ArrayList<Borrowing> borrwingList = new ArrayList<>();
        for (Borrowing borrow : colOfBorrowings) {
            if (!borrow.getCustomer().getId().equals(id)) continue;
            if (!borrow.isFinished()) borrwingList.add(borrow);
        }

        return borrwingList;

    }

    private void returnBook(String isbn, ArrayList<Borrowing> borrowingList) {

        for (Borrowing borrow : borrowingList) {
            if (borrow.getBooks().containsKey(isbn)) borrow.getBooks().put(isbn, borrow.getBooks().get(isbn) - 1);
        }

    }

    public String getName() {
        return name;
    }

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
