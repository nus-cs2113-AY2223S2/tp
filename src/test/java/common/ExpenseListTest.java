package common;

import command.CommandAdd;
import command.CommandList;
import data.Currency;
import data.Expense;
import data.ExpenseList;
import org.junit.jupiter.api.Test;
import parser.Parser;

import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.MESSAGE_DIVIDER_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class ExpenseListTest {
    public ArrayList<Expense> testExpenseList = new ArrayList<>();
    public ExpenseList expenseList = new ExpenseList();
    public Parser parser = new Parser();

    @Test
    public void addExpense_successful() {
        testExpenseList.add(new Expense(2.5, "02/02/2012", "food", Currency.SGD));
        new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters("add amt/2.5 " +
                "t/02/02/2012 cat/food")).run();
        assertEquals(testExpenseList.get(0), expenseList.getExpenseList().get(0));

        testExpenseList.add(new Expense(2.5, "02/02/2012", "food", Currency.SGD));
        new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters("add amt/2.5 " +
                "t/02/02/2012 cur/USD cat/food")).run();
        assertNotEquals(testExpenseList.get(1), expenseList.getExpenseList().get(1));

        testExpenseList.clear();
        expenseList.clear();
    }

    @Test
    public void listExpense_successful() {
        // To standardize all the line separator to \n just for doing test

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        String expected = "Sorry, there are no expenses tracked currently.\r\n"
                + MESSAGE_DIVIDER + "\r\n";
        System.setOut(new PrintStream(outContent));
        boolean checkNotEmpty = new CommandList(expenseList.getExpenseList()).run();
        String actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertFalse(checkNotEmpty);
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);

        new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters("add amt/2.5 " +
                "t/02/02/2012 cur/USD cat/food")).run();
        new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters("add amt/5.5 " +
                "t/02/02/2014 cur/SGD cat/food")).run();

        outContent = new ByteArrayOutputStream();
        expected = "Here are the tasks in your list:\r\n" + "\r\n"
                + MESSAGE_DIVIDER_LIST + "\r\n"
                + "1.USD2.5 cat:food date:02/02/2012\r\n"
                + "2.SGD5.5 cat:food date:02/02/2014\r\n"
                + "Now you have 2 expenses in the list.\r\n"
                + MESSAGE_DIVIDER + "\r\n";
        System.setOut(new PrintStream(outContent));
        checkNotEmpty = new CommandList(expenseList.getExpenseList()).run();

        actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertTrue(checkNotEmpty);
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);

        testExpenseList.clear();
        expenseList.clear();
    }

    @Test
    public void deleteExpense_successful() {
        testExpenseList.add(new Expense(2.5, "02/02/2012", "food", Currency.SGD));
        new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters("add amt/2.5 " +
                "t/02/02/2012 cat/food")).run();
        testExpenseList.remove(0);
        expenseList.deleteExpense("delete 1");
        assertIterableEquals(testExpenseList, expenseList.getExpenseList());

        testExpenseList.clear();
        expenseList.clear();
    }
}
