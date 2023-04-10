/* @@author avielcx */
package chching.command;

import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.Expense;
import chching.record.ExpenseList;
import chching.record.Income;
import chching.record.IncomeList;
import chching.record.Target;
import chching.record.TargetStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit Test for ShowTargetCommand
 */
public class ShowTargetCommandTest {
    private Ui ui;
    private Storage storage;
    private Selector selector;
    private Converter converter;
    private TargetStorage targetStorage;
    private IncomeList defaultIncomeList;
    private ExpenseList defaultExpenseList;
    private Target defaultTarget;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private String convertedCurrencies;
    private String expectedString;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        converter = new Converter();
        targetStorage = new TargetStorage();
        selector = new Selector();
        defaultTarget = new Target((double) 350);
        ArrayList<Expense> expenseList = new ArrayList<>();
        ArrayList<Income> incomeList = new ArrayList<>();
        targetStorage.addTarget(defaultTarget);
        System.setOut(new PrintStream(outputStreamCaptor));
        selector.setCurrency("HKD");
        convertedCurrencies = converter.printConverter(350, selector);
        expectedString = "Current target: 350.00 SGD" + convertedCurrencies;
    }


    /**
     * Junit Test to show target
     */
    @Test
    public void execute_showTargetCommand_success() {
        try {
            Command command = new ShowTargetCommand();
            command.execute(defaultIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage);
            assertEquals(expectedString, outputStreamCaptor.toString().trim());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
