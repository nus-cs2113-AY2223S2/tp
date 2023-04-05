package command;

import data.Currency;
import data.ExpenseList;
import org.junit.jupiter.api.Test;
import parser.Parser;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.MESSAGE_DIVIDER_CATEGORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandCategoryTest {
    public ExpenseList expenseList = new ExpenseList();
    public Parser parser = new Parser();

    public Currency currency = new Currency();

    @Test
    public void categoryExpense_successful() {
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

        String input = "food";
        String expected = "Here are all your expense categories: \n"
                + "eat food \n"
                + "Totally there are 2 categories.\n"
                + MESSAGE_DIVIDER_CATEGORY + "\n"
                + "Category: food\n"
                + "1.SGD2.50 date:02/02/2012\n"
                + "2.USD2.50 date:02/02/2012\n"
                + "3.USD2.50 date:02/02/2013\n"
                + MESSAGE_DIVIDER + "\n";

        new CommandCategory(expenseList.getExpenseList(), input).execute();
        String actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);

        input = "fish";
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        expected = "Sorry, none of your previous expenses corresponds to this category.\n"
                + "Here are all your expense categories: \n"
                + "eat food \n"
                + "Totally there are 2 categories.\n"
                + MESSAGE_DIVIDER + "\n";
        new CommandCategory(expenseList.getExpenseList(), input).execute();
        actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);


        // Check case sensitivity problem
        input = "FOOD";
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        expected = "Here are all your expense categories: \n"
                + "eat food \n"
                + "Totally there are 2 categories.\n"
                + MESSAGE_DIVIDER_CATEGORY + "\n"
                + "Category: food\n"
                + "1.SGD2.50 date:02/02/2012\n"
                + "2.USD2.50 date:02/02/2012\n"
                + "3.USD2.50 date:02/02/2013\n"
                + MESSAGE_DIVIDER + "\n";
        new CommandCategory(expenseList.getExpenseList(), input).execute();
        actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);

        // Uncategorized case
        input = "";
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " +
                        "t/02-02-2013 cur/SGD"), currency).execute();

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        expected = "Here are all your expense categories: \n"
                + "eat uncategorized food \n"
                + "Totally there are 3 categories.\n"
                + MESSAGE_DIVIDER_CATEGORY + "\n"
                + "Category: uncategorized\n"
                + "1.SGD2.50 date:02/02/2013\n"
                + MESSAGE_DIVIDER + "\n";
        new CommandCategory(expenseList.getExpenseList(), input).execute();
        actual = outContent.toString().replaceAll(System.lineSeparator(), "\n");
        assertEquals(expected.replaceAll(System.lineSeparator(), "\n"), actual);

        expenseList.clear();

    }




}
