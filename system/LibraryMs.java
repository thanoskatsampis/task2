package task2.system;


import task2.structures.Admin;
import task2.structures.Book;
import task2.structures.Library;
import task2.structures.Member;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class LibraryMS implements LibraryService {

    private Set<Library> libraries = new HashSet<>();

    private static final String FILE_LIBRARY = "Library_data.txt";

    public Set<Library> getLibraries() {
        return libraries;
    }

    @Override
    public void addBook(Library library, Book book) {
        for (Book b : library.getBooks()) {
            if (book.equals(b)) {
                System.out.println(book.getTitle() + " is already exist.");
                return;
            }
        }
        library.getBooks().add(book);
        System.out.println(book.getTitle() + " is successfully added.");

    }

    @Override
    public Book searchBookByTitle(Library library, String title) {
        for(Book b : library.getBooks()){
            if(b.getTitle().equals(title)){
                return b;
            }
        }
        System.out.println("Invalid data!");
        return null;
    }

    @Override
    public Book searchBookByISBN(Library library, String ISBN) {
        for(Book b : library.getBooks()){
            if(b.getISBN().equals(ISBN)){
                return b;
            }
        }
        System.out.println("Invalid data!");
        return null;
    }

    @Override
    public List<Book> searchBooksByAuthor(Library library, String author) {
        List<Book> booksByAuthor = new ArrayList<>();
        for(Book b : library.getBooks()){
            if(b.getAuthor().equals(author)){
                booksByAuthor.add(b);
            }
        }
        return booksByAuthor;
    }

    @Override
    public List<Book> searchBooksByPublicationYear(Library library, String yearOfPublication) {
        List<Book> booksByPublicationsYear = new ArrayList<>();
        for(Book b : library.getBooks()){
            if(b.getPublicationYear().equals(yearOfPublication)){
                booksByPublicationsYear.add(b);
            }
        }
        return booksByPublicationsYear;
    }

    @Override
    public void removeBookByTitle(Library library, String title) {
        Book book = searchBookByTitle(library, title);
        if(book != null){
            library.getBooks().remove(book);
            System.out.println(book.getTitle() + " removed from the library.");
        }
    }

    @Override
    public void removeBookByISBN(Library library, String ISBN) {
        Book book = searchBookByISBN(library, ISBN);
        if(book != null){
            library.getBooks().remove(book);
            System.out.println(book.getTitle() + " removed from the library.");
        }else
            System.out.println("There is no book with this ISBN in the library.");
    }

    @Override
    public void removeBooksByAuthor(Library library, String author) {
        searchBooksByAuthor(library, author).forEach(library.getBooks()::remove);
        System.out.println(" You successfully removed all author's books from the library.");
    }

    @Override
    public void removeBooksByPublicationYear(Library library, String publicationYear) {
        searchBooksByPublicationYear(library, publicationYear).forEach(library.getBooks()::remove);
        System.out.println(" You successfully removed all books which published this year from the library.");
    }

    @Override
    public List<Book> booksAvailability(Library library) {
        List<Book> listOfAvailableBooks = new ArrayList<>();
        for(Book b : library.getBooks()){
            if(b.isAvailable()){
                listOfAvailableBooks.add(b);
            }
        }
        return listOfAvailableBooks;
    }

    @Override
    public void borrowBook(Library library, Member member, String title) {
        Book book = searchBookByTitle(library, title);
        if(book == null){
            System.out.println("You enter wrong data");
            return;
        }
        if(book.isAvailable()){
            book.setAvailable(false);
            library.getListOfAllBorrowedBooksWithTheirMembers().put(book.getTitle(), member.getId());
            System.out.println(book.getTitle() + " is borrowed by " + member.getId());
        } else
            System.out.println("This book is not available wright now");
    }

    @Override
    public void returnBook(Library library, Member member, String title) {
        Book book = searchBookByTitle(library, title);
        if(book == null){
            System.out.println("You enter wrong data");
            return;
        }
        if(!book.isAvailable()){
            book.setAvailable(true);
            library.getListOfAllBorrowedBooksWithTheirMembers().remove(book.getTitle(), member.getId());
            System.out.println(member.getName() + " returned " + book.getTitle());

        }else
            System.out.println("This book wasn't borrowed.");
    }

    @Override
    public void addMember(Library library, Member member) {
        for(Member m : library.getMembers()) {
            if (member.equals(m)) {
                System.out.println(m.getName() + "is already member.");
                return;
            }
        }
        library.getMembers().add(member);
        System.out.println(member.getName() + " registered successfully.");
    }

    @Override
    public void addAdmin(Library library, Admin admin) {
        for(Admin a : library.getAdmins()) {
            if (admin.equals(a)) {
                System.out.println(admin.getName() + "is already exists.");
                return;
            }
        }
        library.getAdmins().add(admin);
        System.out.println(admin.getName() + " registered successfully.");
    }

    @Override
    public void printMembers(Library library) {
        System.out.println("LibraryMembers{" + library.getMembers() + '}');
    }

    @Override
    public Member searchMemberById(Library library, int id) {
        for(Member member : library.getMembers()){
            if(member.getId() == id){
                return member;
            }
        }
        return null;
    }

    @Override
    public Member searchMemberByName(Library library, String name) {
        for(Member member : library.getMembers()){
            if(member.getName().equals(name)){
                return member;
            }
        }
        return null;
    }

    public void saveLibraryDataToFile() {
        try (FileWriter writer = new FileWriter(FILE_LIBRARY)) {
            for (Library library : libraries) {
                writer.write("Library:");
                writer.write( "~" + library.getLibraryName() + "~" + library.getLibraryAddress() + "\n");
                for (Book book : library.getBooks()) {
                    writer.write("Book:");
                    writer.write("~" + book.getTitle() + "~" + book.getAuthor() + "~" + book.getISBN() + "~" + book.getPublicationYear() + "~" + book.isAvailable() + "\n");
                }
                for (Member member : library.getMembers()) {
                    writer.write("Member:");
                    writer.write("~" +  member.getName() + "~" + member.getId() + "~" + member.getContactDetails() + "\n");
                }
                for (Admin admin : library.getAdmins()) {
                    writer.write("Admin:");
                    writer.write("~" +  admin.getName() + "~" + admin.getId() + "~" + admin.getContactDetails() + "\n");
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
                        Book book = new Book(arrOfStr[1], arrOfStr[2], arrOfStr[3], arrOfStr[4]);
                        currentLibrary.getBooks().add(book);
                    }
                } else if (line.startsWith("Member:")) {
                    if (currentLibrary != null) {
                        Member member = new Member(arrOfStr[1], Integer.parseInt(arrOfStr[2]), arrOfStr[3]);
                        currentLibrary.getMembers().add(member);
                    }
                }
                else if (line.startsWith("Admin:")) {
                    if (currentLibrary != null) {
                        Admin admin = new Admin(arrOfStr[1], Integer.parseInt(arrOfStr[2]), arrOfStr[3]);
                        currentLibrary.getAdmins().add(admin);
                    }
                }
            }
        }catch (IOException e) {
            System.out.println(" ");
        }
    }
}
