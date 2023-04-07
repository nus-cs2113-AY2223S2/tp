package chching;

import chching.parser.Parser;
import chching.api.LiveCurrencyApi;
import chching.command.Command;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Target;
import chching.record.TargetStorage;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChChing {
    private Storage storage;
    private IncomeList incomes;
    private ExpenseList expenses;
    private Selector selector;
    private Converter converter;
    private LiveCurrencyApi liveCurrencyAPI;
    private TargetStorage targetStorage;
    private Target target = new Target(0);
    private Ui ui;

    /**
     * Program Logging
     */
    private static final Logger logger = Logger.getLogger(ChChing.class.getName());

    static {
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
    }

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
            this.liveCurrencyAPI = new LiveCurrencyApi(selector, converter);
            this.targetStorage = new TargetStorage();
            this.targetStorage.addTarget(target);
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
                Command c = Parser.parse(fullCommand, ui);
                c.execute(incomes, expenses, ui, storage, selector, converter, targetStorage);
                isExit = c.isExit();
            } catch (ChChingException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new ChChing("data/chching.json").run();
    }
}
