package task2.structures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Library {

    private String libraryName;
    private String libraryAddress;
    private Set<Member> members = new HashSet<>();
    private Set<Book> books = new HashSet<>();
    private Set<Admin> admins = new HashSet<>();
    private HashMap<String, Integer> listOfAllBorrowedBooksWithTheirMembers = new HashMap<>();

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

    public Set<Member> getMembers() {
        return members;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public Set<Admin> getAdmins() {
        return admins;
    }

    public HashMap<String, Integer> getListOfAllBorrowedBooksWithTheirMembers() {
        return listOfAllBorrowedBooksWithTheirMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(libraryAddress, library.libraryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(libraryAddress);
    }

    @Override
    public String toString() {
        return "Library{" +
                "libraryName: " + libraryName + '\'' +
                ", libraryAddress: " + libraryAddress + '\'' +
                '}';

    }
}