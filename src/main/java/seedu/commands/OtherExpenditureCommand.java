package seedu.commands;

import seedu.expenditure.ExpenditureList;
import seedu.expenditure.OtherExpenditure;

import java.time.LocalDate;

public class OtherExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "other";
    private final String otherExpenditureDescription;
    private final double otherExpenditureValue;
    private final LocalDate otherExpenditureDate;

    public OtherExpenditureCommand(String description, double value, LocalDate date) {
        this.otherExpenditureDescription = description;
        this.otherExpenditureValue = value;
        this.otherExpenditureDate = date;
    }

    public CommandResult execute(ExpenditureList expenditures) {
        OtherExpenditure otherExpenditure = new OtherExpenditure(otherExpenditureDescription,
                otherExpenditureValue, otherExpenditureDate);
        expenditures.addExpenditure(otherExpenditure);
        return new CommandResult(String.format("Added %s expenditure: %s", COMMAND_WORD, otherExpenditure.toString()));
    }
}
