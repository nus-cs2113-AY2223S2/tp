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
            if (editedExpenditure.getExpenditureType() != LEND_EXPENDITURE_TYPE
                    && editedExpenditure.getExpenditureType() != BORROW_EXPENDITURE_TYPE) {
                String dateVal3 = ParseIndividualValue.parseIndividualValue(userInput, DSLASH, ASLASH);
                String amountVal3 = ParseIndividualValue.parseIndividualValue(userInput, ASLASH, SSLASH);
                String descriptionVal3 = ParseIndividualValue.parseIndividualValue(userInput, SSLASH, BLANK);
                LocalDate date3 = LocalDate.parse(dateVal3);
                editedExpenditure.setDate(date3);
                editedExpenditure.setValue(Double.parseDouble(amountVal3));
                editedExpenditure.setDescription(descriptionVal3);
                return new CommandResult(
                        String.format("Edited! Here is the updated list:\n" + expenditures.toString()));
            } else {
                String dateVal = ParseIndividualValue.parseIndividualValue(userInput, DSLASH, NSLASH);
                String amountVal = ParseIndividualValue.parseIndividualValue(userInput, ASLASH, BSLASH);
                String descriptionVal = ParseIndividualValue.parseIndividualValue(userInput, SSLASH, BLANK);
                LocalDate date = LocalDate.parse(dateVal);
                String name = ParseIndividualValue.parseIndividualValue(userInput, NSLASH, ASLASH);
                LocalDate deadline = LocalDate
                        .parse(ParseIndividualValue.parseIndividualValue(userInput, BSLASH, SSLASH));
                editedExpenditure.setDate(date);
                editedExpenditure.setValue(Double.parseDouble(amountVal));
                editedExpenditure.setDescription(descriptionVal);
                if (editedExpenditure.getExpenditureType() == LEND_EXPENDITURE_TYPE) {
                    ((LendExpenditure) editedExpenditure).setLenderName(name);
                    ((LendExpenditure) editedExpenditure).setDeadline(deadline);
                } else {
                    ((BorrowExpenditure) editedExpenditure).setBorrowerName(name);
                    ((BorrowExpenditure) editedExpenditure).setDeadline(deadline);
                }
            }
            return new CommandResult(String.format("Edited! Here is the updated list:\n" + expenditures.toString()));
        } catch (IndexOutOfBoundsException | EmptyStringException | DateTimeParseException s) {
            return new CommandResult("Failed to edit! Please check the format and try again!");
        }
    }
}
