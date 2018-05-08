package ca.ciccc.controller;

import ca.ciccc.model.Book;
import ca.ciccc.model.Borrowing;
import ca.ciccc.model.Catalogue;
import ca.ciccc.model.Customer;
import ca.ciccc.util.IdGenerator;
import ca.ciccc.util.PseudoIsbnGenerator;

import java.time.LocalDate;
import java.util.*;

public class Library {

    private String name;
    private HashMap<Integer, Book> colOfBooks;
    private HashMap<String, Catalogue> colOfCatalogue;
    private HashMap<String, Customer> colOfCustomers;
    private ArrayList<Borrowing> colOfBorrowings;

    final private int LENDINGPERIODDAYS = 14;

    public Library() {
    }

    public Library(String name) {
        this.name = name;
        this.colOfBooks = new HashMap<>();
        this.colOfCatalogue = new HashMap<>();
        this.colOfCustomers = new HashMap<>();
        this.colOfBorrowings = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {

        String id;
        boolean isDuplicated = false;

        do {
            id = new IdGenerator().generateId();
            isDuplicated = colOfCustomers.keySet().contains(id);
        } while (isDuplicated);

        // assign id and activate
        customer.setId(id);
        customer.setActive(true);

        colOfCustomers.put(id, customer);
    }

    public void removeCustomer(String id) {

        // remove customer from customer collection
        colOfCustomers.remove(id);
    }

    public void displayCustomers() {


        String outputTitle = String.format("|%-5s|%-20s|%-20s|%-20s|%-20s|%n", "ID", "First Name", "Last Name", "Date of Birth", "State");
        String outputHRule = String.format("+%-5s+%s+%s+%s+%s+%n", "-----", "--------------------", "--------------------", "--------------------", "--------------------");
        System.out.println();
        System.out.printf(outputHRule);
        System.out.printf(outputTitle);
        System.out.printf(outputHRule);

        Iterator<String> it = colOfCustomers.keySet().iterator();

        while (it.hasNext()) {
            Customer c = colOfCustomers.get(it.next());
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

    public void activateCustomer(String customerID) {
        colOfCustomers.get(customerID).setActive(true);
    }

    public void inActivateCustomer(String customerID) {
        colOfCustomers.get(customerID).setActive(false);
    }

    public void addBook(Book[] books) {

        String isbn;
        boolean isDuplicated;

        do {
            isbn = new PseudoIsbnGenerator().generatePseudoIsbn(books[0].getEdition());
            isDuplicated = colOfBooks.keySet().contains(isbn);
        } while (isDuplicated);

        for (int i = 0; i < books.length; i++) {

            // assign isbn and set available
            books[i].setIsbn(isbn);
            books[i].setAvailable(true);

            colOfBooks.put(books[i].getId(), books[i]);

        }

        // refresh catalogue table
        makeColOfCatalogue();

    }

    public void removeBook(int id) {

        colOfBooks.remove(id);

        // refresh catalogue table
        makeColOfCatalogue();

    }

    public void displayBook() {

        String outputTitle = String.format("|%-20s|%-22s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%n", "ID", "ISBN", "Title", "Author", "Published Year", "Edition", "Genre", "Available");
        String outputHRule = String.format("+%s+%s+%s+%s+%s+%s+%s+%s+%n", "--------------------", "----------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------");
        System.out.println();
        System.out.printf(outputHRule);
        System.out.printf(outputTitle);
        System.out.printf(outputHRule);

        Iterator<Integer> it = colOfBooks.keySet().iterator();

        while (it.hasNext()) {
            Book b = colOfBooks.get(it.next());
            String outputRecord = String.format(
                    "|%20s|%-22s|%-20s|%-20s|%20s|%20s|%-20s|%20s|%n",
                    b.getId(),
                    b.getIsbn(),
                    b.getTitle(),
                    b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName(),
                    b.getPublishedYear(),
                    b.getEdition(),
                    b.getGenre(),
                    b.getAvailable()
            );
            System.out.printf(outputRecord);
        }

        System.out.printf(outputHRule);

    }

    public void displayCatalogue(int option) {

        switch (option) {
            case 1:
                displayCatalogueWithoutSorting();
                break;
            case 2:
                displayCatalogueSortedBy​Editon();
                break;
            case 3:
                displayCatalogueSortedBy​PublishedYear();
                break;
        }

    }

    private void displayCatalogueWithoutSorting() {

        String outputTitle = String.format("|%-22s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%n", "ISBN", "Title", "Author", "Published Year", "Edition", "Genre", "Available");
        String outputHRule = String.format("+%s+%s+%s+%s+%s+%s+%s+%n", "----------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------");
        System.out.println();
        System.out.printf(outputHRule);
        System.out.printf(outputTitle);
        System.out.printf(outputHRule);

        Set<String> keys = colOfCatalogue.keySet();
        ArrayList<String> keyList = new ArrayList<>(keys);
        Iterator<String> it = keyList.iterator();
        while (it.hasNext()) {
            String isbn = it.next();
            Catalogue c = colOfCatalogue.get(isbn);

            String outputRecord = String.format(
                    "|%-22s|%-20s|%-20s|%20s|%20s|%-20s|%20s|%n",
                    c.getIsbn(),
                    c.getTitle(),
                    c.getAuthor().getFirstName() + " " + c.getAuthor().getLastName(),
                    c.getPublishedYear(),
                    c.getEdition(),
                    c.getGenre(),
                    c.getAvailable() + "/" + c.getNumOfCopies()
            );
            System.out.printf(outputRecord);
        }

        System.out.printf(outputHRule);

    }

    private void displayCatalogueSortedBy​Editon() {

        Collection<Catalogue> catalogues = colOfCatalogue.values();
        ArrayList<Catalogue> catalogueList = new ArrayList<>(catalogues);

        Collections.sort(catalogueList);

        String outputTitle = String.format("|%-22s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%n", "ISBN", "Title", "Author", "Published Year", "Edition", "Genre", "Available");
        String outputHRule = String.format("+%s+%s+%s+%s+%s+%s+%s+%n", "----------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------");
        System.out.println();
        System.out.printf(outputHRule);
        System.out.printf(outputTitle);
        System.out.printf(outputHRule);

        for (Catalogue c : catalogueList) {
            String outputRecord = String.format(
                    "|%-22s|%-20s|%-20s|%20s|%20s|%-20s|%20s|%n",
                    c.getIsbn(),
                    c.getTitle(),
                    c.getAuthor().getFirstName() + " " + c.getAuthor().getLastName(),
                    c.getPublishedYear(),
                    c.getEdition(),
                    c.getGenre(),
                    c.getAvailable() + "/" + c.getNumOfCopies()
            );
            System.out.printf(outputRecord);
        }

        System.out.printf(outputHRule);

    }

    private void displayCatalogueSortedBy​PublishedYear() {

        Collection<Catalogue> catalogues = colOfCatalogue.values();
        ArrayList<Catalogue> catalogueList = new ArrayList<>(catalogues);

        Collections.sort(catalogueList, ((o1, o2) -> o2.getPublishedYear() - o1.getPublishedYear()));

        String outputTitle = String.format("|%-22s|%-20s|%-20s|%-20s|%-20s|%-20s|%-20s|%n", "ISBN", "Title", "Author", "Published Year", "Edition", "Genre", "Available");
        String outputHRule = String.format("+%s+%s+%s+%s+%s+%s+%s+%n", "----------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------");
        System.out.println();
        System.out.printf(outputHRule);
        System.out.printf(outputTitle);
        System.out.printf(outputHRule);

        for (Catalogue c : catalogueList) {
            String outputRecord = String.format(
                    "|%-22s|%-20s|%-20s|%20s|%20s|%-20s|%20s|%n",
                    c.getIsbn(),
                    c.getTitle(),
                    c.getAuthor().getFirstName() + " " + c.getAuthor().getLastName(),
                    c.getPublishedYear(),
                    c.getEdition(),
                    c.getGenre(),
                    c.getAvailable() + "/" + c.getNumOfCopies()
            );
            System.out.printf(outputRecord);
        }

        System.out.printf(outputHRule);

    }

    private void makeColOfCatalogue() {

        HashMap<String, Book> tempCatalogue = makeTempCatalogue();

        HashMap<String, Integer> copies = getNumOfCopies(tempCatalogue);
        HashMap<String, Integer> available = getNumOfAvailable(tempCatalogue);

        Iterator it = tempCatalogue.keySet().iterator();
        while (it.hasNext()) {
            String isbn = (String) it.next();
            Book b = tempCatalogue.get(isbn);

            Catalogue catalogue = new Catalogue(
                    b.getTitle(),
                    b.getAuthor(),
                    b.getPublishedYear(),
                    b.getEdition(),
                    b.getIsbn(),
                    b.getGenre(),
                    copies.get(isbn),
                    available.get(isbn)
            );

            colOfCatalogue.put(isbn, catalogue);
        }

    }

    private HashMap<String, Book> makeTempCatalogue() {

        Collection<Book> books = colOfBooks.values();
        ArrayList<Book> booksList = new ArrayList<>(books);
        HashMap<String, Book> tempCatalogue = new HashMap<>();
        for (int i = 0; i < booksList.size(); i++) {
            tempCatalogue.put(booksList.get(i).getIsbn(), booksList.get(i));
        }

        return tempCatalogue;
    }

    private HashMap<String, Integer> getNumOfCopies(HashMap<String, Book> tempCatalogue) {

        Collection<Book> books = colOfBooks.values();
        ArrayList<Book> booksList = new ArrayList<>(books);

        Iterator it = tempCatalogue.keySet().iterator();
        HashMap<String, Integer> copies = new HashMap<>();
        while (it.hasNext()) {
            String isbn = (String) it.next();
            for (int i = 0; i < booksList.size(); i++) {
                if (booksList.get(i).getIsbn().equals(isbn)) {

                    // copies
                    if (copies.containsKey(booksList.get(i).getIsbn())) {
                        copies.put(booksList.get(i).getIsbn(), copies.get(booksList.get(i).getIsbn()) + 1);
                    } else {
                        copies.put(booksList.get(i).getIsbn(), 1);
                    }
                }
            }
        }
        return copies;
    }

    private HashMap<String, Integer> getNumOfAvailable(HashMap<String, Book> tempCatalogue) {
        Collection<Book> books = colOfBooks.values();
        ArrayList<Book> booksList = new ArrayList<>(books);

        Iterator it = tempCatalogue.keySet().iterator();
        HashMap<String, Integer> available = new HashMap<>();
        while (it.hasNext()) {
            String isbn = (String) it.next();
            for (int i = 0; i < booksList.size(); i++) {
                if (booksList.get(i).getIsbn().equals(isbn)) {

                    // available
                    if (booksList.get(i).getAvailable()) {
                        if (available.containsKey(booksList.get(i).getIsbn())) {
                            available.put(booksList.get(i).getIsbn(), available.get(booksList.get(i).getIsbn()) + 1);
                        } else {
                            available.put(booksList.get(i).getIsbn(), 1);
                        }
                    }


                }
            }
        }
        return available;
    }

    public void borrowBooks(String customerId, int[] bookIds) {

        Customer c = colOfCustomers.get(customerId);

        //1. check customer active
        if (!c.isActive()) {
            return;
        }
        //2. check book available
        for (int bookId : bookIds) {
            if (colOfCatalogue.get(colOfBooks.get(bookId).getIsbn()).getAvailable() > 0) continue;
            return;
        }

        //3. add borrow
        LocalDate borrowedDate = LocalDate.now();
        LocalDate returnDate = borrowedDate.plusDays(LENDINGPERIODDAYS);
        Borrowing borrow = new Borrowing(c, borrowedDate, returnDate);
        for (int bookId : bookIds) {
            if (borrow.getBooks().containsKey(bookId)) {
                borrow.getBooks().put(bookId, borrow.getBooks().get(bookId) + 1);
            } else {
                borrow.getBooks().put(bookId, 1);
            }
        }

        colOfBorrowings.add(borrow);

        //4. update the value of copies available from the book
        for (int bookId : bookIds) {
            colOfBooks.get(bookId).setAvailable(false);
        }

        //5. refresh catalogue table
        makeColOfCatalogue();

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

    public void returnAllBooks(String customerId, int[] bookIds) {

        //1. get the customer's borrowing list
        ArrayList<Borrowing> borrowingList;
        borrowingList = getBorrowingList(customerId);

        //2.
        for (int bookId : bookIds) {
            returnBook(bookId, borrowingList);
        }

        //3.
        for (int bookId : bookIds) {
            colOfBooks.get(bookId).setAvailable(true);
        }

        //4.
        for (Borrowing borrow : borrowingList) {
            if (getSumOfBorrowingBook(borrow) != 0) continue;
            borrow.setFinished(true);
        }

        //5. refresh catalogue table
        makeColOfCatalogue();


    }

    private int getSumOfBorrowingBook(Borrowing borrow) {

        Iterator<Integer> it = borrow.getBooks().keySet().iterator();
        int sum = 0;
        while (it.hasNext()) {
            sum += borrow.getBooks().get(it.next());
        }
        return sum;

    }

    private ArrayList<Borrowing> getBorrowingList(String customerID) {

        ArrayList<Borrowing> borrowingList = new ArrayList<>();
        for (Borrowing borrow : colOfBorrowings) {
            if (!borrow.getCustomer().getId().equals(customerID)) continue;
            if (!borrow.isFinished()) borrowingList.add(borrow);
        }

        return borrowingList;

    }

    private void returnBook(int bookId, ArrayList<Borrowing> borrowingList) {

        for (Borrowing borrow : borrowingList) {
            if (borrow.getBooks().containsKey(bookId)) borrow.getBooks().put(bookId, borrow.getBooks().get(bookId) - 1);
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, Book> getColOfBooks() {
        return colOfBooks;
    }

    public void setColOfBooks(HashMap<Integer, Book> colOfBooks) {
        this.colOfBooks = colOfBooks;
    }

    public HashMap<String, Customer> getColOfCustomers() {
        return colOfCustomers;
    }

    public void setColOfCustomers(HashMap<String, Customer> colOfCustomers) {
        this.colOfCustomers = colOfCustomers;
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
