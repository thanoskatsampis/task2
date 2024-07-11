package task2.app;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        AppScreen ls = new AppScreen();
        ls.getLibraryMS().loadLibraryDataFromFile();
        Scanner input = new Scanner(System.in);
        while (true) {
            ls.menu();
            try {
                int num = input.nextInt();
                input.nextLine();
                if (num == 1) {
                    ls.selectLibrary();
                } else if (num == 2) {
                    ls.addLibrary();
                } else if (num == 3) {
                    ls.getLibraryMS().saveLibraryDataToFile();
                    System.exit(0);
                }
            } catch (Exception e){
                System.out.println("Enter a number.");
                input.nextLine();
            }
        }
    }
}