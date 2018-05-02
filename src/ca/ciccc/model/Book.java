package ca.ciccc.model;

public class Book implements Comparable<Book>{

    private String title;
    private Author author;
    private int edition;
    private String isbn;
    private Genre genre;
    private int numOfCopies;
    private int available;

    public Book() {
    }

    public Book(String title, Author author, int edition, String isbn, Genre genre, int numOfCopies, int available) {
        this.title = title;
        this.author = author;
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

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", edition=" + edition +
                ", isbn='" + isbn + '\'' +
                ", genre=" + genre +
                ", numOfCopies=" + numOfCopies +
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
