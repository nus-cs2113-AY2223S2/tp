package seedu.commands;

import seedu.exceptions.ExceptionChecker;
import seedu.exceptions.InvalidCharacterInAmount;
import seedu.exceptions.NotPositiveValueException;
import seedu.exceptions.SmallAmountException;
import seedu.exceptions.WrongPrecisionException;
import seedu.expenditure.Expenditure;
import seedu.expenditure.AccommodationExpenditure;
import seedu.expenditure.TuitionExpenditure;
import seedu.expenditure.BorrowExpenditure;
import seedu.expenditure.LendExpenditure;
import seedu.expenditure.ExpenditureList;
import seedu.parser.ParseIndividualValue;
import seedu.exceptions.DateLimitException;
import seedu.exceptions.EmptyStringException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import static seedu.ui.ErrorMessages.ERROR_INVALID_AMOUNT_PRECISION;
import static seedu.ui.ErrorMessages.ERROR_NOT_POSITIVE_VALUE_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_LACK_OF_PARAMETERS_MESSAGE;

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
            boolean isLendOrBorrowExpenditure = filterLendAndBorrow(editedExpenditure);
            editExpenditure(editedExpenditure, isLendOrBorrowExpenditure);
            handleLumpSumExpenditure(editedExpenditure, isLendOrBorrowExpenditure);
            handleLendBorrowExpenditure(editedExpenditure);
            return new CommandResult(String.format("Edited! Here is the updated list:\n" + expenditures.toString()));
        } catch (IndexOutOfBoundsException | EmptyStringException | DateTimeParseException | NumberFormatException s) {
            return new CommandResult(ERROR_LACK_OF_PARAMETERS_MESSAGE.toString());
        } catch (WrongPrecisionException e) {
            return new CommandResult(ERROR_INVALID_AMOUNT_PRECISION.toString());
        }  catch (NotPositiveValueException p) {
            return new CommandResult(ERROR_NOT_POSITIVE_VALUE_MESSAGE.toString());
        }   catch (SmallAmountException | InvalidCharacterInAmount e) {
            return new CommandResult(e.getMessage());
        } catch (DateLimitException l) {
            return new CommandResult(l.getMessage());
        }
    }

    /**
     * @author TzeLoong
     */
    public void editExpenditure(Expenditure editedExpenditure, boolean isLendOrBorrowExpenditure)
            throws EmptyStringException, WrongPrecisionException, SmallAmountException,
            InvalidCharacterInAmount, NotPositiveValueException {
        LocalDate date = fetchDate(isLendOrBorrowExpenditure);
        double amount = fetchAmount(isLendOrBorrowExpenditure);
        String descriptionVal = fetchDescription();
        editedExpenditure.setDescriptionValueDate(descriptionVal, amount, date);
    }

    /**
     * @author Leo Zheng Rui Darren
     */
    public void handleLumpSumExpenditure(Expenditure editedExpenditure, boolean isLendOrBorrowExpenditure)
            throws EmptyStringException {
        if (editedExpenditure instanceof AccommodationExpenditure) {
            LocalDate firstDate = fetchDate(isLendOrBorrowExpenditure);
            ((AccommodationExpenditure) editedExpenditure).setRepeatDate(firstDate);
        } else if (editedExpenditure instanceof TuitionExpenditure) {
            LocalDate firstDate = fetchDate(isLendOrBorrowExpenditure);
            ((TuitionExpenditure) editedExpenditure).setRepeatDate(firstDate);
        }
    }

    /**
     * @author TzeLoong
     */
    public void handleLendBorrowExpenditure(Expenditure editedExpenditure) throws EmptyStringException {
        if (editedExpenditure instanceof LendExpenditure) {
            String name = fetchName();
            LocalDate deadline = fetchDeadline();
            ((LendExpenditure) editedExpenditure).setLenderNameAndDeadline(name, deadline);

        } else if (editedExpenditure instanceof BorrowExpenditure) {
            String name = fetchName();
            LocalDate deadline = fetchDeadline();
            ((BorrowExpenditure) editedExpenditure).setBorrowerNameAndDeadline(name, deadline);
        }
    }

    public boolean filterLendAndBorrow(Expenditure expenditure) {
        return Objects.equals(expenditure.getExpenditureType(), LEND_EXPENDITURE_TYPE)
                || Objects.equals(expenditure.getExpenditureType(), BORROW_EXPENDITURE_TYPE);
    }

    public LocalDate fetchDate(boolean isLendOrBorrowExpenditure)
            throws EmptyStringException, StringIndexOutOfBoundsException,
            DateTimeParseException, DateLimitException {
        String dateVal = ParseIndividualValue.parseIndividualValue(userInput, DSLASH,
                isLendOrBorrowExpenditure ? NSLASH : ASLASH);
        LocalDate date = LocalDate.parse(dateVal);
        ExceptionChecker.checkDateLimit(date);
        return date;
    }

    public double fetchAmount(boolean isLendOrBorrowExpenditure)
            throws StringIndexOutOfBoundsException, EmptyStringException, 
            WrongPrecisionException, InvalidCharacterInAmount, NumberFormatException, NotPositiveValueException, 
            SmallAmountException {
        String amountVal = ParseIndividualValue.parseIndividualValue(userInput, ASLASH,
                isLendOrBorrowExpenditure ? BSLASH : PSLASH);
        ExceptionChecker.checkIfMoreThanTwoDecimalPlaces(amountVal, DOT, BLANK);
        ExceptionChecker.checkValidDoubleInput(amountVal);
        ExceptionChecker.checkValidAmount(Double.parseDouble(amountVal));
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
