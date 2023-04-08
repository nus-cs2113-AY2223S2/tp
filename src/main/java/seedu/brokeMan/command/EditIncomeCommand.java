package seedu.brokeMan.command;

import seedu.brokeMan.entry.Category;
import seedu.brokeMan.entry.income.IncomeList;
import seedu.brokeMan.exception.CategoryNotCorrectException;
import seedu.brokeMan.parser.StringToCategory;
import seedu.brokeMan.parser.StringToTime;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class EditIncomeCommand extends Command {
    public static final String COMMAND_WORD = "editIncome";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": edits the income from the list.\n" +
            "|  Parameter: i/ <index> t/ <type> n/ <newEntry>\n" +
            "|  There are 4 types that can be changed, amount, info, time, category\n" +
            "|  Example: " + COMMAND_WORD + " i/ 1 t/ info n/ stocks";
    private int index;
    private String type;
    private String newEntry;

    public EditIncomeCommand(int index, String type, String newEntry) {
        this.index = index;
        this.type = type;
        this.newEntry = newEntry;
    }

    public void execute() {
        if (type.equals("amount")) {
            double newIncome = Double.parseDouble(newEntry);
            IncomeList.editIncome(index, newIncome);
        } else if (type.equals("info")) {
            IncomeList.editIncome(index, newEntry);
        } else if (type.equals("time")) {
            LocalDateTime newTime = StringToTime.convertStringToTime(newEntry);
            IncomeList.editIncome(index, newTime);
        } else if (type.equals("category")) {
            Category newCategory = null;
            try {
                newCategory = StringToCategory.convertStringToCategory(newEntry);
            } catch (CategoryNotCorrectException e) {
                throw new RuntimeException(e);
            }
            IncomeList.editIncome(index, newCategory);
        }
    }
}
