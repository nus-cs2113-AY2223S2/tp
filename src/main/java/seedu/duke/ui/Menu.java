//@@author Jeraldchen

package seedu.duke.ui;

import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    static HashMap<String, String> personalInfo = new HashMap<>(); //name to password
    public static void showMenu() {
        System.out.println("Welcome to the Dr Duke!");
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
            personalInfo.put(name, password);
        } else {
            System.out.println("Registration failed!");
        }
    }

    public static void login() {
        System.out.println("Please enter your name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Please enter your password: ");
        String password = new Scanner(System.in).nextLine();
        if (personalInfo.containsKey(name) && personalInfo.get(name).equals(password)) {
            System.out.println("Login successful!");
            System.out.println("Welcome " + name + "!");
        } else {
            System.out.println("Login failed!");
        }
    }

    public static void exit() {
        System.out.println("Thank you for using Dr Duke!");
        System.exit(0);
    }
}
