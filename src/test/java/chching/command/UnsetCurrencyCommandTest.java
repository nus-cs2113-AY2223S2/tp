package chching.command;


import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.TargetStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UnsetCurrencyCommandTest {
    
    private Ui ui;
    private Storage storage;
    private Selector selector;
    private Converter converter;
    private TargetStorage targetStorage;
    private IncomeList defaultIncomeList;
    private ExpenseList defaultExpenseList;
    private final String currency = "HKD";

    @BeforeEach
    void setUp() {
        ui = new Ui();
        converter = new Converter();
        targetStorage = new TargetStorage();
        selector = new Selector();
    }

    /**
     * Junit Test when unsetting currency to be displayed
     */
    @Test
    public void execute_addTargetCommand_success() {
        try {
            Command command = new SetCurrencyCommand(currency);
            command.execute(defaultIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage);
            assertEquals(selector.isSelected(currency), true);
            command = new UnsetCurrencyCommand(currency);
            command.execute(defaultIncomeList, defaultExpenseList, ui, storage, selector, converter, targetStorage);
            assertEquals(selector.isSelected(currency), false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
