package seedu.commands;

import seedu.expenditure.ExpenditureList;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String AMOUNT_ASCENDING = "ascend";
    public static final String AMOUNT_DESCENDING = "descend";
    private String sortType;
    private ExpenditureList sortedExpenditures;

    public SortCommand(String sortType) {
        this.sortType = sortType;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        switch (sortType) {
        case AMOUNT_ASCENDING:
            sortedExpenditures = ExpenditureList.sortAmount(sortType);
            return new CommandResult("Here is your list of expenditures sorted in ascending amount: \n"
                    + sortedExpenditures.toString());
        case AMOUNT_DESCENDING:
            sortedExpenditures = ExpenditureList.sortAmount(sortType);
            return new CommandResult("Here is your list of expenditures sorted in descending amount: \n"
                    + sortedExpenditures.toString());
        default:
            return new CommandResult("<a/> to sort amount in ascending order. " +
                    "<d/> to sort amount in descending order");
        }
    }
}
