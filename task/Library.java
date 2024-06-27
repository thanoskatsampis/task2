package task2.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Library {

    private Scanner scanner = new Scanner(System.in);

    private String libraryName;
    private String libraryAddress;
    private List<Member> listOfMembers = new ArrayList<>();
    private Book book;
   private List<Book> listOfBooks = new ArrayList<>();
    HashMap<String, Integer> listOfAllBorrowedBooksWithTheirMembers = new HashMap<>();

    public Library() {
        System.out.println("Enter Library's name: ");
        this.libraryName = scanner.nextLine();

        System.out.println("Enter Library's address: ");
        this.libraryAddress = scanner.nextLine();
    }

    public Library(String name, String address){
        this.libraryName = name;
        this.libraryAddress = address;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public String getLibraryAddress() {
        return libraryAddress;
    }

    public List<Member> getListOfMembers() {
        return listOfMembers;
    }

    public List<Book> getListOfBooks() {
        return listOfBooks;
    }

    private int compareMember(Member member) {
        for(Member m : listOfMembers) {
            if (member.getId() == m.getId()) {
                return 0;
            }
        }
        return 1;
    }

    public void addMember() {
        Member member = new Member();
        if(compareMember(member) == 0){
            System.out.println("This id already exists.");
            return;
        }
        if(member.isAdmin()) {
            if(searchAdmin()) {
                member.setAdmin(false);
                System.out.println("Only one admin is allowed");
            }
        }
        listOfMembers.add(member);
    }

    public boolean searchAdmin() {
        for(Member member : listOfMembers)
            if(member.isAdmin()){
                return true;
            }
        return false;
    }

    private int compareBooks(Book book) {
        for(Book b : listOfBooks) {
            if(b.getTitle().equalsIgnoreCase(book.getTitle())) {
                return 0;
            }else if(b.getISBN().equals(book.getISBN())) {
                return 0;
            }
        }
        return 1;
    }

    public void addBook() {
        Book book= new Book();
        if(compareBooks(book) == 0){
            System.out.println("This book already exists.");
            scanner.nextLine();
            return;
        }
        listOfBooks.add(book);
        System.out.println("This book is successfully added.");
    }

    public Book searchBookByTitle() {
        System.out.println("Enter book's title: ");
        String title = scanner.nextLine();
        for(Book b : listOfBooks){
            if(b.getTitle().equals(title)){
                return b;
            }
        }
        System.out.println("Invalid data!");
        return null;
    }

    public Book searchBookByISBN() {
        System.out.println("Enter book's ISBN: ");
        String num = scanner.nextLine();
        for(Book b : listOfBooks){
            if(b.getISBN().equals(num)){
                return b;
            }
        }
        System.out.println("Invalid data!");
        return null;
    }

    public List<Book> searchBooksByAuthor() {
        System.out.println("Enter book's author: ");
        String authorName = scanner.nextLine();
        List<Book> booksByAuthor = new ArrayList<>();
        for(Book b : listOfBooks){
            if(b.getAuthor().equals(authorName)){
                booksByAuthor.add(b);
            }
        }
        return booksByAuthor;
    }

    public List<Book> searchBooksByPublicationYear() {
        System.out.println("Enter book's publication year: ");
        String yearOfPublication = scanner.nextLine();
        List<Book> booksByPublicationsYear = new ArrayList<>();
        for(Book b : listOfBooks){
            if(b.getPublicationYear().equals(yearOfPublication)){
                booksByPublicationsYear.add(b);
            }
        }
        return booksByPublicationsYear;
    }

    public void removeBookByTitle() {
        Book book = searchBookByTitle();
        if(book != null){
            listOfBooks.remove(book);
            System.out.println(book.getTitle() + " removed from the library.");
        }
    }

    public void removeBookByISBN() {
        book = searchBookByISBN();
        if(book != null){
            listOfBooks.remove(book);
            System.out.println(book.getTitle() + " removed from the library.");
        }else
            System.out.println("There is no book with this ISBN in the library.");
    }

    public void removeBooksByAuthor() {
        listOfBooks.removeAll(searchBooksByAuthor());
        System.out.println(" You successfully removed all author's books from the library.");
    }

    public void removeBooksByPublicationYear() {
        listOfBooks.removeAll(searchBooksByPublicationYear());
        System.out.println(" You successfully removed all books which published this year from the library.");
    }

    public List<Book> booksAvailability() {
        List<Book> listOfAvailableBooks = new ArrayList<>();
        for(Book b : listOfBooks){
            if(b.isAvailable()){
                listOfAvailableBooks.add(b);
            }
        }
        return listOfAvailableBooks;
    }

    public void borrowBooks(Member member) {
        book = searchBookByTitle();
        if(book == null){
            System.out.println("You enter wrong data");
            return;
        }
        if(book.isAvailable()){
            book.setAvailable(false);
            listOfAllBorrowedBooksWithTheirMembers.put(book.getTitle(), member.getId());
            System.out.println(book.getTitle() + " is borrowed by " + member.getId());
        } else
            System.out.println("This book is not available wright now");
    }

    public void returnBooks(Member member){
        Book book = searchBookByTitle();
        if(book == null){
            System.out.println("You enter wrong data");
            return;
        }
        if(!book.isAvailable()){
            book.setAvailable(true);
            listOfAllBorrowedBooksWithTheirMembers.remove(book.getTitle(), member.getId());
            System.out.println(member.getName() + " returned " + book.getTitle());

        }else
            System.out.println("This book wasn't borrowed.");
    }


    @Override
    public String toString() {
        return "Library{" +
                "libraryName: " + libraryName + '\'' +
                ", libraryAddress: " + libraryAddress + '\'' +
                '}';

    }

    public void printMembers() {
        System.out.println("LibraryMembers{" + listOfMembers + '}');
    }


    public Member searchMemberById() {
        System.out.println("Enter member's id: ");
        int num = scanner.nextInt();
        for(Member member : listOfMembers){
            if(member.getId() == num){
                return member;
            }
        }
        return null;
    }

    public Member searchMemberByName() {
        System.out.println("Enter member's name: ");
        String name = scanner.nextLine();
        for(Member member : listOfMembers){
            if(member.getName().equals(name)){
                return member;
            }
        }
        return null;
    }
}





