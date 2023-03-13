package chching;

import chching.command.Command;
import chching.record.ExpenseList;
import chching.record.IncomeList;

public class ChChing {
    private Storage storage;
    private IncomeList incomes;
    private ExpenseList expenses;
    private Ui ui;

    public ChChing(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.incomes = new IncomeList(storage.load());
            this.expenses = new ExpenseList(storage.load());
        } catch (ChChingException e) {
            ui.showError(e.getMessage());
            this.incomes = new IncomeList();
            this.expenses = new ExpenseList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(incomes, expenses, ui, storage);
                isExit = c.isExit();
            } catch(ChChingException e) {
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
