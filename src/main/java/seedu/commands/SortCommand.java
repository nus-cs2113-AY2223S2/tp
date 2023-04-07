package seedu.commands;

import seedu.expenditure.Expenditure;
import seedu.expenditure.ExpenditureList;

import java.util.ArrayList;

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
        ArrayList<Expenditure> sortedList;
        ExpenditureList sortedExpenditures;
        if(expenditures.getSize() == 0) {
            return new CommandResult("Unable to sort as list is currently empty!");
        }
        switch (sortType) {
        case AMOUNT_ASCENDING:
            sortedList = ExpenditureList.sortList(sortType);
            sortedExpenditures = ExpenditureList.getSortedExpenditures(sortedList);
            return new CommandResult("Here is your list of expenditures sorted in ascending amount: \n"
                    + sortedExpenditures);
        case AMOUNT_DESCENDING:
            sortedList = ExpenditureList.sortList(sortType);
            sortedExpenditures = ExpenditureList.getSortedExpenditures(sortedList);
            return new CommandResult("Here is your list of expenditures sorted in descending amount: \n"
                    + sortedExpenditures);
        case DATE_FROM_EARLIEST:
            sortedList = ExpenditureList.sortList(sortType);
            sortedExpenditures = ExpenditureList.getSortedExpenditures(sortedList);
            return new CommandResult("Here is your list of expenditures sorted from the earliest date: \n"
                    + sortedExpenditures);
        case DATE_FROM_LATEST:
            sortedList = ExpenditureList.sortList(sortType);
            sortedExpenditures = ExpenditureList.getSortedExpenditures(sortedList);
            return new CommandResult("Here is your list of expenditures sorted from the latest date: \n"
                    + sortedExpenditures);
        default:
            return new CommandResult("<sort ascend> to sort amount in ascending order. " +
                    "<sort descend> to sort amount in descending order.\n" +
                    "<sort earliest> to sort amount from earliest date added. " +
                    "<sort latest> to sort amount from latest date added.");
        }
    }
}
