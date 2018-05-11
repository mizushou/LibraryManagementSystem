package ca.ciccc.model;

import ca.ciccc.exception.InValidPublishedYearException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book implements Comparable<Book> {

    private int id;
    private String title;
    private Author author;
    private int publishedYear;
    private int edition;
    private String isbn;
    private Genre genre;
    private boolean available;

    static private int count = 10000;

    public Book() {
    }

    public Book(String title, Author author, int publishedYear, int edition, Genre genre) throws InValidPublishedYearException {

        setId(++count);
        setTitle(title);
        setAuthor(author);
        setPublishedYear(publishedYear);
        setEdition(edition);
        setGenre(genre);

    }

    public Book(String title, Author author, int publishedYear, int edition, Genre genre, int numOfCopies) {

        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.edition = edition;
        this.genre = genre;

    }

    public Book(String title, Author author, int publishedYear, int edition, String isbn, Genre genre, int numOfCopies, boolean available, int id) {

        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.edition = edition;
        this.isbn = isbn;
        this.genre = genre;
        this.available = available;
        this.id = id;

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

    public int getEdition() {

        return edition;

    }

    public void setEdition(int edition) {

        this.edition = edition;

    }

    public int getPublishedYear() {

        return publishedYear;

    }

    public void setPublishedYear(int publishedYear) throws InValidPublishedYearException {

        if (!isValidPublisheYear(publishedYear)) {
            throw new InValidPublishedYearException("Input is invalid format");
        }
        this.publishedYear = publishedYear;

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

    public boolean getAvailable() {

        return available;

    }

    public void setAvailable(boolean available) {

        this.available = available;

    }

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    private boolean isValidPublisheYear(int publishedYear) {
        String regex = "[0-9]{3,4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(String.valueOf(publishedYear));
        return matcher.matches();

    }

    @Override
    public String toString() {

        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", edition=" + edition +
                ", isbn='" + isbn + '\'' +
                ", genre=" + genre +
                ", available=" + available +
                '}';

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (getEdition() != book.getEdition()) return false;
        return getAuthor().equals(book.getAuthor());

    }

    @Override
    public int hashCode() {

        int result = getAuthor().hashCode();
        result = 31 * result + getEdition();
        return result;

    }

    @Override
    public int compareTo(Book o) {

        return this.edition - o.edition;

    }
}
