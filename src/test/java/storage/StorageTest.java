package storage;

import command.CommandAdd;
import data.Currency;
import data.ExpenseList;
import org.junit.jupiter.api.Test;
import parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StorageTest {
    public Parser parser = new Parser();
    public ExpenseList expenseList  = new ExpenseList();

    public ExpenseList testExpenseList = new ExpenseList();
    public Currency currency = new Currency();

    @Test
    void saveAndReadTest_successful() {

        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/2.5 " + "t/02-02-2012 cat/food"), currency).execute();
        new CommandAdd(expenseList.getExpenseList(),
                parser.extractAddParameters("add amt/500 " + "t/01-03-2020 cat/travel"), currency).execute();
        Storage storage = new Storage(expenseList);
        storage.saveExpenseList();
        testExpenseList = storage.initialiseExpenseList();
        assertEquals(expenseList.getExpenseList(), testExpenseList.getExpenseList());

        testExpenseList.clear();
        expenseList.clear();
        storage.clearContent();

    }
}
