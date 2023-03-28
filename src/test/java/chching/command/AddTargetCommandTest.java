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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Junit Test for AddTargetCommand
 */
public class AddTargetCommandTest {

    private Ui ui;
    private Storage storage;
    private Selector selector;
    private Converter converter;
    private TargetStorage targetStorage;
    private IncomeList defaultIncomeList;
    private ExpenseList defaultExpenseList;
    private Target defaultTarget;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        converter = new Converter();
        targetStorage = new TargetStorage();
        selector = new Selector();
        defaultTarget = new Target(350);
        ArrayList<Expense> expenseList = new ArrayList<>();
        ArrayList<Income> incomeList = new ArrayList<>();
    }

    /**
     * Junit Test when adding target value
     */
    @Test
    public void execute_addTargetCommand_success() {
        try {
            Command command = new AddTargetCommand(defaultTarget);
            command.execute(defaultIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage);
            assertTrue(targetStorage.getTarget().getValue() == (double) 350);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
