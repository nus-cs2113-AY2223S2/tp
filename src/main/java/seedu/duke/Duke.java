package seedu.duke;

import command.CommandAdd;
import command.CommandCategory;
import command.CommandDelete;
import command.CommandList;
import command.CommandSort;
import command.CommandCategory;
import data.Account;
import command.CommandTotal;
import command.CommandFind;
import command.overview.CommandOverview;
//import command.CommandHelp;
import data.ExpenseList;
import data.Currency;
import parser.Parser;
import storage.Storage;
//import common.WelcomeMessage;

import java.util.Scanner;

import static common.MessageList.HELLO_MESSAGE;
import static common.MessageList.COMMAND_LIST_MESSAGE;
import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.NAME_QUESTION;
import static data.Account.logout;
//import static data.ExpenseList.showToUser;
import static parser.ParserPassword.*;


public class Duke {

    protected static Storage storage;
    protected Parser parser;
    protected ExpenseList expenseList;
    protected Currency currency;

    //protected Storage storage;


    //TODO: arbitrary filePath
    protected String filePath = "test.json";

    /**
     * Initialize Duke and instantiate parser and expenseList objects.
     */
    public Duke() {
        parser = new Parser();
        expenseList = new ExpenseList();
        currency = new Currency();
        storage = new Storage(expenseList);
        //expenseList.setExpenseList(storage.loadExpenses(filePath));
    }

    public void run() {
        String logo =
                  " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n");
       // WelcomeMessage.printWelcomeLogo();
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        if (in.hasNextLine()) {
            System.out.println("Hello " + in.nextLine());
        }
        initialize(in);
        //WelcomeMessage.welcomeHelper();
        String input = "";
        while (in.hasNextLine()) {
            input = in.nextLine();
            if(input.equals("exit")) {
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
            case "logout":
                logout();
                initialize(in);
            case "overview":
                new CommandOverview(expenseList.getExpenseList(),
                        parser.extractMonth(input), parser.extractYear(input)).execute();
                break;
            case "find":
                // Use the same parser function as category as it also need the input string from user
                new CommandFind(expenseList.getExpenseList(), parser.extractCategory(input)).execute();
                break;
            case "help":
                //new CommandHelp().execute();
                break;
            default:
                System.out.println("Unknown command.");
            }
            //storage.saveExpenses(filePath);
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
