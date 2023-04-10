package command;

import data.Currency;
import data.ExpenseList;
import org.junit.jupiter.api.Test;
import parser.Parser;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.MESSAGE_DIVIDER_SORTEDLIST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandSortTest {
    public ExpenseList expenseList = new ExpenseList();
    public Parser parser = new Parser();

    public Currency currency = new Currency();

    @Test
    public void sortExpense_successful() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " + "t/02-02-2012 cat/food"), currency).execute();
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " +
                        "t/02-02-2012 cur/USD cat/food"), currency).execute();
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " +
                        "t/02-02-2013 cur/USD cat/eat"), currency).execute();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "C";
        String expected = MESSAGE_DIVIDER_SORTEDLIST + "\n"
                + "Category: eat\n"
                + "1.USD2.50 date:02-02-2013\n"
                + "Category: food\n"
                + "1.USD2.50 date:02-02-2012\n"
                + "2.SGD2.50 date:02-02-2012\n"
                + MESSAGE_DIVIDER + "\n";

        new CommandSort(expenseList.getExpenseList(), input).execute();
        String actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);


        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        input = "D";
        expected = MESSAGE_DIVIDER_SORTEDLIST + "\n"
                + "Date: 02-02-2012\n"
                + "1.SGD2.50 cat:food\n"
                + "2.USD2.50 cat:food\n"
                + "Date: 02-02-2013\n"
                + "1.USD2.50 cat:eat\n"
                + MESSAGE_DIVIDER + "\n";
        new CommandSort(expenseList.getExpenseList(), input).execute();
        actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);

        expenseList.clear();
    }




}
