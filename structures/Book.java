package task2.structures;


import java.util.Objects;

public class Book {

    private String title;
    private String author;
    private String ISBN;
    private String publicationYear;
    private boolean isAvailable;

    public Book(String title, String author, String ISBN, String publicationYear) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title: " + title + '\'' +
                ", author: " + author + '\'' +
                ", ISBN: " + ISBN +
                ", publicationYear: " + publicationYear + '\'' +
                ", isAvailable: " + isAvailable +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(ISBN, book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, ISBN);
    }

}