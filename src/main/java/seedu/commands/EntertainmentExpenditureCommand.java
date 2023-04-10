package seedu.commands;

import seedu.expenditure.EntertainmentExpenditure;
import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

public class EntertainmentExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "entertainment";
    private final String entertainmentExpenditureDescription;
    private final double entertainmentExpenditureValue;
    private final LocalDate entertainmentExpenditureDate;

    public EntertainmentExpenditureCommand(String description, double value, LocalDate date) {
        this.entertainmentExpenditureDescription = description;
        this.entertainmentExpenditureValue = value;
        this.entertainmentExpenditureDate = date;
    }

    public CommandResult execute(ExpenditureList expenditures) {
        EntertainmentExpenditure entertainmentExpenditure = new EntertainmentExpenditure(
                entertainmentExpenditureDescription,
                entertainmentExpenditureValue,
                entertainmentExpenditureDate);
        expenditures.addExpenditure(entertainmentExpenditure);
        return new CommandResult(String.format("Added %s expenditure: %s",
                COMMAND_WORD, entertainmentExpenditure.toString()));
    }

}
