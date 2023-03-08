package seedu.duke.ui;


import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void showMenu() {
        System.out.println("Welcome to the Duke App!");
        System.out.println("What would you like to do?");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    public static void register() {
        ArrayList<String> register = new ArrayList<>();
        System.out.println("Please enter your name: ");
        int count = 0;
        register.add(count, new Scanner(System.in).nextLine());
        System.out.println("Please enter your email: ");
        register.add(count + 1, new Scanner(System.in).nextLine());
        System.out.println("Please enter your contact number: ");
        register.add(count + 2, new Scanner(System.in).nextLine());
    }

    public static void login() {
        System.out.println("Please enter your name: ");
        String name = new Scanner(System.in).nextLine();
    }

    public static void exit() {
        System.out.println("Thank you for using Duke App!");
        System.exit(0);
    }
}
