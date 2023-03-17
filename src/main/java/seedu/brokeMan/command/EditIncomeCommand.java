package seedu.brokeMan.command;

import seedu.brokeMan.entry.IncomeList;
import seedu.brokeMan.ui.Ui;

import java.time.LocalDateTime;

import static seedu.brokeMan.common.Messages.MESSAGE_INVALID_EDIT_COMMAND;

public class EditIncomeCommand extends Command {
    public static final String COMMAND_WORD = "editIncome";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": edit the income from the list.\n" +
            "|  Parameter: i/ <index> t/ <type> n/ <newEntry>\n" +
            "|  There are 3 type that can be changed, income, info, time\n" +
            "|  Example: " + COMMAND_WORD + " i/ 1 t/ info n/ stocks";
    private int index;
    private String type;
    private String newInfo;
    private double newIncome;
    private LocalDateTime newTime;

    public EditIncomeCommand(int index, String type, String newInfo) {
        this.index = index;
        this.type = type;
        this.newInfo = newInfo;
    }

    public EditIncomeCommand(int index, String type, double newIncome) {
        this.index = index;
        this.type = type;
        this.newIncome = newIncome;
    }

    public EditIncomeCommand(int index, String type, LocalDateTime newIncome) {
        this.index = index;
        this.type = type;
        this.newTime = newIncome;
    }

    public void execute() {
        switch (type) {
        case "cost": IncomeList.editIncome(index, newIncome);
        case "info": IncomeList.editIncome(index, newInfo);
        case "time": IncomeList.editIncome(index, newTime);
        default:
            Ui.showToUserWithLineBreak(MESSAGE_INVALID_EDIT_COMMAND);

        }
    }
}
