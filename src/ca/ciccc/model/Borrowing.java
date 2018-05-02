package ca.ciccc.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Borrowing {

    private ArrayList<Book> books;
    private Customer customer;
    private boolean finished;
    private LocalDate borrowedDate;
    private LocalDate returnDate;

    public Borrowing() {
    }

    public Borrowing(Customer customer, boolean finished, LocalDate borrowedDate, LocalDate returnDate) {
        this.books = new ArrayList<>();
        this.customer = customer;
        this.finished = finished;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Borrowing{" +
                "books=" + books +
                ", customer=" + customer +
                ", finished=" + finished +
                ", borrowedDate=" + borrowedDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
