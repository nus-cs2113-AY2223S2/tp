package seedu.duke.ui;


import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    static HashMap<String, String> nameToPassword = new HashMap<>();
    public static void showMenu() {
        System.out.println("Welcome to the Duke App!");
        System.out.println("What would you like to do?");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    public static void register() {
        System.out.println("Please enter your name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Please enter your password: ");
        String password = new Scanner(System.in).nextLine();
        System.out.println("Please re-enter your password: ");
        String password2 = new Scanner(System.in).nextLine();
        if (password.equals(password2)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Passwords do not match!");
        }
        nameToPassword.put(name, password); //store name as key and password as value
    }

    public static void login() {
        System.out.println("Please enter your name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Please enter your password: ");
        String password = new Scanner(System.in).nextLine();
        if (nameToPassword.containsKey(name)) {
            if (nameToPassword.get(name).equals(password)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Wrong password!");
            }
        } else {
            System.out.println("User does not exist!");
        }
    }

    public static void exit() {
        System.out.println("Thank you for using Dr Duke!");
        System.exit(0);
    }
}
