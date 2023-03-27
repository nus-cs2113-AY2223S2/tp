package seedu.commands;

import seedu.expenditure.Expenditure;
import seedu.expenditure.ExpenditureList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        int numberOfMatchingTasks = 0;
        for (int i = 0; i < expenditures.getSize(); i++) {
            Expenditure currentExpenditure = expenditures.getExpenditure(i);
            if (currentExpenditure.getDescription().contains(keyword)) {
                numberOfMatchingTasks++;
                System.out.println(currentExpenditure.toString());
            }
        }

        if (numberOfMatchingTasks > 0) {
            return new CommandResult(
                    "Total of " + numberOfMatchingTasks + " records were found");
        }

        return new CommandResult(
                "There are no matching records in this list");

    }

}
