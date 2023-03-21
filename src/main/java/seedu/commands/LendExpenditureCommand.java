package seedu.commands;

import seedu.expenditure.LendExpenditure;
import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

public class LendExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "lend";
    private final String lendExpenditureDescription;
    private final String lendExpenditureName;
    private final double lendExpenditureValue;
    private final LocalDate lendExpenditureDate;
    private final LocalDate lendDeadline;

    public LendExpenditureCommand(String description, String borrowerName, double value, LocalDate date,
                                  LocalDate borrowDeadline) {
        this.lendExpenditureDescription = description;
        this.lendExpenditureName = borrowerName;
        this.lendExpenditureValue = value;
        this.lendExpenditureDate = date;
        this.lendDeadline = borrowDeadline;
    }

    public CommandResult execute(ExpenditureList expenditures) {
        LendExpenditure lendExpenditure = new LendExpenditure(lendExpenditureDescription,
                lendExpenditureName, lendExpenditureValue, lendExpenditureDate, lendDeadline);
        expenditures.addExpenditure(lendExpenditure);
        return new CommandResult(String.format("Added %s expenditure: %s", COMMAND_WORD, lendExpenditure.toString()));
    }
}
