package seedu.brokeMan.command;

import seedu.brokeMan.entry.IncomeList;
import seedu.brokeMan.parser.StringToTime;
import seedu.brokeMan.ui.Ui;

import java.time.LocalDateTime;

import static seedu.brokeMan.common.Messages.MESSAGE_INVALID_EDIT_COMMAND;

public class EditExpenseCommand extends Command {
    public static final String COMMAND_WORD = "editExpense";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": edit the expense from the list.\n" +
            "|  Parameter: i/ <index> t/ <type> n/ <newEntry>\n" +
            "|  There are 3 type that can be changed, cost, info, time\n" +
            "|  Example: " + COMMAND_WORD + " i/ 1 t/ cost n/ 5";
    private int index;
    private String type;
    private String newEntry;

    public EditExpenseCommand(int index, String type, String newEntry) {
        this.index = index;
        this.type = type;
        this.newEntry = newEntry;
    }

    public void execute() {
        switch (type) {
        case "cost": Double newCost = Double.parseDouble(newEntry);
            IncomeList.editIncome(index, newCost);
            break;
        case "info": IncomeList.editIncome(index, newEntry);
            break;
        case "time":
            LocalDateTime newTime = StringToTime.convertStringToTime(newEntry);
            IncomeList.editIncome(index, newTime);
            break;
        default:
            Ui.showToUserWithLineBreak(MESSAGE_INVALID_EDIT_COMMAND);

        }
    }
}
