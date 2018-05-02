package ca.ciccc.controller;

import ca.ciccc.model.Book;
import ca.ciccc.model.Borrowing;
import ca.ciccc.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;

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
