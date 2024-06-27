package task2.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;

public class LibraryMS {

    private List<Library> libraries = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private static final String FILE_LIBRARY = "Library_data.txt";

    private int compareLibraries(Library library){
        for(Library l : libraries){
            if(l.getLibraryAddress().equalsIgnoreCase(library.getLibraryAddress())){
                return 0;
            }
        }
        return 1;
    }

    public void addLibrary(){
        Library library = new Library();
        if (compareLibraries(library) == 0) {
            System.out.println("This Library already exists.");
            scanner.nextLine();
            return;
        }
        libraries.add(library);
        System.out.println("Library is successfully added!");
    }

    public void selectLibrary(){
        if (libraries.isEmpty()){
            System.out.println("You should add a Library first.");
            return;
        }
        System.out.println(libraries + "\n Select a library by its address.");
        String addressOfLibrary = scanner.nextLine();
        for(Library library : libraries){
            if(library.getLibraryAddress().equals(addressOfLibrary)){
                int num = 0;
                while (num != 10){
                    menu2();
                    try {
                        num = scanner.nextInt();
                        scanner.nextLine();
                        switch (num) {
                            case 1:
                                if(!library.searchAdmin()){
                                    System.out.println("You must add an administrator at first.");
                                    break;
                                }
                                int id;
                                System.out.println("Enter administrator's id");
                                id = scanner.nextInt();
                                for(Member member : library.getListOfMembers()){
                                    if(member.isAdmin() && member.getId() == id){
                                        library.addBook();
                                        break;
                                    }
                                    System.out.println("Only the administrator can add a book. ");
                                    break;
                                }
                                break;
                            case 2:
                                if(library.getListOfBooks().isEmpty()){
                                    System.out.println("There isn't any book added in the library.");
                                    break;
                                }
                                int number = 0;
                                System.out.println("""
                                         1 - Press 1 if you want to remove a book by its title.
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
                                    library.removeBookByTitle();
                                }else if(number == 2){
                                    library.removeBookByISBN();
                                }else if(number == 3){
                                    library.removeBooksByAuthor();
                                }else if(number == 4){
                                    library.removeBooksByPublicationYear();
                                }else{
                                    System.out.println("exit");
                                }
                                break;
                            case 3:
                                System.out.println("Add a new Member:");
                                library.addMember();
                                break;
                            case 4:
                                membersMenu(library);
                                break;
                            case 5:
                                if(library.getListOfBooks().isEmpty()){
                                    System.out.println("You should add a book at first.");
                                    break;
                                }
                                System.out.println(library.booksAvailability());
                                break;
                            case 6:
                                if(library.getListOfBooks().isEmpty()){
                                    System.out.println("You should add a book at first.");
                                    break;
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
                                    System.out.println(library.searchBookByTitle());
                                }else if(n == 2){
                                    System.out.println(library.searchBookByISBN());
                                }else if(n == 3) {
                                    System.out.println(library.searchBooksByAuthor());
                                }else if(n == 4) {
                                    System.out.println(library.searchBooksByPublicationYear());
                                }
                                break;
                            case 7:
                                if(library.getListOfBooks().isEmpty()){
                                    System.out.println("The Library is empty.");
                                    break;
                                }
                                library.listOfAllBorrowedBooksWithTheirMembers.forEach((key, value) -> System.out.println("Book: " + key + "," + "Member: " + value));
                                break;
                            case 8:
                              saveLibraryDataToFile();
                                break;
                            case 10:
                                System.out.println("Exit the Library menu.");
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
                3 - Press 3 to add a new member.
                4 - Press 4 to borrow/return/list of all members/search a member.
                5 - Press 5 to see available books.
                6 - Press 6 for searching a book by specific keys.
                7 - Press 7 to see all borrowed books with their members.
                8 - Press 8 to save Library's data to a file.
                10 - Press 10 to exit this Library.
                """);
    }

    public void membersMenu(Library library){
        int n = 0;
        while(n != 10){
            System.out.println("""
                    1 - Press 1 for borrowing/returning a book.
                    2 - Press 2 to see a list of all members.
                    3 - Press 3 to search a member by specific keys.
                    10 - Press 10 to exit members' menu.
                    """);
            try {
                n = scanner.nextInt();
                switch (n) {
                    case 1:
                        if (library.getListOfMembers().isEmpty()) {
                            System.out.println("You should add a member.");
                            return;
                        }
                        System.out.println(library.getListOfMembers() + "\n Enter member's id.");
                        int memberId = scanner.nextInt();
                        for (Member member : library.getListOfMembers()) {
                            if (member.getId() == memberId) {
                                System.out.println("""
                                        1 - Press 1 for borrow a book.
                                        2 - Press 2 for returning a book.
                                        """);
                                int x = scanner.nextInt();
                                scanner.nextLine();
                                if (x == 1) {
                                    library.borrowBooks(member);
                                } else if (x == 2) {
                                    library.returnBooks(member);
                                } else {
                                    System.out.println("You must enter 1 or 2.");
                                }
                            }
                        }
                        break;
                    case 2:
                        if (library.getListOfMembers().isEmpty()) {
                            System.out.println("You haven't add any member.");
                            return;
                        }
                        library.printMembers();
                        break;
                    case 3:
                        if (library.getListOfMembers().isEmpty()) {
                            System.out.println(" You haven't add any member.");
                            return;
                        }
                        int num;
                        System.out.println("""
                                1 - Press 1 if you want to find a member by their name.
                                2 - Press 2 if you want to find a member by their id.
                                """);
                        num = scanner.nextInt();
                        if (num == 1) {
                            System.out.println(library.searchMemberByName());
                            break;
                        } else if (num == 2) {
                            System.out.println(library.searchMemberById());
                            break;
                        }
                    case 10:
                        break;
                    default:
                        System.out.println("Choose among 1 to 3 or 10 for exit.");
                        break;
                }

            } catch (Exception e) {
                System.out.println("You should enter a number.");
                scanner.nextLine();
            }
        }
    }


    public void saveLibraryDataToFile() {
        try (FileWriter writer = new FileWriter(FILE_LIBRARY)) {
            for (Library library : libraries) {
                writer.write("Library:");
                writer.write( "~" + library.getLibraryName() + "~" + library.getLibraryAddress() + "\n");
                for (Book book : library.getListOfBooks()) {
                    writer.write("Book:");
                    writer.write("~" + book.getTitle() + "~" + book.getAuthor() + "~" + book.getISBN() + "~" + book.getPublicationYear() + "~" + book.isAvailable() + "\n");
                }
                for (Member member : library.getListOfMembers()) {
                    writer.write("Member:");
                    writer.write("~" +  member.getName() + "~" + member.getId() + "~" + member.getContactDetails() + "~" + member.isAdmin() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public void loadLibraryDataFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_LIBRARY))) {
            String line;
            Library currentLibrary = null;
            while ((line = reader.readLine()) != null) {
                String[] arrOfStr = line.split("~");
                if (line.startsWith("Library:")) {
                    currentLibrary = new Library(arrOfStr[1], arrOfStr[2]);
                    libraries.add(currentLibrary);
                } else if (line.startsWith("Book:")) {
                    if (currentLibrary != null) {
                        Book book = new Book(arrOfStr[1], arrOfStr[2], arrOfStr[3], arrOfStr[4], parseBoolean(arrOfStr[5]));
                        currentLibrary.getListOfBooks().add(book);
                    }
                } else if (line.startsWith("Member:")) {
                    if (currentLibrary != null) {
                        Member member = new Member(arrOfStr[1], Integer.parseInt(arrOfStr[2]), arrOfStr[3], parseBoolean(arrOfStr[4]));
                        currentLibrary.getListOfMembers().add(member);
                    }
                }
            }
        }catch (IOException e) {
            throw new RuntimeException();
        }
    }
}