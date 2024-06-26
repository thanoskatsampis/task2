package task2;

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

    @Override
    public String toString() {
        return "Book{" +
                "title= " + title + '\'' +
                ", author= " + author + '\'' +
                ", ISBN= " + ISBN +
                ", publicationYear= " + publicationYear + '\'' +
                ", isAvailable= " + isAvailable +
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
        if(!isAvailable){
            System.out.println("Look for another book");
            return isAvailable;
        }
        return true;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
