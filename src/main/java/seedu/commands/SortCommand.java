package seedu.commands;

import seedu.expenditure.ExpenditureList;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String AMOUNT_ASCENDING = "ascend";
    public static final String AMOUNT_DESCENDING = "descend";
    public static final String DATE_FROM_LATEST = "latest";
    public static final String DATE_FROM_EARLIEST = "earliest";
    private final String sortType;

    public SortCommand(String sortType) {
        this.sortType = sortType;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        ExpenditureList sortedExpenditures;
        switch (sortType) {
        case AMOUNT_ASCENDING:
            sortedExpenditures = ExpenditureList.sortAmount(sortType);
            return new CommandResult("Here is your list of expenditures sorted in ascending amount: \n"
                    + sortedExpenditures.toString());
        case AMOUNT_DESCENDING:
            sortedExpenditures = ExpenditureList.sortAmount(sortType);
            return new CommandResult("Here is your list of expenditures sorted in descending amount: \n"
                    + sortedExpenditures.toString());
        case DATE_FROM_EARLIEST:
            sortedExpenditures = ExpenditureList.sortDate(sortType);
            return new CommandResult("Here is your list of expenditures sorted from the earliest date: \n"
                    + sortedExpenditures.toString());
        case DATE_FROM_LATEST:
            sortedExpenditures = ExpenditureList.sortDate(sortType);
            return new CommandResult("Here is your list of expenditures sorted from the latest date: \n"
                    + sortedExpenditures.toString());
        default:
            return new CommandResult("<sort ascend> to sort amount in ascending order. " +
                    "<sort descend> to sort amount in descending order.\n" +
                    "<sort earliest> to sort amount from earliest date added. " +
                    "<sort latest> to sort amount from latest date added.");
        }
    }
}
