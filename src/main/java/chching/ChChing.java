package chching;

import chching.parser.Parser;
import chching.command.Command;
import chching.record.ExpenseList;
import chching.record.IncomeList;

public class ChChing {
    private Storage storage;
    private IncomeList incomes;
    private ExpenseList expenses;
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
                c.execute(incomes, expenses, ui, storage);
                isExit = c.isExit();
            } catch (ChChingException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new ChChing("data/chching.txt").run();
    }
}
