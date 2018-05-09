package ca.ciccc.model;

import ca.ciccc.exception.IvalidArgumentException;

import java.time.LocalDate;

public abstract class Person {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public Person() {
    }

    public Person(String firstName, String lastName, LocalDate dateOfBirth) throws IvalidArgumentException {
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws IvalidArgumentException {
        if (firstName == null || firstName.equals("")) {
            throw new IvalidArgumentException("Input is null or empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws IvalidArgumentException {
        if (lastName == null || lastName.equals("")) {
            throw new IvalidArgumentException("Input is null or empty");
        }
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
