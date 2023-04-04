package command;

import data.Currency;
import data.ExpenseList;
import org.junit.jupiter.api.Test;
import parser.Parser;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.MESSAGE_DIVIDER_FIND;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandFindTest {
    public ExpenseList expenseList = new ExpenseList();
    public Parser parser = new Parser();

    public Currency currency = new Currency();

    @Test
    public void findExpense_successful() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " + "t/02-02-2012 cat/food"), currency).execute();
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " +
                        "t/02-02-2012 cur/USD cat/food"), currency).execute();
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " +
                        "t/02-02-2013 cur/USD cat/eat"), currency).execute();
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " +
                        "t/02-02-2013 cur/USD cat/food"), currency).execute();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "fo";
        String expected = MESSAGE_DIVIDER_FIND + "\n"
                + "1.SGD2.50 cat:food date:02/02/2012\n"
                + "2.USD2.50 cat:food date:02/02/2012\n"
                + "3.USD2.50 cat:food date:02/02/2013\n"
                + MESSAGE_DIVIDER + "\n";

        new CommandFind(expenseList.getExpenseList(), input).execute();
        String actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);


        input = "fish";
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        expected = "Sorry, none of your previous expenses have such input.\n"
                + MESSAGE_DIVIDER + "\n";
        new CommandFind(expenseList.getExpenseList(), input).execute();
        actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);
        expenseList.clear();
    }

    @Test
    public void findExpense_specialCases() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.50 " + "t/02-02-2012 cat/food"), currency).execute();
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.50 " +
                        "t/02-02-2012 cur/USD cat/food"), currency).execute();
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.50 " +
                        "t/02-02-2013 cur/USD cat/eat"), currency).execute();
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " +
                        "t/02-02-2013 cur/USD cat/food"), currency).execute();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // With the same expenseAmount
        String input = "2.5";
        String expected = MESSAGE_DIVIDER_FIND + "\n"
                + "1.SGD2.50 cat:food date:02/02/2012\n"
                + "2.USD2.50 cat:food date:02/02/2012\n"
                + "3.USD2.50 cat:eat date:02/02/2013\n"
                + "4.USD2.50 cat:food date:02/02/2013\n"
                + MESSAGE_DIVIDER + "\n";

        new CommandFind(expenseList.getExpenseList(), input).execute();
        String actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);


        input = "2.50"; // It should be no difference compare to the previous case
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        expected = MESSAGE_DIVIDER_FIND + "\n"
                + "1.SGD2.50 cat:food date:02/02/2012\n"
                + "2.USD2.50 cat:food date:02/02/2012\n"
                + "3.USD2.50 cat:eat date:02/02/2013\n"
                + "4.USD2.50 cat:food date:02/02/2013\n"
                + MESSAGE_DIVIDER + "\n";
        new CommandFind(expenseList.getExpenseList(), input).execute();
        actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);

        input = "13"; // Check whether it can get expenses with years we want
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        expected = MESSAGE_DIVIDER_FIND + "\n"
                + "1.USD2.50 cat:eat date:02/02/2013\n"
                + "2.USD2.50 cat:food date:02/02/2013\n"
                + MESSAGE_DIVIDER + "\n";
        new CommandFind(expenseList.getExpenseList(), input).execute();
        actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);

        // Check whether it can get expenses with the specified currency
        // And won't get the expense with currency equals to SGD
        input = "USD";
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        expected = MESSAGE_DIVIDER_FIND + "\n"
                + "1.USD2.50 cat:food date:02/02/2012\n"
                + "2.USD2.50 cat:eat date:02/02/2013\n"
                + "3.USD2.50 cat:food date:02/02/2013\n"
                + MESSAGE_DIVIDER + "\n";
        new CommandFind(expenseList.getExpenseList(), input).execute();
        actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);


        // Check whether case sensitivity problem is solved
        // And won't get the expense with currency equals to SGD
        input = "uSd";
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        expected = MESSAGE_DIVIDER_FIND + "\n"
                + "1.USD2.50 cat:food date:02/02/2012\n"
                + "2.USD2.50 cat:eat date:02/02/2013\n"
                + "3.USD2.50 cat:food date:02/02/2013\n"
                + MESSAGE_DIVIDER + "\n";
        new CommandFind(expenseList.getExpenseList(), input).execute();
        actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);

        input = "FOOD";
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        expected = MESSAGE_DIVIDER_FIND + "\n"
                + "1.SGD2.50 cat:food date:02/02/2012\n"
                + "2.USD2.50 cat:food date:02/02/2012\n"
                + "3.USD2.50 cat:food date:02/02/2013\n"
                + MESSAGE_DIVIDER + "\n";
        new CommandFind(expenseList.getExpenseList(), input).execute();
        actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);


        expenseList.clear();
    }



}
