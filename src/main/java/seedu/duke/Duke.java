package seedu.duke;

import command.CommandAdd;
import command.CommandCategory;
import command.CommandDelete;
import command.CommandList;
import command.CommandSort;
import command.CommandTotal;
import command.CommandFind;
import command.overview.CommandOverview;
import command.CommandHelp;
import data.ExpenseList;
import data.Currency;
import parser.Parser;
import storage.Storage;
import common.WelcomeMessage;

import java.util.Scanner;

public class Duke {

    protected static Storage storage;
    protected Parser parser;
    protected ExpenseList expenseList;
    protected Currency currency;

    protected String filePath = "expenses.json";


    /**
     * Initialize Duke and instantiate parser and expenseList objects.
     */
    public Duke() {
        parser = new Parser();
        expenseList = new ExpenseList();
        currency = new Currency();
        storage = new Storage(expenseList);
        expenseList.setExpenseList(storage.loadExpenses(filePath));
    }

    public void run() {

        Scanner in = new Scanner(System.in);

        WelcomeMessage.welcomeHelper();

        String input = "";
        while (in.hasNextLine()) {
            input = in.nextLine();
            if (input.equals("exit")) {
                break;
            }
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
            case "sort":
                new CommandSort(expenseList.getExpenseList(), parser.extractSortBy(input)).execute();
                break;
            case "category":
                new CommandCategory(expenseList.getExpenseList(), parser.extractCategory(input)).execute();
                break;
            case "overview":
                new CommandOverview(expenseList.getExpenseList(),
                        parser.extractMonth(input), parser.extractYear(input)).execute();
                break;
            case "find":
                // Use the same parser function as category as it also need the input string from user
                new CommandFind(expenseList.getExpenseList(), parser.extractCategory(input)).execute();
                break;
            case "help":
                new CommandHelp().execute();
                break;
            default:
                System.out.println("Unknown command.");
                break;
            }
            storage.saveExpenses(filePath);
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
