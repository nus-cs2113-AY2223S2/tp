package parser;

import data.Account;
import java.util.Scanner;

import static common.MessageList.COMMAND_LIST_MESSAGE;
import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.ACCOUNT_MESSAGE;
import static common.MessageList.SAVING_QUESTION_MESSAGE;
import static common.MessageList.SAVING_EXIT_MESSAGE;

import static data.Account.account;
import static data.Account.save;
import static data.ExpenseList.showToUser;


public class ParserAccount {
    private static void caseLogIn() {
        System.out.println("username");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        System.out.println("Password");
        String password = scanner.nextLine();
        Account existingAccount = new Account(user, password);
        String filepath = "./src/main/java/storage/" + user + ".json";
        String res = existingAccount.login();
        System.out.println(res);
        if (res.equals("Invalid username or password.")
            || res.equals("Sorry, there is no username found")
            || res.equals("An error occurred while logging in.")
            || res.equals("Log In Failed. Invalid login credentials")) {
            initialize(scanner);
        } else {
            showToUser(MESSAGE_DIVIDER, COMMAND_LIST_MESSAGE, MESSAGE_DIVIDER);
        }
    }

    private static void caseSignUp() {
        System.out.println("username");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        System.out.println("Password");
        String password = scanner.nextLine();
        Account newAccount = new Account(user, password);
        String filepath = "./src/main/java/storage/" + user + ".json";
        newAccount.signup();
    }

    public static String caseLogOut() {
        Scanner scanner = new Scanner(System.in);
        String res = scanner.nextLine();
        if (res.equals("yes")) {
            save();
        } else if (res.equals("no")) {
            account.clear();
            return res;
        } else if (res.equals("cancel")) {
            return res;
        } else {
            System.out.println("Invalid command");
            showToUser(MESSAGE_DIVIDER, SAVING_QUESTION_MESSAGE, MESSAGE_DIVIDER);
            caseLogOut();
            return res;
        }
        return res;
    }

    public static String caseExit() {
        Scanner scanner = new Scanner(System.in);
        String res = scanner.nextLine();
        if (res.equals("yes")) {
            save();
        } else if (res.equals("no")) {
            account.clear();
            return res;
        } else {
            System.out.println("Invalid command");
            showToUser(MESSAGE_DIVIDER, SAVING_EXIT_MESSAGE, MESSAGE_DIVIDER);
            caseLogOut();
            return res;
        }
        return res;
    }

    public static void initialize(Scanner in) {
        do {
            showToUser(MESSAGE_DIVIDER, ACCOUNT_MESSAGE, MESSAGE_DIVIDER);
            String input = in.nextLine();
            if (input.equals("login")) {
                // get login details
                caseLogIn();
                break;
            } else if (input.equals("signup")) {
                // get register details
                caseSignUp();
            } else {
                // invalid input, tell them to try again
                System.out.println("invalid option, chose login or signup!");
                initialize(in);
                break;
            }
        } while (true);
    }

}




