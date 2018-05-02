package ca.ciccc.model;

import java.time.LocalDate;

public class Customer extends Person {

    private String id;
    private boolean active;

    public Customer() {
    }

    public Customer(String firstName, String lastName, LocalDate dateOfBirth, String id, boolean active) {
        super(firstName, lastName, dateOfBirth);
        this.id = id;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", active=" + active +
                '}';
    }
}
