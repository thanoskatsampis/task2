package task2.task;

import java.util.Scanner;

public class Book {

    private String title;
    private String author;
    private String ISBN;
    private String publicationYear;
    private boolean isAvailable = true;

    private Scanner scanner = new Scanner(System.in);

    public Book() {

        System.out.println("Enter book title: ");
        this.title = scanner.nextLine();

        System.out.println("Enter author's name: ");
        this.author = scanner.nextLine();

        System.out.println("Enter ISBN code ");
        this.ISBN = scanner.nextLine();

        System.out.println("Enter book's publication year: ");
        this.publicationYear = scanner.next();
    }

    public Book(String title, String author, String ISBN, String publicationYear, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.isAvailable = isAvailable;
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
}
