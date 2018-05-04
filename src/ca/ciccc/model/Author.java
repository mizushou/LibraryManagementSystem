package ca.ciccc.model;

import java.time.LocalDate;

public class Author extends Person {

    private String pseudonym;
    private Genre specialty;

    public Author() {
    }

    public Author(String firstName, String lastName, String pseudonym, Genre specialty) {
        super(firstName, lastName, null);
        this.pseudonym = pseudonym;
        this.specialty = specialty;
    }

    public Author(String firstName, String lastName, LocalDate dateOfBirth, String pseudonym, Genre specialty) {
        super(firstName, lastName, dateOfBirth);
        this.pseudonym = pseudonym;
        this.specialty = specialty;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public Genre getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Genre specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Author{" +
                "pseudonym='" + pseudonym + '\'' +
                ", specialty=" + specialty +
                '}';
    }
}
