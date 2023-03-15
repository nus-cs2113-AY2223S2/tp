package seedu.duke;

import command.CommandAdd;
import command.CommandDelete;
import command.CommandList;
import data.ExpenseList;
import parser.Parser;

import java.util.Scanner;

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
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

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
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
