package ca.ciccc.controller;

import ca.ciccc.model.Book;
import ca.ciccc.model.Borrowing;
import ca.ciccc.model.Customer;
import ca.ciccc.util.IdGenerator;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Library {

    private String name;
    private HashMap<String, Book> colOfBooks;
    private HashMap<String, Customer> colOfCutomers;
    private HashMap<String, Borrowing> colOfBorrowings;

    public Library() {
    }

    public Library(String name) {
        this.name = name;
        this.colOfBooks = new HashMap<>();
        this.colOfCutomers = new HashMap<>();
        this.colOfBorrowings = new HashMap<>();
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

        // add customer into customer collection
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
            String outputRecord = String.format("|%-5s|%-20s|%-20s|%-20s|%-20s|%n", c.getId(), c.getFirstName(), c.getLastName(), c.getDateOfBirth().toString(), c.isActive() ? "active" : "inactive");
            System.out.printf(outputRecord);
        }

        System.out.printf(outputHRule);

    }

    public void addBook(Book book) {
        String id = book.getAuthor().getFirstName() + book.getAuthor().getLastName() + book.getEdition();
        colOfBooks.put(id, book);
    }

    public void removeBook(){

    }

    public void displayBook() {

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

    public HashMap<String, Borrowing> getColOfBorrowings() {
        return colOfBorrowings;
    }

    public void setColOfBorrowings(HashMap<String, Borrowing> colOfBorrowings) {
        this.colOfBorrowings = colOfBorrowings;
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                '}';
    }
}
