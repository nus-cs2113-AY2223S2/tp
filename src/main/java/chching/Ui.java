package chching;

import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Record;
import chching.record.RecordList;

import java.util.Scanner;

public class Ui {

    public String readCommand() {
        Scanner input = new Scanner((System.in));
        String fullCommand = input.nextLine();
        return fullCommand;
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
        System.out.println("    Now you have " + incomes.getRecordCount() + " income records,");
        System.out.println("    and " + expenses.getRecordCount() + " expense records in the list.");
    }

    public void showDelete(IncomeList incomes, ExpenseList expenses, Record record) {
        System.out.println("    Noted. I've removed this record:");
        System.out.println("    " + record);
        System.out.println("    Now you have " + incomes.getRecordCount() + " income records,");
        System.out.println("    and " + expenses.getRecordCount() + " expense records in the list.");
    }

    public void showAllRecords(IncomeList incomes, ExpenseList expenses) {
        System.out.println("    Here are the incomes in your list:");
        for (int i = 0; i < incomes.getRecordCount(); i++) {
            System.out.println("    " + (i+1) + ". "  + incomes.get(i));
        }
        System.out.println();
        System.out.println("    Here are the expense in your list:");
        for (int i = 0; i < expenses.getRecordCount(); i++) {
            System.out.println("    " + (i+1) + ". "  + expenses.get(i));
        }
    }

    public void showBalance(double balance) {
        System.out.println("    Current balance:");
        System.out.println(String.format("    SGD %.2f", balance));
    }


    public static void showHelp() {
        System.out.println("    ADD RECORDS:");
        System.out.println("    add expense /c <category> /de <description> /da <date> /v <value>");
        System.out.println("    add income");
        System.out.println();
        System.out.println("    SHOW ALL RECORDS:");
        System.out.println("    list");
        System.out.println();
        System.out.println("    EDIT RECORDS:");
        System.out.println("    edit expense <index> /c <category> /de <description> /da <date> /v <value>");
        System.out.println("    edit income <index>");
        System.out.println();
        System.out.println("    DELETE RECORDS:");
        System.out.println("    delete expense <index>");
        System.out.println("    delete income <index>");
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
}
