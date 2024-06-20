package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryMembers {
    private static int counter;

    static Scanner scanner = new Scanner(System.in);

    static List<Member> listOfAllMembers = new ArrayList<>();

    public int compareMembers(Member member) {
        for (Member m : listOfAllMembers) {
            if (m.Id == member.Id) {
                return 0;
            }
        }
        return 1;
    }

    public void addMember() {
        Member member = new Member();
        if(member.isAdmin){
            counter++;
            if(counter > 1){
                System.out.println("Only one administrator is allowed. ");
                member.setAdmin(false);
            }
        }
        if (compareMembers(member) == 0) {
            System.out.println("Your Id already exists in the system.");
            return;
        }
        listOfAllMembers.add(member);
    }

    public static void listOfAllMembers() {
        System.out.println("LibraryMembers{" +
                "listOfAllMembers: " + listOfAllMembers +
                '}');
    }

    public static Member searchMemberByName() {
        System.out.println("Enter member's name: ");
        String name = scanner.nextLine();
        for (Member member : listOfAllMembers) {
            if (member.name.equals(name)) {
                return member;
            }
        }
        return null;
    }

    public static Member searchMemberById(){
        System.out.println("Enter member's id: ");
        int num = scanner.nextInt();
        for (Member member : listOfAllMembers) {
            if (member.Id == num) {
                return member;
            }
        }
        return null;
    }
}
