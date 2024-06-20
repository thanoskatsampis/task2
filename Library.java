package task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Library {

    static Scanner scanner = new Scanner(System.in);

    public String libraryName;
    public String libraryAddress;

    public Library() {
        System.out.println("Give Library's name: ");
        this.libraryName = scanner.nextLine();

        System.out.println("Give Library's address: ");
        this.libraryAddress = scanner.nextLine();
    }
    Book book;
    List<Book> listOfBooks = new ArrayList<>();
    static List<Library> listOfLibraries = new ArrayList<>();
    HashMap<String , String> listOfAllBorrowedBooksWithTheirMembers = new HashMap<>();

    public int compareLibraries(Library library) {
        for (Library l : listOfLibraries) {
            if (l.libraryAddress.equalsIgnoreCase(library.libraryAddress)) {
                return 0;
            }
        }
        return 1;
    }

    public void addLibraries(Library library) {
        if (compareLibraries(library) == 0) {
            System.out.println("This Library already exists.");
            scanner.nextLine();
            return;
        }
        listOfLibraries.add(library);
        System.out.println("Library is successfully added!");
    }


    public int compareBooks(Book book) {
        for (Book b : listOfBooks) {
            if (b.title.equalsIgnoreCase(book.title)) {
                return 0;
            } else if (b.ISBN == book.ISBN) {
                return 0;
            }
        }
        return 1;
    }

    public void addBooks() {
         book = new Book();
        if (compareBooks(book) == 0) {
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
        for (Book b : listOfBooks) {
            if (b.title.equals(title)) {
                return b;
            }
        }
        return null;
    }

    public Book searchBookByISBN() {
        System.out.println("Enter book's ISBN: ");
        int num = scanner.nextInt();
        for (Book b : listOfBooks) {
            if (b.ISBN == num) {
                return b;
            }
        }
        return null;
    }

    public List<Book> searchBooksByAuthor() {
        System.out.println("Enter book's author: ");
        String authorName = scanner.nextLine();
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book b : listOfBooks) {
            if (b.author.equals(authorName)) {
                booksByAuthor.add(b);
            }
        }
        return booksByAuthor;
    }

    public List<Book> searchBooksByPublicationYear() {
        System.out.println("Enter book's publication year: ");
        String yearOfPublication = scanner.nextLine();
        List<Book> booksByPublicationsYear = new ArrayList<>();
        for (Book b : listOfBooks) {
            if (b.publicationYear.equals(yearOfPublication)) {
                booksByPublicationsYear.add(b);
            }
        }
        return booksByPublicationsYear;
    }

    public void removeBookByTitle() {
        book = searchBookByTitle();
        listOfBooks.remove(book);
        System.out.println(book.title + " removed from the library.");
    }

    public void removeBookByISBN() {
        book = searchBookByISBN();
        listOfBooks.remove(book);
        System.out.println(book.title + " removed from the library.");
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
        for (Book b : listOfBooks) {
            if (b.isAvailable) {
                listOfAvailableBooks.add(b);
            }
        }
        return listOfAvailableBooks;
    }

    private void BorrowedBooksWithTheirMember(String title, String name) {
        listOfAllBorrowedBooksWithTheirMembers.put(title, name);
    }

    private void booksWhichReturn(String name , String title){
        listOfAllBorrowedBooksWithTheirMembers.remove(name, title);
    }

    public void borrowBooks(Member member){
        book = searchBookByTitle();
        if(book == null){
            System.out.println("You enter wrong data");
            return;
        }
        if(book.getIsAvailable()){
            book.setAvailable(false);
            BorrowedBooksWithTheirMember(book.title, member.name );
            System.out.println(book.title + " is borrowed by " + member.name);
            return;
        }
        System.out.println("This book is not available wright now");
    }

    public  void returnBooks(Member member) {
         book = searchBookByTitle();
        if(book == null){
            System.out.println("You enter wrong data");
            return;
        }
        if (!book.getIsAvailable()) {
            book.setAvailable(true);
            booksWhichReturn(book.title, member.name);
            System.out.println(member.name + " returned " + book.title);
            return;
        }
        System.out.println("This book wasn't borrowed.");
    }

    @Override
    public String toString() {
        return "Library{" +
                "libraryName: " + libraryName + '\'' +
                ", libraryAddress: " + libraryAddress + '\'' +
                '}';
    }

    public static void selectALibrary() {
        if (listOfLibraries.isEmpty()) {
            System.out.println("You should add a Library first.");
            return;
        }
        System.out.println(listOfLibraries + "\n Select the library you want to work on by its address.");
        String addressOfLibrary = scanner.nextLine();
        for (Library library : listOfLibraries) {
            if (library.libraryAddress.equals(addressOfLibrary)) {
                int num = 0;
                while (num != 10) {
                    menu2();
                    num = scanner.nextInt();
                    scanner.nextLine();
                    switch (num) {
                        case 1:
                            if (LibraryMembers.listOfAllMembers.isEmpty()) {
                                System.out.println("You must first add an administrator.");
                                break;
                            }
                            int id;
                            System.out.println("Enter administrator's id");
                            id = scanner.nextInt();
                            for (Member member : LibraryMembers.listOfAllMembers) {
                                if (member.isAdmin && member.Id == id) {
                                    library.addBooks();
                                    break;
                                }
                                System.out.println("Only the administrator can add a book. ");
                            }
                            break;
                        case 2:
                            if(library.listOfBooks.isEmpty()){
                                System.out.println("There is not any book added in the library.");
                                break;
                            }
                            int number;
                            System.out.println("""
                                     1 - Press 1 if you want to remove a book by its title.
                                     2 - Press 2 if you want to remove a book by its ISBN.
                                     3 - Press 3 if you want to remove an author's books.
                                     4 - Press 4 if you want to remove book from a whole publication year.
                                    ---- Press any other number to exit.
                                    """);
                            number = scanner.nextInt();
                            scanner.nextLine();
                            if (number == 1) {
                                library.removeBookByTitle();
                            } else if (number == 2) {
                                library.removeBookByISBN();
                            } else if (number == 3) {
                                library.removeBooksByAuthor();
                            } else if (number == 4) {
                                library.removeBooksByPublicationYear();
                            } else {
                                System.out.println("exit");
                            }
                            break;
                        case 3:
                            System.out.println("Add a new Member:");
                            LibraryMembers libraryMembers = new LibraryMembers();
                            libraryMembers.addMember();
                            break;
                        case 4:
                            membersMenu(library);
                            break;
                        case 5:
                            System.out.println(library.booksAvailability());
                            break;
                        case 6:
                            int n;
                            System.out.println("""
                                     1 - Press 1 if you want to find a book by its title.
                                     2 - Press 2 if you want to find a book by its ISBN.
                                     3 - Press 3 if you want to find an author's books.
                                     4 - Press 4 if you want to find book from a whole publication year.
                                    """);
                            n = scanner.nextInt();
                            scanner.nextLine();
                            if (n == 1) {
                                System.out.println(library.searchBookByTitle());
                            } else if (n == 2) {
                                System.out.println(library.searchBookByISBN());
                            } else if (n == 3) {
                                System.out.println(library.searchBooksByAuthor());
                            } else if (n == 4) {
                                System.out.println(library.searchBooksByPublicationYear());
                            }
                            break;
                        case 7:
                            library.listOfAllBorrowedBooksWithTheirMembers.forEach((key, value) -> System.out.println("Book: " + key + "," + "Member: " + value));
                            break;
                        case 10:
                            System.out.println("EXIT");
                            break;
                        default:
                            System.out.println("Choose among 1 to 7 or 10 for exit.");
                            break;
                    }
                }
                return;
            }
        }
        System.out.println("This library does not exist.");
    }

    public static void menu2() {
        System.out.println( "\n" + """ 
                1 - Press 1 to add a new book.
                2 - Press 2 to remove a book from the library.
                3 - Press 3 to add a new member.
                4 - Press 4 to work with members.
                5 - Press 5 to see available books.
                6 - Press 6 for searching a book by specific keys.
                7 - Press 7 to see all borrowed books with their members.
                10 - Press 10 to exit this Library.
                """);
    }


    public static void membersMenu(Library library) {
        int n = 0;
        while (n != 10) {
            System.out.println("""
                    1 - Press 1 for borrowing/returning a book.
                    2 - Press 2 to see a list of all members.
                    3 - Press 3 to search a member by specific keys.
                    10 - Press 10 to exit members' menu.
                    """);
            n = scanner.nextInt();
            switch (n) {
                case 1:
                    System.out.println(LibraryMembers.listOfAllMembers + "\n Select the member you want to work on by their id.");
                    int memberId = scanner.nextInt();
                    for (Member members : LibraryMembers.listOfAllMembers) {
                        if (members.Id == memberId) {
                            System.out.println("""
                                    1 - Press 1 for borrow a book.
                                    2 - Press 2 for returning a book.
                                    """);
                            int x = scanner.nextInt();
                            scanner.nextLine();
                            if(x == 1){
                              library.borrowBooks(members);
                            } else if (x == 2) {
                                library.returnBooks(members);
                            }
                        }
                    }
                        break;
                case 2:
                    LibraryMembers.listOfAllMembers();
                    break;
                case 3:
                    int num;
                    System.out.println("""
                                   1 - Press 1 if you want to find a member by their name.
                                   2 - Press 2 if you want to find a member by their id.
                                   """);
                    num = scanner.nextInt();
                    if (num == 1) {
                        System.out.println(LibraryMembers.searchMemberByName());
                    } else if (num == 2) {
                        System.out.println(LibraryMembers.searchMemberById());
                        break;
                    }
                case 10:
                    System.out.println("EXIT");
                     break;
                default:
                    System.out.println("Choose among 1 to 3 or 10 for exit.");
                    break;
                }
            }
        }
    }





