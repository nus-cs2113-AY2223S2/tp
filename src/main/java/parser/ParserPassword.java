package parser;

import data.Account;
import java.util.Scanner;

import static common.MessageList.COMMAND_LIST_MESSAGE;
import static common.MessageList.MESSAGE_DIVIDER;
import static data.Account.logout;
import static data.ExpenseList.showToUser;


public class ParserPassword {
    public static void caseLogIn() {
        System.out.println("username");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        System.out.println("Password");
        String password = scanner.nextLine();
        Account existingAccount = new Account(user, password);
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

    public static void caseSignUp() {
        System.out.println("username");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        System.out.println("Password");
        String password = scanner.nextLine();
        Account newAccount = new Account(user, password);
        newAccount.signup();
    }

    public static void caseLogOut() {

    }

    public static void initialize(Scanner in) {
        do {
            System.out.println("Enter \"login\", \"signup\", or \"logout\"");
            String input = in.nextLine();
            if (input.equals("login")) {
                // get login details
                caseLogIn();
                break;
            } else if (input.equals("signup")) {
                // get register details
                caseSignUp();
            } else if (input.equals("logout")) {
                logout();
                break; // exit the loop
            } else {
                // invalid input, tell them to try again
                System.out.println("invild option, chose login or signup!");
                input = in.nextLine();
            }
        } while (true);
    }

}




