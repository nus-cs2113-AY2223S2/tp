package seedu.parser;

import seedu.commands.Command;
import seedu.commands.EditCommand;
import seedu.commands.HelpCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.ExitCommand;
import seedu.commands.FindCommand;
import seedu.commands.ListExpenditureCommand;
import seedu.commands.ViewDateExpenditureCommand;
import seedu.commands.ViewTypeExpenditureCommand;
import seedu.commands.ShowRatesCommand;
import seedu.commands.AcademicExpenditureCommand;
import seedu.commands.AccommodationExpenditureCommand;
import seedu.commands.EntertainmentExpenditureCommand;
import seedu.commands.FoodExpenditureCommand;
import seedu.commands.OtherExpenditureCommand;
import seedu.commands.TransportExpenditureCommand;
import seedu.commands.TuitionExpenditureCommand;
import seedu.commands.LendExpenditureCommand;
import seedu.commands.BorrowExpenditureCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.UnmarkCommand;
import seedu.commands.MarkCommand;
import seedu.commands.SortCommand;
import seedu.exceptions.EmptyStringException;
import seedu.exceptions.InvalidDateException;
import seedu.exceptions.NotPositiveValueException;
import seedu.exceptions.WrongInputException;
import seedu.commands.DuplicateCommand;
import seedu.commands.SetBudgetCommand;
import seedu.commands.CheckBudgetCommand;

import java.time.format.DateTimeParseException;

import static seedu.ui.ErrorMessages.ERROR_COMMAND_NOT_RECOGNISED_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_LACK_OF_PARAMETERS_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_DATE_TIME_ERROR_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_NOT_POSITIVE_VALUE_MESSAGE;

public class MainInputParser {
    public static final int LIMIT = 2;
    public static final int INDEX_COMMAND = 0;
    public static final int INDEX_USERSTRING = 1;

    public static Command parseInputs(String userInput) {
        String[] splitValues = userInput.split(" ", LIMIT);
        String command = splitValues[INDEX_COMMAND];
        try {
            return filterCategories(command, splitValues);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand(ERROR_LACK_OF_PARAMETERS_MESSAGE.toString());
        } catch (DateTimeParseException d) {
            return new InvalidCommand(ERROR_DATE_TIME_ERROR_MESSAGE.toString());
        } catch (WrongInputException e) {
            return new InvalidCommand(ERROR_INVALID_INPUT_MESSAGE.toString());
        } catch (NotPositiveValueException p) {
            return new InvalidCommand(ERROR_NOT_POSITIVE_VALUE_MESSAGE.toString());
        } catch (InvalidDateException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    public static Command filterCategories(String command, String[] splitValues) throws IndexOutOfBoundsException,
            DateTimeParseException, WrongInputException, NotPositiveValueException, InvalidDateException {
        switch (command) {

        // Commands that insert new inputs
        case AcademicExpenditureCommand.COMMAND_WORD:
        case AccommodationExpenditureCommand.COMMAND_WORD:
        case EntertainmentExpenditureCommand.COMMAND_WORD:
        case FoodExpenditureCommand.COMMAND_WORD:
        case OtherExpenditureCommand.COMMAND_WORD:
        case TransportExpenditureCommand.COMMAND_WORD:
        case TuitionExpenditureCommand.COMMAND_WORD:
            ParseAdd prepareAddExpenditure;
            prepareAddExpenditure = new ParseAdd(splitValues[INDEX_USERSTRING]);
            return prepareAddExpenditure.addItem(command);
        case LendExpenditureCommand.COMMAND_WORD:
        case BorrowExpenditureCommand.COMMAND_WORD:
            ParseLendBorrow prepareLendBorrowExpenditure;
            prepareLendBorrowExpenditure = new ParseLendBorrow(splitValues[INDEX_USERSTRING]);
            return prepareLendBorrowExpenditure.addItem(command);
        case DuplicateCommand.COMMAND_WORD:
            ParseDuplicate prepareDuplicate = new ParseDuplicate(splitValues[INDEX_USERSTRING]);
            return prepareDuplicate.duplicateItem();

        // Commands that support basic functionalities of MyLedger
        case ListExpenditureCommand.COMMAND_WORD:
            return new ListExpenditureCommand(splitValues[INDEX_USERSTRING]);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ShowRatesCommand.COMMAND_WORD:
            return new ShowRatesCommand();

        // Commands that mark and unmark inputs in list
        case MarkCommand.COMMAND_WORD:
            ParseMark prepareMark;
            prepareMark = new ParseMark(splitValues[INDEX_USERSTRING]);
            return prepareMark.markExpenditure();
        case UnmarkCommand.COMMAND_WORD:
            ParseMark prepareUnmark;
            prepareUnmark = new ParseMark(splitValues[INDEX_USERSTRING]);
            return prepareUnmark.unmarkExpenditure();

        // Commands that changes existing inputs in list
        case DeleteCommand.COMMAND_WORD:
            ParseDelete prepareDelete;
            prepareDelete = new ParseDelete(splitValues[INDEX_USERSTRING]);
            return prepareDelete.deleteItem();
        case EditCommand.COMMAND_WORD:
            ParseEdit prepareEdit = new ParseEdit(splitValues[INDEX_USERSTRING]);
            return prepareEdit.editItem();

        // Commands that allows users to view list and filtered list according to preference
        case SortCommand.COMMAND_WORD:
            ParseSort prepareSort;
            prepareSort = new ParseSort(splitValues[INDEX_USERSTRING]);
            return prepareSort.sortExpenditures();
        case ViewDateExpenditureCommand.COMMAND_WORD:
            return new ViewDateExpenditureCommand(splitValues[INDEX_USERSTRING]);
        case ViewTypeExpenditureCommand.COMMAND_WORD:
            return new ViewTypeExpenditureCommand(splitValues[INDEX_USERSTRING]);
        case FindCommand.COMMAND_WORD:
            ParseFind prepareFind;
            prepareFind = new ParseFind(splitValues[INDEX_USERSTRING]);
            return prepareFind.findExpenditure();


        // Commands that allows users to compare budget with expenditures
        case SetBudgetCommand.COMMAND_WORD:
            ParseSetBudget prepareBudget = new ParseSetBudget(splitValues[INDEX_USERSTRING]);
            return prepareBudget.setBudget();
        case CheckBudgetCommand.COMMAND_WORD:
            if (splitValues.length == 1 || splitValues[1].isEmpty() || splitValues[1].isBlank()) {
                return new CheckBudgetCommand("c/");
            }
            return new CheckBudgetCommand(splitValues[INDEX_USERSTRING]);

        // Commands that do not fulfill requirements above
        default:
            return new InvalidCommand(ERROR_COMMAND_NOT_RECOGNISED_MESSAGE.toString());
        }

    }
}
