package seedu.commands;

import seedu.expenditure.ExpenditureList;
import seedu.expenditure.TuitionExpenditure;

import java.time.LocalDate;

public class TuitionExpenditureCommand extends Command{
    public static final String COMMAND_WORD = "tuition";
    private final String tuitionExpenditureDescription;
    private final double tuitionExpenditureValue;
    private final LocalDate tuitionExpenditureDate;
    private final LocalDate tuitionExpenditureRepeatDate;

    public TuitionExpenditureCommand(String description, double value, LocalDate date, LocalDate repeatDate) {
        this.tuitionExpenditureDescription = description;
        this.tuitionExpenditureValue = value;
        this.tuitionExpenditureDate = date;
        this.tuitionExpenditureRepeatDate = repeatDate;
    }

    public CommandResult execute(ExpenditureList expenditures) {
        TuitionExpenditure tuitionExpenditure = new TuitionExpenditure(tuitionExpenditureDescription,
                tuitionExpenditureValue, tuitionExpenditureDate, tuitionExpenditureRepeatDate);
        expenditures.addExpenditure(tuitionExpenditure);
        return new CommandResult(String.format("Added %s expenditure: %s", COMMAND_WORD,
                tuitionExpenditure.toString()));
    }
}
