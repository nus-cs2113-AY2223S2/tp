package seedu.Parser;

import seedu.Expenditure.ExpenditureList;
import seedu.commands.Command;
import seedu.commands.EditCommand;
import seedu.commands.HelpCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.ExitCommand;
import seedu.commands.ViewExpenditureCommand;
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

public class MainInputParser {
    public static Command parseInputs(String line) {
        String [] splitValues = line.split(" ", 2);
        String command = splitValues[0];

        switch (command) {
        case ExitCommand.COMMAND_WORD:
            ExpenditureList.saveList();
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case DeleteCommand.COMMAND_WORD:
            return ParseDelete.deleteItem(line);
        case EditCommand.COMMAND_WORD:
            return ParseEdit.editItem(line);
        case ViewExpenditureCommand.COMMAND_WORD:
            return new ViewExpenditureCommand();
        case AcademicExpenditureCommand.COMMAND_WORD:
        case AccommodationExpenditureCommand.COMMAND_WORD:
        case EntertainmentExpenditureCommand.COMMAND_WORD:
        case FoodExpenditureCommand.COMMAND_WORD:
        case OtherExpenditureCommand.COMMAND_WORD:
        case TransportExpenditureCommand.COMMAND_WORD:
        case TuitionExpenditureCommand.COMMAND_WORD:
            return ParseAdd.addItem(line, command);
        case LendExpenditureCommand.COMMAND_WORD:
        case BorrowExpenditureCommand.COMMAND_WORD:
            ExpenditureList.saveList();
            return ParseLendBorrow.lendBorrowItem(line, command);
        default:
            // Commands that are not listed above
            return new InvalidCommand("Invalid");
        }
    }
}
