package task2;

import java.util.Scanner;

public class Book {

    public String title;
    public String author;
    public int ISBN;
    public String publicationYear;
    public boolean isAvailable = true;

    Scanner scanner = new Scanner(System.in);

    public Book() {

        System.out.println("Give book title: ");
        this.title = scanner.nextLine();

        System.out.println("Give author's name: ");
        this.author = scanner.nextLine();

        System.out.println("Give ISBN code: ");
        this.ISBN = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Give book's publication year: ");
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

    public boolean getIsAvailable() {
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
