package seedu.commands;

import seedu.expenditure.BorrowExpenditure;
import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

public class BorrowExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "borrow";
    private final String borrowExpenditureDescription;
    private final String borrowExpenditureName;
    private final double borrowExpenditureValue;
    private final LocalDate borrowExpenditureDate;
    private final LocalDate borrowExpenditureDeadline;

    public BorrowExpenditureCommand(String description, String borrowerName, double value, LocalDate date,
                                    LocalDate borrowExpenditureDeadline) {
        this.borrowExpenditureDescription = description;
        this.borrowExpenditureName = borrowerName;
        this.borrowExpenditureValue = value;
        this.borrowExpenditureDate = date;
        this.borrowExpenditureDeadline = borrowExpenditureDeadline;
    }

    public CommandResult execute(ExpenditureList expenditures) {
        BorrowExpenditure borrowExpenditure = new BorrowExpenditure(borrowExpenditureDescription,
                borrowExpenditureName, borrowExpenditureValue, borrowExpenditureDate, borrowExpenditureDeadline);
        expenditures.addExpenditure(borrowExpenditure);
        return new CommandResult(String.format("Added %s expenditure: %s", COMMAND_WORD, borrowExpenditure.toString()));
    }
}
