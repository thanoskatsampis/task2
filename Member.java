package task2;

import java.util.Scanner;

public class Member {

    public String name;
    public int Id;
    public String contactDetails;
    public boolean isAdmin;
    Scanner memberScanner = new Scanner(System.in);

    public Member() {
    System.out.println("Give member's name: ");
        this.name = memberScanner.nextLine();

        System.out.println("Give member's id: ");
        this.Id = memberScanner.nextInt();
        memberScanner.nextLine();

        System.out.println("Give member's contact details ");
        this.contactDetails = memberScanner.nextLine();

        System.out.println("Is this member the library's admin?(answer true/false) ");
        this.isAdmin = memberScanner.nextBoolean();

    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name: '" + name + '\'' +
                ", Id: " + Id +
                ", contactDetails: " + contactDetails + '\'' +
                ", isAdmin: " + isAdmin + '\'' +
                '}';
    }
}
