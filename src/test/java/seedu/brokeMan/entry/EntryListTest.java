package seedu.brokeMan.entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.brokeMan.entry.expense.Expense;
import seedu.brokeMan.entry.income.Income;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.brokeMan.entry.EntryList.addEntry;
import static seedu.brokeMan.entry.EntryList.deleteEntry;
import static seedu.brokeMan.entry.EntryList.getEntryListSum;
import static seedu.brokeMan.parser.StringToTime.convertStringToTime;
import static seedu.brokeMan.parser.StringToCategory.convertStringToCategory;


public class EntryListTest {

    Entry entry1;
    Entry entry2;
    Entry entry3;
    Entry entry4;
    Entry entry5;
    Entry entry6;
    LinkedList<Entry> expenseList;
    LinkedList<Entry> incomeList;

    @BeforeEach
    public void setUp() throws Exception {
        entry1 = new Expense(4, "lunch", convertStringToTime("2022 09 08 12 14"),
                convertStringToCategory("FOOD"));
        entry2 = new Expense(6, "dinner",  convertStringToTime("2023 12 12 20 01"),
                convertStringToCategory("FOOD"));
        entry3 = new Expense(5, "book", convertStringToTime("2023 09 08 20 14"),
                convertStringToCategory("ENTERTAINMENT"));
        expenseList = new LinkedList<Entry>(Arrays.asList(entry1, entry2));
        entry4 = new Income(5, "Stock", convertStringToTime("2023 03 29 10 11"),
                convertStringToCategory("INVESTMENT"));
        entry5 = new Income(10, "TA", convertStringToTime("2023 03 10 12 15"),
                convertStringToCategory("SALARY"));
        entry5 = new Income(20, "Allowance", convertStringToTime("2023 03 31 20 50"),
                convertStringToCategory("OTHERS"));
        incomeList = new LinkedList<Entry>(Arrays.asList(entry4, entry5));
    }

    @Test
    public void addEntryExpense_validExpense_success() {
        addEntry(entry3, expenseList);
        assertEquals(expenseList, new LinkedList<>(Arrays.asList(entry1, entry2, entry3)));
    }
    @Test
    public void deleteEntryExpense_inBoundIndex_success() {
        deleteEntry(2, expenseList);
        assertEquals(expenseList, new LinkedList<>(Arrays.asList(entry1)));
    }

    @Test
    public void deleteEntryExpense_outBoundIndex_exceptionThrown() {
        try {
            deleteEntry(3, expenseList);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            assertThrows(IndexOutOfBoundsException.class, () -> deleteEntry(3,expenseList));
        }

    }

    @Test
    public void getTotalAmountExpense_inputList_success() {
        assertEquals(10, getEntryListSum(expenseList));
    }

    @Test
    public void addEntryIncome_validIncome_success() {
        addEntry(entry6, incomeList);
        assertEquals(incomeList, new LinkedList<>(Arrays.asList(entry4, entry5, entry6)));
    }

    @Test
    public void deleteEntryIncome_inBoundIndex_success() {
        deleteEntry(1, incomeList);
        assertEquals(incomeList, new LinkedList<>(Arrays.asList(entry5)));
    }

    @Test
    public void deleteEntryIncome_outBoundIndex_exceptionThrown() {
        try {
            deleteEntry(0, expenseList);
        } catch (IndexOutOfBoundsException e) {
            assertThrows(IndexOutOfBoundsException.class, () -> deleteEntry(4, incomeList));
        }
    }

    @Test
    public void getTotalAmountIncome_inputList_success() {
        assertEquals(25, getEntryListSum(incomeList));
    }
}
