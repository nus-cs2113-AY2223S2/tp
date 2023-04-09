package seedu.commands;

import seedu.exceptions.ExceptionChecker;
import seedu.exceptions.WrongPrecisionException;
import seedu.expenditure.Expenditure;
import seedu.expenditure.ExpenditureList;
import seedu.expenditure.LendExpenditure;
import seedu.parser.ParseIndividualValue;
import seedu.exceptions.EmptyStringException;
import seedu.expenditure.BorrowExpenditure;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.ui.ErrorMessages.ERROR_INVALID_AMOUNT_PRECISION;

public class EditCommand extends Command {
    // Edit file accordingly
    public static final String COMMAND_WORD = "edit";
    public static final String LEND_EXPENDITURE_TYPE = "L";
    public static final String BORROW_EXPENDITURE_TYPE = "B";

    public static final String BLANK = "";
    public static final String DSLASH = "d/";
    public static final String ASLASH = "a/";
    public static final String PSLASH = "p/";
    public static final String BSLASH = "b/";
    public static final String NSLASH = "n/";
    public static final String DOT = ".";
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
            boolean isLendOrBorrowExpenditure = fliterLendAndBorrow(editedExpenditure);

            LocalDate date = fetchDate(isLendOrBorrowExpenditure);
            Double amount = fetchAmount(isLendOrBorrowExpenditure);
            String descriptionVal = fetchDescription();
            editedExpenditure.setDescriptionValueDate(descriptionVal, amount, date);

            if (editedExpenditure instanceof LendExpenditure) {
                String name = fetchName();
                LocalDate deadline = fetchDeadline();
                ((LendExpenditure) editedExpenditure).setLenderNameAndDeadline(name, deadline);
            } else if (editedExpenditure instanceof BorrowExpenditure) {
                String name = fetchName();
                LocalDate deadline = fetchDeadline();
                ((BorrowExpenditure) editedExpenditure).setBorrowerNameAndDeadline(name, deadline);
            }
            return new CommandResult(String.format("Edited! Here is the updated list:\n" + expenditures.toString()));
        } catch (IndexOutOfBoundsException | EmptyStringException | DateTimeParseException | NumberFormatException s) {
            return new CommandResult("Failed to edit! Please check the format and try again!");
        } catch (WrongPrecisionException e) {
            return new CommandResult(ERROR_INVALID_AMOUNT_PRECISION.toString());
        }
    }

    public boolean fliterLendAndBorrow(Expenditure expenditure) {
        if (expenditure.getExpenditureType() == LEND_EXPENDITURE_TYPE
                || expenditure.getExpenditureType() == BORROW_EXPENDITURE_TYPE) {
            return true;
        }
        return false;
    }

    public LocalDate fetchDate(boolean isLendOrBorrowExpenditure)
            throws EmptyStringException, StringIndexOutOfBoundsException,
            DateTimeParseException {
        String dateVal = ParseIndividualValue.parseIndividualValue(userInput, DSLASH,
                isLendOrBorrowExpenditure ? NSLASH : ASLASH);
        return LocalDate.parse(dateVal);
    }

    public double fetchAmount(boolean isLendOrBorrowExpenditure)
            throws StringIndexOutOfBoundsException, EmptyStringException, WrongPrecisionException {
        String amountVal = ParseIndividualValue.parseIndividualValue(userInput, ASLASH,
                isLendOrBorrowExpenditure ? BSLASH : PSLASH);
        ExceptionChecker.checkIfMoreThanTwoDecimalPlaces(amountVal, DOT, BLANK);
        return Double.parseDouble(amountVal);
    }

    public String fetchDescription()
            throws EmptyStringException, StringIndexOutOfBoundsException {
        return ParseIndividualValue.parseIndividualValue(userInput, PSLASH, BLANK);
    }

    public String fetchName()
            throws EmptyStringException, StringIndexOutOfBoundsException {
        return ParseIndividualValue.parseIndividualValue(userInput, NSLASH, ASLASH);
    }

    public LocalDate fetchDeadline()
            throws EmptyStringException, StringIndexOutOfBoundsException,
            DateTimeParseException {
        String deadline = ParseIndividualValue.parseIndividualValue(userInput, BSLASH, PSLASH);
        return LocalDate.parse(deadline);
    }

}
