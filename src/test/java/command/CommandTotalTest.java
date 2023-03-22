package command;

import data.Currency;
import data.ExpenseList;
import org.junit.jupiter.api.Test;
import parser.Parser;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CommandTotalTest {
    public ExpenseList expenseList = new ExpenseList();
    public Currency currency = new Currency();
    public Parser parser = new Parser();
    @Test
    void listTotal_successful() {
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 t/02-02-2012 cat/food"),
                currency).execute();
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 t/02-02-2012 cat/food"),
                currency).execute();
        CommandTotal commandTotal = new CommandTotal(expenseList.getExpenseList());
        commandTotal.execute();
        assertEquals(commandTotal.getTotal(), new BigDecimal(5.00).setScale(2, RoundingMode.HALF_UP));
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 t/02-02-2012 cat/food cur/USD"), currency).execute();
        System.out.println(expenseList.getExpenseList().get(2).getRate());
        commandTotal.execute();
        assertEquals(commandTotal.getTotal(), new BigDecimal(8.14).setScale(2, RoundingMode.HALF_UP));
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 t/02-02-2012 cat/food cur/HKD"), currency).execute();
        System.out.println(expenseList.getExpenseList().get(3).getRate());
        commandTotal.execute();
        assertEquals(commandTotal.getTotal(), new BigDecimal(8.55).setScale(2, RoundingMode.HALF_UP));


    }
}
