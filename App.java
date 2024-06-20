package task2;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int num = 0;
        while (num != 10){
            menu();
            num = input.nextInt();
            input.nextLine();
            if(num == 1){
                Library.selectALibrary();
            } else if(num == 2) {
                Library library = new Library();
                library.addLibraries(library);
            }
        }
    }

       public static void menu(){
        System.out.println("""
                Hello! Welcome to Library Management System!
                1 - Press 1 to select a library.
                2 - Press 2 to add a new library.
                10 - Press 10 to exit the app.
                """);
       }

}

