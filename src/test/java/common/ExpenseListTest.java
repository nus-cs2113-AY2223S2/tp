package common;

import command.CommandAdd;
import command.CommandList;
import data.Expense;
import data.ExpenseList;
import data.Currency;
import org.junit.jupiter.api.Test;
import parser.Parser;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.MESSAGE_DIVIDER_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ExpenseListTest {
    public ArrayList<Expense> testExpenseList = new ArrayList<>();
    public ExpenseList expenseList = new ExpenseList();
    public Parser parser = new Parser();
    public Currency currency = new Currency();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Test
    public void listExpense_successful() {
        // To standardize all the line separator to \n just for doing test
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        String expected = "Sorry, there are no expenses tracked currently.\n"
                + MESSAGE_DIVIDER + "\n";
        System.setOut(new PrintStream(outContent));
        boolean checkNotEmpty = new CommandList(expenseList.getExpenseList()).run();
        String actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertFalse(checkNotEmpty);
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);

        new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters("add amt/2.5 " +
                "t/02-02-2012 cur/USD cat/food"), currency).execute();
        new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters("add amt/5.5 " +
                "t/02-02-2014 cur/SGD cat/food"), currency).execute();

        outContent = new ByteArrayOutputStream();
        expected = "Here are the tasks in your list:\n\n"
                + MESSAGE_DIVIDER_LIST + "\n"
                + "1.USD2.50 cat:food date:02-02-2012\n"
                + "2.SGD5.50 cat:food date:02-02-2014\n"
                + "Now you have 2 expenses in the list.\n"
                + MESSAGE_DIVIDER + "\n";
        System.setOut(new PrintStream(outContent));
        checkNotEmpty = new CommandList(expenseList.getExpenseList()).run();

        actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertTrue(checkNotEmpty);
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);

        testExpenseList.clear();
        expenseList.clear();
    }

    @Test
    void expenseAmountStandardization_successful() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters("add amt/2.5658 " +
                "t/02-02-2012 cur/USD cat/food"), currency).execute();
        new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters("add amt/5 " +
                "t/02-02-2014 cur/SGD cat/food"), currency).execute();

        System.setOut(new PrintStream(outContent));
        new CommandList(expenseList.getExpenseList()).run();

        String expected = "Here are the tasks in your list:\n\n"
                + MESSAGE_DIVIDER_LIST + "\n"
                + "1.USD2.57 cat:food date:02-02-2012\n"
                + "2.SGD5.00 cat:food date:02-02-2014\n"
                + "Now you have 2 expenses in the list.\n"
                + MESSAGE_DIVIDER + "\n";
        String actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);

        testExpenseList.clear();
        expenseList.clear();
    }
}
