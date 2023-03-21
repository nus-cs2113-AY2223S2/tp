package seedu.commands;

import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import java.time.LocalDate;

public class AcademicExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "academic";
    private final String academicExpenditureDescription;
    private final double academicExpenditureValue;
    private final LocalDate academicExpenditureDate;

    public AcademicExpenditureCommand(String description, double value, LocalDate date) {
        this.academicExpenditureDescription = description;
        this.academicExpenditureValue = value;
        this.academicExpenditureDate = date;
    }

    public CommandResult execute(ExpenditureList expenditures) {
        AcademicExpenditure academicExpenditure = new AcademicExpenditure(
                academicExpenditureDescription,
                academicExpenditureValue,
                academicExpenditureDate);
        expenditures.addExpenditure(academicExpenditure);
        return new CommandResult(String.format("Added %s expenditure: %s",
                COMMAND_WORD, academicExpenditure.toString()));
    }
}
