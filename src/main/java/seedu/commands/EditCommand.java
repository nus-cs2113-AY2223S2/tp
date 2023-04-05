package seedu.commands;

import seedu.expenditure.Expenditure;
import seedu.expenditure.ExpenditureList;
import seedu.expenditure.LendExpenditure;
import seedu.parser.ParseIndividualValue;
import seedu.exceptions.EmptyStringException;
import seedu.expenditure.BorrowExpenditure;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EditCommand extends Command {
    // Edit file accordingly
    public static final String COMMAND_WORD = "edit";
    public static final String LEND_EXPENDITURE_TYPE = "L";
    public static final String BORROW_EXPENDITURE_TYPE = "B";

    public static final String BLANK = "";
    public static final String DSLASH = "d/";
    public static final String ASLASH = "a/";
    public static final String SSLASH = "s/";
    public static final String BSLASH = "b/";
    public static final String NSLASH = "n/";
    public final int index;
    public final String userInput;

    public EditCommand(int index, String userInput) {
        this.index = index;
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        try {
            Expenditure editedExpenditure = expenditures.getExpenditure(index);
            String dateVal = ParseIndividualValue.parseIndividualValue(userInput, DSLASH, NSLASH);
            String amountVal = ParseIndividualValue.parseIndividualValue(userInput, ASLASH, BSLASH);
            String descriptionVal = ParseIndividualValue.parseIndividualValue(userInput, SSLASH, BLANK);
            LocalDate date = LocalDate.parse(dateVal);

            editedExpenditure.setDescriptionValueDate(descriptionVal, Double.parseDouble(amountVal), date);
            String name = null;
            LocalDate deadline = null;
            
            if (editedExpenditure.getExpenditureType() == LEND_EXPENDITURE_TYPE
                    || editedExpenditure.getExpenditureType() == BORROW_EXPENDITURE_TYPE) {
                name = ParseIndividualValue.parseIndividualValue(userInput, NSLASH, ASLASH);
                deadline = LocalDate.parse(ParseIndividualValue.parseIndividualValue(userInput, BSLASH, SSLASH));
            }
            if (editedExpenditure instanceof LendExpenditure) {
                ((LendExpenditure) editedExpenditure).setLenderNameAndDeadline(name, deadline);
            } else if (editedExpenditure instanceof BorrowExpenditure) {
                ((BorrowExpenditure) editedExpenditure).setBorrowerNameAndDeadline(name, deadline);
            }
            return new CommandResult(String.format("Edited! Here is the updated list:\n%s", expenditures));
        } catch (IndexOutOfBoundsException | EmptyStringException | DateTimeParseException e) {
            return new CommandResult("Failed to edit! Please check the format and try again!");
        }
    }
}
