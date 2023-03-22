package chching;

import chching.parser.Parser;
import chching.command.Command;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.IncomeList;

public class ChChing {
    private Storage storage;
    private IncomeList incomes;
    private ExpenseList expenses;
    private Selector selector;
    private Converter converter;
    private Ui ui;

    /**
     * Loads memory of the program
     */
    public ChChing(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.incomes = new IncomeList(storage.loadIncomes());
            this.expenses = new ExpenseList(storage.loadExpenses());
            this.selector = new Selector();
            this.converter = new Converter();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            this.incomes = new IncomeList();
            this.expenses = new ExpenseList();
        }
    }

    /**
     * Runs the program
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand, incomes, expenses, ui);
                c.execute(incomes, expenses, ui, storage, selector, converter);
                isExit = c.isExit();
            } catch (ChChingException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        // assert false : "Assertions should be enabled";
        new ChChing("data/chching.txt").run();
    }
}
