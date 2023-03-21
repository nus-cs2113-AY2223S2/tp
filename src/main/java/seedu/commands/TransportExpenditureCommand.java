package seedu.commands;

import seedu.expenditure.TransportExpenditure;
import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

public class TransportExpenditureCommand extends Command{
    public static final String COMMAND_WORD = "transport";
    private final String transportExpenditureDescription;
    private final double transportExpenditureValue;
    private final LocalDate transportExpenditureDate;

    public TransportExpenditureCommand(String description, double value, LocalDate date) {
        this.transportExpenditureDescription = description;
        this.transportExpenditureValue = value;
        this.transportExpenditureDate = date;
    }

    public CommandResult execute(ExpenditureList expenditures) {
        TransportExpenditure transportExpenditure = new TransportExpenditure(
                transportExpenditureDescription,
                transportExpenditureValue,
                transportExpenditureDate);
        expenditures.addExpenditure(transportExpenditure);
        return new CommandResult(String.format("Added %s expenditure: %s",
                COMMAND_WORD, transportExpenditure.toString()));
    }
}
