package parser;

import data.Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static data.Account.login;

public class ParserPassword {
    String user;
    String pass;
    public static void parsePassword(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        boolean validPassword = isValidPassword(password, regex);
        System.out.println(password + " is valid password:" + validPassword);

    }

    public static boolean isValidPassword(String password, String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public void run() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        Scanner keyboard = new Scanner (System.in);
        String user = scan.nextLine();
        String pass = scan.nextLine();

        String inUser = keyboard.nextLine();
        String inPass = keyboard.nextLine(); // gets input from user

        if (inUser.equals(user) && inPass.equals(pass)) {
            System.out.print("your login message");
        } else {
            System.out.print("your error message");
        }
    }

    public static void caseLogIn() {
        System.out.println("username");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        System.out.println("Password");
        String password = scanner.nextLine();
        int a = Account.accountNumber;
        login(user, password);
        System.out.println("");
    }

    public static void caseSignUp() {
        System.out.println("username");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        System.out.println("Password");
        String password = scanner.nextLine();
        Account userName = new Account(user, password);
        System.out.println("");
    }
}




