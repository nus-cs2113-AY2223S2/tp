package seedu.duke;

import command.CommandAdd;
import command.CommandDelete;
import command.CommandList;
import data.ExpenseList;
import parser.Parser;

import java.util.Scanner;

import static common.MessageList.HELLO_MESSAGE;
import static common.MessageList.COMMAND_LIST_MESSAGE;
import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.NAME_QUESTION;
import static data.ExpenseList.showToUser;

public class Duke {

    protected Parser parser;
    protected ExpenseList expenseList;

    /**
     * Initialize Duke and instantiate parser and expenseList objects.
     */
    public Duke() {
        parser = new Parser();
        expenseList = new ExpenseList();
    }

    public void run() {
    
        showToUser(HELLO_MESSAGE, MESSAGE_DIVIDER, COMMAND_LIST_MESSAGE, MESSAGE_DIVIDER, NAME_QUESTION);

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        String input = in.nextLine();
        while (!input.equals("exit")) {
            switch (parser.extractCommandKeyword(input)) {
            case "add":
                new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters(input)).execute();
                break;
            case "delete":
                new CommandDelete(expenseList.getExpenseList(), parser.extractIndex(input)).execute();
                break;
            case "list":
                new CommandList(expenseList.getExpenseList()).run();
                break;
            default:
                System.out.println("Unknown command.");
                break;
            }
            input = in.nextLine();
        }
        in.close();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
