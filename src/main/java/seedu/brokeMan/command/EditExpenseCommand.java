package seedu.brokeMan.command;

import seedu.brokeMan.entry.Expenses;
import seedu.brokeMan.entry.IncomeList;

import java.time.LocalDateTime;

public class EditExpenseCommand extends Command {
    public static final String COMMAND_WORD = "editExpense";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": edit the expense from the list.\n" +
            "|  Parameter: i/ <index> t/ <type> n/ <newEntry>\n" +
            "|  There are 3 type that can be changed, cost, info, time\n" +
            "|  Example: " + COMMAND_WORD + " i/ 1 t/ cost n/ 5";
    private int index;
    private String type;
    private double newCost;
    private String newEntry;
    private LocalDateTime newTime;

    public EditExpenseCommand(int index, String type, String newEntry) {
        this.index = index;
        this.type = type;
        this.newEntry = newEntry;
    }

    public EditExpenseCommand(int index, String type, double newCost) {
        this.index = index;
        this.type = type;
        this.newCost = newCost;
    }

    public EditExpenseCommand(int index, String type, LocalDateTime newTime) {
        this.index = index;
        this.type = type;
        this.newCost = newCost;
    }

    public void execute() {
        switch (type) {
        case "cost": IncomeList.editIncome(index, newCost);
        case "info": IncomeList.editIncome(index, newEntry);
        case "time": IncomeList.editIncome(index, newTime);
        }
    }
}
