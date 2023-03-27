package parser;

import data.Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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




