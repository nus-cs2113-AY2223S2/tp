package parser;

import data.Account;
import java.util.Scanner;

import static common.AccountMessage.INVALID_ACCOUNT_MESSAGE;
import static common.AccountMessage.NON_EXISTING_ACCOUNT_MESSAGE;
import static common.AccountMessage.FAIL_READING_USERNAME_MESSAGE;
import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.ACCOUNT_MESSAGE;
import static common.MessageList.LOGOUT_MESSAGE;
import static common.MessageList.EXIT_MESSAGE;
import static data.Account.saveLogOut;
import static data.ExpenseList.showToUser;
import static java.lang.System.exit;


public class ParserAccount {
    private static void caseLogIn(Scanner scanner) {
        System.out.println("username");
        String user = scanner.nextLine();
        System.out.println("password");
        String password = scanner.nextLine();
        Account existingAccount = new Account(user, password);
        String res = existingAccount.login();
        System.out.println(res);
        if (res.equals(INVALID_ACCOUNT_MESSAGE)
            || res.equals(NON_EXISTING_ACCOUNT_MESSAGE)
            || res.equals(FAIL_READING_USERNAME_MESSAGE)) {
            initialize(scanner);
        }
    }

    public static void caseSignUp(Scanner scanner) {
        System.out.println("username");
        String user = scanner.nextLine();
        System.out.println("password");
        String password = scanner.nextLine();
        Account newAccount = new Account(user, password);
        System.out.println(newAccount.signup());
    }

    public static void caseLogOut(Scanner scanner) {
        System.out.println(saveLogOut());
        showToUser(LOGOUT_MESSAGE);
        initialize(scanner);
    }

    public static void caseExit() {
        System.out.println(saveLogOut());
        showToUser(EXIT_MESSAGE);
    }

    public static void initialize(Scanner in) {
        do {
            showToUser(MESSAGE_DIVIDER, ACCOUNT_MESSAGE, MESSAGE_DIVIDER);
            String input = in.nextLine();
            if (input.equals("login")) {
                // get login details
                caseLogIn(in);
                break;
            } else if (input.equals("signup")) {
                // get register details
                caseSignUp(in);
            } else if (input.equals("exit")) {
                exit(0);
            } else {
                // invalid input, tell them to try again
                System.out.println("invalid option, chose login or signup!");
                initialize(in);
                break;
            }
        } while (true);
    }

}




