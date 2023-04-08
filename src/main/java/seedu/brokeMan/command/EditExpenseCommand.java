package seedu.brokeMan.command;

import seedu.brokeMan.entry.expense.ExpenseList;
import seedu.brokeMan.exception.CategoryNotCorrectException;
import seedu.brokeMan.parser.StringToCategory;
import seedu.brokeMan.parser.StringToTime;
import seedu.brokeMan.entry.Category;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class EditExpenseCommand extends Command {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static final String COMMAND_WORD = "editExpense";
    private static final Logger logger = Logger.getLogger("EditExpenseCommandLogger");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": edits the expense from the list.\n" +
            "|  Parameter: i/ <index> t/ <type> n/ <newEntry>\n" +
            "|  There are 4 types that can be changed, amount, info, time, category\n" +
            "|  Example: " + COMMAND_WORD + " i/ 1 t/ amount n/ 5";
    private final int index;
    private final String type;
    private final String newEntry;

    public EditExpenseCommand(int index, String type, String newEntry) {
        this.index = index;
        this.type = type;
        this.newEntry = newEntry;
    }

    public void execute() {
        if (type.equals("amount")) {
            double newCost = Double.parseDouble(df.format(newEntry));
            ExpenseList.editExpense(index, newCost);
        } else if (type.equals("info")) {
            ExpenseList.editExpense(index, newEntry);
        } else if (type.equals("time")) {
            LocalDateTime newTime = StringToTime.convertStringToTime(newEntry);
            ExpenseList.editExpense(index, newTime);
        } else if (type.equals("category")) {
            Category newCategory;
            try {
                newCategory = StringToCategory.convertStringToCategory(newEntry);
            } catch (CategoryNotCorrectException e) {
                throw new RuntimeException(e);
            }
            ExpenseList.editExpense(index, newCategory);
        }

    }
}
