package seedu.commands;

import seedu.expenditure.AccommodationExpenditure;
import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

public class AccommodationExpenditureCommand extends Command{
    public static final String COMMAND_WORD = "accommodation";
    private final String accommodationExpenditureDescription;
    private final double accommodationExpenditureValue;
    private final LocalDate accommodationExpenditureDate;

    public AccommodationExpenditureCommand(String description, double value, LocalDate date) {
        this.accommodationExpenditureDescription = description;
        this.accommodationExpenditureValue = value;
        this.accommodationExpenditureDate = date;
    }

    public CommandResult execute(ExpenditureList expenditures) {
        AccommodationExpenditure accommodationExpenditure = new AccommodationExpenditure(
                accommodationExpenditureDescription,
                accommodationExpenditureValue,
                accommodationExpenditureDate);
        expenditures.addExpenditure(accommodationExpenditure);
        return new CommandResult(String.format("Added %s expenditure: %s",
                COMMAND_WORD, accommodationExpenditure.toString()));
    }
}
