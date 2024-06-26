package task2;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        LibraryMS libraryMS = new LibraryMS();
        Scanner input = new Scanner(System.in);
        int num = 0;
        while (num != 10) {
            menu();
            try {
                num = input.nextInt();
                input.nextLine();
                if (num == 1) {
                    libraryMS.selectLibrary();
                } else if (num == 2) {
                    libraryMS.addLibrary();
                } else if (num == 3) {
                    libraryMS.loadLibraryDataFromFile();
                }
            } catch (Exception e){
                System.out.println("Enter a number.");
                input.nextLine();
            }
        }
    }

    public static void menu(){
        System.out.println("""
                Hello! Welcome to Library Management System!
                1 - Press 1 to select a library.
                2 - Press 2 to add a new library.
                3 - Press 3 to load Library Management System data.
                10 - Press 10 to exit the app.
                """);
       }

}

