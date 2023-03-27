package parser;

import data.Account;
import java.util.Scanner;


public class ParserPassword {
    public static void caseLogIn() {
        System.out.println("username");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        System.out.println("Password");
        String password = scanner.nextLine();
        Account existingAccount = new Account(user, password);
        existingAccount.login();
    }

    public static void caseSignUp() {
        System.out.println("username");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        System.out.println("Password");
        String password = scanner.nextLine();
        Account newAccount = new Account(user, password);
        newAccount.signup();
    }
}




