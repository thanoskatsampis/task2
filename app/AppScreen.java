package task2.app;

import task2.structures.Admin;
import task2.structures.Book;
import task2.structures.Library;
import task2.structures.Member;
import task2.system.LibraryMS;

import java.util.Scanner;

public class AppScreen {

    private Scanner scanner = new Scanner(System.in);
    private LibraryMS libraryMS = new LibraryMS();

    public LibraryMS getLibraryMS() {
        return libraryMS;
    }

    public void menu(){
        System.out.println("""
                Hello! Welcome to Library Management System!
                1 - Press 1 to select a library.
                2 - Press 2 to add a new library.
                3 - Press 3 to exit the app.
                """);
    }

    public void addLibrary(){
        System.out.println("Enter library's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter library's address: ");
        String address = scanner.nextLine();
        Library library = new Library(name, address);
        for(Library lib : libraryMS.getLibraries())
            if(lib.equals(library)) {
                System.out.println("This library already exists.");
                return;
            }
        libraryMS.getLibraries().add(library);
        System.out.println("Library is successfully added!");
    }

    public void selectLibrary(){
        if (libraryMS.getLibraries().isEmpty()){
            System.out.println(libraryMS.getLibraries());
            System.out.println("You should add a library first.");
            return;
        }
        System.out.println(libraryMS.getLibraries() + "\n Select a library by its address.");
        String addressOfLibrary = scanner.nextLine();
        for(Library library : libraryMS.getLibraries()){
            if(library.getLibraryAddress().equals(addressOfLibrary)){
                int num = 0;
                while (num != 10){
                    menu2();
                    try {
                        num = scanner.nextInt();
                        scanner.nextLine();
                        switch (num) {
                            case 1:
                                addBook(library, libraryMS);
                                break;
                            case 2:
                                removeBook(library, libraryMS);
                                break;
                            case 3:
                                availableBooks(library, libraryMS);
                                break;
                            case 4:
                                searchBook(library, libraryMS);
                                break;
                            case 5:
                                addMember(library, libraryMS);
                                break;
                            case 6:
                                addAdmin(library, libraryMS);
                                break;
                            case 7:
                                membersMenu(library, libraryMS);
                                break;
                            case 8:
                                borrowedBooks(library);
                                break;
                            case 10:
                                libraryMS.saveLibraryDataToFile();
                                System.out.println("Exit the library menu.");
                                break;
                            default:
                                System.out.println("Choose among 1 to 8 or 10 for exit.");
                                break;
                        }
                    }catch (Exception e){
                        System.out.println("You should enter a number.");
                        scanner.nextLine();
                    }
                }
                return;
            }
        }
        System.out.println("This library does not exist.");
    }

    public void menu2(){
        System.out.println("\n" + """ 
                1 - Press 1 to add a new book.
                2 - Press 2 to remove a book from the library.
                3 - Press 3 to see available books.
                4 - Press 4 for searching a book by specific keys.
                5 - Press 5 to add a new member.
                6 - Press 6 to add a new administrator.
                7 - Press 7 to borrow/return/list of all members/search a member.
                8 - Press 8 to see all borrowed books with their members.
                9 - Press 9 to save library's data to a file.
                10 - Press 10 to exit this library.
                """);
    }

    public void addBook(Library library, LibraryMS libraryMS) {
        if (library.getAdmins().isEmpty()) {
            System.out.println("You must add an administrator at first.");
            return;
        }
        System.out.println("Enter administrator's id");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Admin admin : library.getAdmins()) {
            if (admin.getId() != id) {
                System.out.println("Invalid Data! Library's administrators are:" + library.getAdmins());
                return;
            }
        }
        System.out.println("Enter book's title: ");
        String title = scanner.nextLine();
        System.out.println("Enter book's author: ");
        String author = scanner.nextLine();
        System.out.println("Enter book's ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.println("Enter book's publicationYear: ");
        String publicationYear = scanner.nextLine();

        Book book = new Book(title, author, ISBN, publicationYear);
        libraryMS.addBook(library, book);
    }


    public void removeBook(Library library, LibraryMS libraryMS){
        if(library.getBooks().isEmpty()){
            System.out.println("There isn't any book added in the library.");
            return;
        }
        int number = 0;
        System.out.println("""
             1 - Press 1 if you want to remove a book
             2 - Press 2 if you want to remove a book by its ISBN.
             3 - Press 3 if you want to remove an author's books.
             4 - Press 4 if you want to remove book from a whole publication year.
             ---- Press any other number to exit.
             """);
        try{
            number = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Enter a number.");
        }
        scanner.nextLine();
        if(number == 1){
            System.out.println("Enter book's title: ");
            String title = scanner.nextLine();
            libraryMS.removeBookByTitle(library, title);
        }else if(number == 2){
            System.out.println("Enter book's ISBN: ");
            String ISBN = scanner.nextLine();
            libraryMS.removeBookByISBN(library, ISBN);
        }else if(number == 3){
            System.out.println("Enter book's author: ");
            String author = scanner.nextLine();
            libraryMS.removeBooksByAuthor(library, author);
        }else if(number == 4){
            System.out.println("Enter book's title: ");
            String publicationYear = scanner.nextLine();
            libraryMS.removeBooksByPublicationYear(library, publicationYear);
        }else{
            System.out.println("exit");
        }
    }

    public void searchBook(Library library, LibraryMS libraryMS){
        if(library.getBooks().isEmpty()){
            System.out.println("You should add a book at first.");
            return;
        }
        int n;
        System.out.println("""
         1 - Press 1 if you want to find a book by its title.
         2 - Press 2 if you want to find a book by its ISBN.
         3 - Press 3 if you want to find an author's books.
         4 - Press 4 if you want to find book from a whole publication year.
         """);
        try{
            n = scanner.nextInt();
        }catch (Exception e){
            System.out.println("Enter a number among.");
            n = 0;
        }
        scanner.nextLine();
        if(n == 1){
            System.out.println("Enter book's title: ");
            String title = scanner.nextLine();
            System.out.println(libraryMS.searchBookByTitle(library, title));
        }else if(n == 2){
            System.out.println("Enter book's ISBN: ");
            String ISBN = scanner.nextLine();
            System.out.println(libraryMS.searchBookByISBN(library, ISBN));
        }else if(n == 3) {
            System.out.println("Enter book's author: ");
            String author = scanner.nextLine();
            System.out.println(libraryMS.searchBooksByAuthor(library, author));
        }else if(n == 4) {
            System.out.println("Enter book's title: ");
            String publicationYear = scanner.nextLine();
            System.out.println(libraryMS.searchBooksByPublicationYear(library, publicationYear));
        }
    }

    public void availableBooks(Library library, LibraryMS libraryMS){
        if(library.getBooks().isEmpty()){
            System.out.println("You should add a book at first.");
            return;
        }
        System.out.println(libraryMS.booksAvailability(library));
    }

    public void borrowedBooks(Library library){
        if(library.getBooks().isEmpty()){
            System.out.println("The library is empty.");
            return;
        }
        library.getListOfAllBorrowedBooksWithTheirMembers().forEach((key, value) -> System.out.println("Book: " + key + ", " + "Member: " + value));
    }

    public void addMember(Library library, LibraryMS libraryMS){
        System.out.println("Add a new Member!");
        System.out.println("Enter member's name: ");
        String name = scanner.nextLine();

        int id;
        while(true){
            try {
                System.out.println("Enter member's id:(number) ");
                id = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Enter a number.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        System.out.println("Enter member's contact details ");
        String contactDetails = scanner.nextLine();

        Member member = new Member(name, id, contactDetails);
        libraryMS.addMember(library, member);
    }

    public void addAdmin(Library library, LibraryMS libraryMS){
        System.out.println("Add a new Admin!");
        System.out.println("Enter admins's name: ");
        String name = scanner.nextLine();

        int id;
        while(true){
            try {
                System.out.println("Enter admin's id:(number) ");
                id = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Enter a number.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();

        System.out.println("Enter admin's contact details ");
        String contactDetails = scanner.nextLine();

        Admin admin = new Admin(name, id, contactDetails);
        libraryMS.addAdmin(library, admin);
    }


    public void membersMenu(Library library, LibraryMS libraryMS){
        int n = 0;
        while(n != 10){
            System.out.println("""
                    1 - Press 1 for borrowing a book.
                    2 - Press 2 for returning a book.
                    3 - Press 3 to see a list of all members.
                    4 - Press 4 to search a member by their id.
                    5 - Press 5 to search a member by their names.
                    6 - Press 6 to exit members' menu.
                    """);
            try {
                n = scanner.nextInt();
                scanner.nextLine();
                switch (n) {
                    case 1:
                        borrowBook(library, libraryMS);
                        break;
                    case 2:
                        returnBook(library, libraryMS);
                        break;
                    case 3:
                        listOfMembers(library, libraryMS);
                        break;
                    case 4:
                        searchMemberById(library, libraryMS);
                    case 5:
                        searchMemberByName(library, libraryMS);
                    case 6:
                        break;
                    default:
                        System.out.println("Choose among 1 to 5 or 6 for exit.");
                        break;
                }

            } catch (Exception e) {
                System.out.println("You should enter a number.");
                scanner.nextLine();
            }
        }
    }

    public void borrowBook(Library library, LibraryMS libraryMS){
        if (library.getMembers().isEmpty()) {
            System.out.println("You should add a member.");
            return;
        }
        System.out.println(library.getMembers() + "\n Enter member's id.");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        for (Member member : library.getMembers()) {
            if (member.getId() == memberId) {
                System.out.println("Enter book's title: ");
                String title = scanner.nextLine();
                libraryMS.borrowBook(library, member, title);
            } else {
                System.out.println("Invalid data!");
                return;
            }
        }
    }

    public void returnBook(Library library, LibraryMS libraryMS ) {
        if (library.getMembers().isEmpty()) {
            System.out.println("You should add a member.");
            return;
        }
        System.out.println(library.getMembers() + "\n Enter member's id.");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        for (Member member : library.getMembers()) {
            if (member.getId() == memberId) {
                System.out.println("Enter book's title: ");
                String title = scanner.nextLine();
                libraryMS.returnBook(library, member, title);
            } else {
                System.out.println("Invalid data!");
                return;
            }
        }
    }

    public void listOfMembers(Library library, LibraryMS libraryMS ){
        if (library.getMembers().isEmpty()) {
            System.out.println("You haven't add any member.");
            return;
        }
        libraryMS.printMembers(library);
    }

    public void searchMemberById(Library library, LibraryMS libraryMS) {
        if (library.getMembers().isEmpty()) {
            System.out.println(" You haven't add any member.");
            return;
        }
        System.out.println("Enter member's id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(libraryMS.searchMemberById(library, id));
    }

    public void searchMemberByName(Library library, LibraryMS libraryMS) {
        if (library.getMembers().isEmpty()) {
            System.out.println(" You haven't add any member.");
            return;
        }
        System.out.println("Enter member's id: ");
        String name = scanner.nextLine();
        System.out.println(libraryMS.searchMemberByName(library, name));
    }

}

