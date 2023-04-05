package chching;

import chching.currency.Selector;
import chching.currency.Converter;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Record;
import chching.record.RecordList;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Models a class for user interface of the program
 */
public class Ui {
    public String readCommand() {
        Scanner input = new Scanner((new InputStreamReader(System.in, Charset.forName("UTF-8"))));
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
        assert (incomes.size() >= 0);
        System.out.println("    Now you have " + incomes.size() + " income records,");
        System.out.println("    and " + expenses.size() + " expense records in the list.");
    }

    public void showEdited(int index, Record record, boolean isExpense) {
        if (isExpense) {
            System.out.println("    Got it. I've updated the expense in index " + index + " to");
        } else {
            System.out.println("    Got it. I've updated the income in index " + index + " to");
        }
        System.out.println("    " + record);
    }

    public void showDelete(IncomeList incomes, ExpenseList expenses, Record record) {
        System.out.println("    Noted. I've removed this record:");
        System.out.println("    " + record);
        System.out.println();
        assert (expenses.size() >= 0);
        System.out.println("    Now you have " + incomes.size() + " income records,");
        System.out.println("    and " + expenses.size() + " expense records in the list.");
    }

    public void showAllRecords(IncomeList incomes, ExpenseList expenses, Selector selector, Converter converter) {
        if(incomes.size() == 0 && expenses.size() == 0) {
            System.out.println("    Both lists are empty. Start adding!");
        } else if(incomes.size() == 0) {
            System.out.println("    Income list is empty. Start adding!");
            System.out.println();
            showExpenseList(expenses, selector, converter);
        } else if(expenses.size() == 0) {
            showIncomeList(incomes, selector, converter);
            System.out.println();
            System.out.println("    Expense list is empty. Start adding!");
        } else {
            showIncomeList(incomes, selector, converter);
            System.out.println();
            showExpenseList(expenses, selector, converter);
        }
    }

    public void showBalance(double totalExpense, double totalIncome, double balance, String convertedBalance) {
        assert (totalExpense >= 0);
        System.out.println("    Total Expense: " + String.format("%.02f", totalExpense));
        System.out.println("    Total Income: " + String.format("%.02f", totalIncome));
        System.out.println();
        System.out.println("    Current balance:");
        System.out.println("    SGD " + String.format("%.02f", balance) + convertedBalance);
    }

    public void showMatchedRecord(RecordList records) {
        if (records.getRecordCount() > 0) {
            System.out.println("    Here are the matching records in your list:");

            for (int i = 0; i < records.getRecordCount(); i++) {
                System.out.println("    " + (i + 1) + ". " + records.get(i));
            }
        } else {
            System.out.println("    No matching records for those keyword");
        }

    }

    public void showMatchedIncome(IncomeList incomes) {
        if (incomes.size() > 0) {
            System.out.println("    Here are the matching records in your list:");

            for (int i = 0; i < incomes.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + incomes.get(i));
            }
        } else {
            System.out.println("    No matching records for those keyword");
        }

    }

    public void showMatchedExpense(ExpenseList expenses) {
        if (expenses.size() > 0) {
            System.out.println("    Here are the matching records in your list:");

            for (int i = 0; i < expenses.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + expenses.get(i));
            }
        } else {
            System.out.println("    No matching records for those keyword");
        }

    }

    public static void showHelp() {
        System.out.println("    ADD RECORDS:");
        System.out.println("    add expense /c <category> /de <description> /da <date> /v <value>");
        System.out.println("    add income /de <description> /da <date> /v <value>");
        System.out.println();
        System.out.println("    SHOW ALL RECORDS:");
        System.out.println("    list");
        System.out.println();
        System.out.println("    EDIT RECORDS:");
        System.out.println("    edit expense /in <index> [/c <category>] [/de <description>] [/da <date>] " +
                "[/v <value>]");
        System.out.println("    edit income /in <index> [/de <description>] [/da <date>] [/v <value>]");
        System.out.println("    Fields with [] are optional");
        System.out.println();
        System.out.println("    DELETE RECORDS:");
        System.out.println("    delete expense /in <index>");
        System.out.println("    delete income /in <index>");
        System.out.println();
        System.out.println("    SHOW BALANCE:");
        System.out.println("    balance");
        System.out.println();
        System.out.println("    SET CURRENCY TO BE DISPLAYED:");
        System.out.println("    set currency /cr <currency>");
        System.out.println("    Currencies available: HKD, PHP, IDR, MYR, VND");
        System.out.println();
        System.out.println("    UNSET CURRENCY TO BE DISPLAYED:");
        System.out.println("    unset currency /cr <currency>");
        System.out.println();
        System.out.println("    ADD TARGET:");
        System.out.println("    add target /v <value>");
        System.out.println();
        System.out.println("    SHOW TARGET:");
        System.out.println("    show target");
        System.out.println();
        System.out.println("    FIND AN INCOME/EXPENSE:");
        System.out.println("    find /k <keyword> /c <record type>");
        System.out.println("    eg: find /k food /c expense");
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

    public void showTargetAdded() {

        System.out.println("    Target added");
    }

    public void showListCleared() {
        System.out.println("    List(s) cleared");
    }

    public void showExpenseList(ExpenseList expenses, Selector selector, Converter converter) {
        if(expenses.size() == 0) {
            showEmptyListMessage();
        } else {
            System.out.println("    Here are the expenses in your list:");
            expenses.printExpenseList(selector, converter);
        }
    }

    public void showIncomeList(IncomeList income, Selector selector, Converter converter) {
        if(income.size() == 0) {
            showEmptyListMessage();
        } else {
            System.out.println("    Here are the incomes in your list:");
            income.printIncomeList(selector, converter);
        }
    }

    public void showEmptyListMessage() {
        System.out.println("    The list is empty. Start adding!");
    }
}
