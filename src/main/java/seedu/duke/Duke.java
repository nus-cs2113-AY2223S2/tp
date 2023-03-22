package seedu.duke;

import command.CommandAdd;
import command.CommandDelete;
import command.CommandList;
import command.CommandTotal;
import data.ExpenseList;
import data.Currency;
import parser.Parser;

import java.util.Scanner;

public class Duke {

    protected Parser parser;
    protected ExpenseList expenseList;
    protected Currency currency;

    /**
     * Initialize Duke and instantiate parser and expenseList objects.
     */
    public Duke() {
        parser = new Parser();
        expenseList = new ExpenseList();
        currency = new Currency();
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
        while (!input.equals("bye")) {
            switch (parser.extractCommandKeyword(input)) {
            case "add":
                new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters(input), currency).execute();
                break;
            case "delete":
                new CommandDelete(expenseList.getExpenseList(), parser.extractIndex(input)).execute();
                break;
            case "list":
                new CommandList(expenseList.getExpenseList()).run();
                break;
            case "total":
                new CommandTotal(expenseList.getExpenseList()).execute();
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
