package command;

import data.ExpenseList;
import org.junit.jupiter.api.Test;
import parser.Parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class CommandSortTest {
    public ExpenseList expenseList = new ExpenseList();
    public Parser parser = new Parser();

    @Test
    public void sortExpense_successful() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " + "t/02-02-2012 cat/food")).execute();
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " +
                        "t/02-02-2012 cur/USD cat/food")).execute();
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " +
                        "t/02-02-2013 cur/USD cat/eat")).execute();
        String input = "C";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);



    }




}
