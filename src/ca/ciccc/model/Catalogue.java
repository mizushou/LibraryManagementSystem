package ca.ciccc.model;

public class Catalogue {

    private String title;
    private Author author;
    private int publishedYear;
    private int edition;
    private String isbn;
    private Genre genre;
    private int numOfCopies;
    private int available;

    public Catalogue(String title, Author author, int publishedYear, int edition, String isbn, Genre genre, int numOfCopies, int available) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.edition = edition;
        this.isbn = isbn;
        this.genre = genre;
        this.numOfCopies = numOfCopies;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
