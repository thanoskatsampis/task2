package task2.task;

import java.util.Scanner;

public class Member {

    private String name;
    private int Id;
    private String contactDetails;
    private boolean isAdmin;

    private Scanner scanner = new Scanner(System.in);

    public Member() {
        System.out.println("Enter member's name: ");
        this.name = scanner.nextLine();

        while(true){
            try {
                System.out.println("Enter member's id:(number) ");
                this.Id = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Enter a number.");
                scanner.nextLine();
            }
        }
        scanner.nextLine();

        System.out.println("Enter member's contact details ");
        this.contactDetails = scanner.nextLine();

        while(true){
            try{
                System.out.println("Is this member the library's admin?(enter true/false) ");
                this.isAdmin = scanner.nextBoolean();
                break;
            }catch (Exception e) {
                System.out.println("Enter true or false..");
                scanner.nextLine();
            }
        }
    }

    public Member(String name, int id, String contactDetails, boolean isAdmin) {
        this.name = name;
        this.Id = id;
        this.contactDetails = contactDetails;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return Id;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name: " + name + '\'' +
                ", Id: " + Id +
                ", contactDetails: " + contactDetails + '\'' +
                ", isAdmin: " + isAdmin + '\'' +
                '}';
    }
}
