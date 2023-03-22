package chching;

import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Record;

import java.util.Scanner;

/**
 * Models a class for user interface of the program
 */
public class Ui {
    public String readCommand() {
        Scanner input = new Scanner((System.in));
        return input.nextLine();
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm ChChing.");
        System.out.println("    What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public void showAdded(IncomeList incomes, ExpenseList expenses, Record record) {
        System.out.println("    Got it. I've added this record:");
        System.out.println("    " + record);
        System.out.println();
        assert(incomes.size() >= 0);
        System.out.println("    Now you have " + incomes.size() + " income records,");
        System.out.println("    and " + expenses.size() + " expense records in the list.");
    }

    public void showDelete(IncomeList incomes, ExpenseList expenses, Record record) {
        System.out.println("    Noted. I've removed this record:");
        System.out.println("    " + record);
        System.out.println();
        assert(expenses.size() >= 0);
        System.out.println("    Now you have " + incomes.size() + " income records,");
        System.out.println("    and " + expenses.size() + " expense records in the list.");
    }

    public void showAllRecords(IncomeList incomes, ExpenseList expenses) {
        System.out.println("    Here are the incomes in your list:");
        incomes.printIncomeList();
        
        System.out.println();
        
        System.out.println("    Here are the expense in your list:");
        expenses.printExpenseList();
    }

    public void showBalance(double totalExpense, double totalIncome, double balance) {
        assert(totalExpense >= 0);
        System.out.println("    Total Expense: " + String.format("%.02f", totalExpense));
        System.out.println("    Total Income: " + String.format("%.02f", totalIncome));
        System.out.println();
        System.out.println("    Current balance:");
        System.out.println("    SGD " + String.format("%.02f", balance));
    }


    public static void showHelp() {
        System.out.println("    ADD RECORDS:");
        System.out.println("    add expense /c <category> /de <description> /da <date> /v <value>");
        System.out.println("    add income /de <description> /da <date> /v <value>");
        System.out.println();
        System.out.println("    SHOW ALL RECORDS:");
        System.out.println("    list");
        System.out.println();
        System.out.println("    DELETE RECORDS:");
        System.out.println("    delete expense /in <index>");
        System.out.println("    delete income /in <index>");
        System.out.println();
        System.out.println("    SHOW BALANCE:");
        System.out.println("    balance");
        System.out.println();
        System.out.println("    SHOW HELP:");
        System.out.println("    help");
        System.out.println();
        System.out.println("    EXIT THE PROGRAM:");
        System.out.println("    exit");
    }

    public void showError(String message) {
        System.out.println("    " + message);
    }

    public void showInvalidMessage() {
        System.out.println("    Command is invalid, use help for valid commands");
    }
}
