package task2.system;

import task2.structures.Library;
import task2.structures.Member;
import task2.structures.Admin;
import task2.structures.Book;
import java.util.List;

public interface LibraryService {

    void addBook(Library library, Book book);

    Book searchBookByTitle(Library library, String title);

    Book searchBookByISBN(Library library, String ISBN);

    List<Book> searchBooksByAuthor(Library library, String author);

    List<Book> searchBooksByPublicationYear(Library library, String yearOfPublication);

    void removeBookByTitle(Library library, String title);

    void removeBookByISBN(Library library, String ISBN);

    void removeBooksByAuthor(Library library, String author);

    void removeBooksByPublicationYear(Library library, String publicationYear);

    List<Book> booksAvailability(Library library);

    void borrowBook(Library library, Member member, String title);

    void returnBook(Library library, Member member, String title);

    void addMember(Library library, Member member);

    void addAdmin(Library library, Admin admin);

    void printMembers(Library library);

    Member searchMemberById(Library library, int id);

    Member searchMemberByName(Library library, String name);
}
