package seedu.brokeMan.command;

import seedu.brokeMan.entry.IncomeList;

public class EditIncomeCommand extends Command {
    public static final String COMMAND_WORD = "editIncome";
    private int index;
    private String type;
    private String newEntry;
    private double newIncome;
    private boolean isEditIncome = false;

    public EditIncomeCommand(int index, String type, String newEntry) {
        this.index = index;
        this.type = type;
        this.newEntry= newEntry;
    }

    public EditIncomeCommand(int index, String type, double newIncome) {
        this.index = index;
        this.type = type;
        this.newIncome = newIncome;
        isEditIncome = true;
    }

    public void execute() {
        if (isEditIncome) {
            IncomeList.editIncomeDouble(type, index, newIncome);
        } else {
            IncomeList.editIncome(type, index, newEntry);
        }
    }
}
