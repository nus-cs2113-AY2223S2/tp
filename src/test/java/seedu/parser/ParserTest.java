package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.commands.Command;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    // Firstly, test parsing in MainInputParser
    @Test
    void parseExitCommand() {
        String inputString = "exit";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.ExitCommand");
    }

    @Test
    void parseViewListCommand() {
        String inputString = "list";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");
    }

    @Test
    void parseHelpCommand() {
        String inputString = "help";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.HelpCommand");
    }

    @Test
    void parseAcademicCommand() {
        String inputString = "academic d/2000-01-01 a/200.0 p/Tuition";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.AcademicExpenditureCommand");
    }

    @Test
    void parseAccommodationCommand() {
        String inputString = "accommodation d/2000-01-01 a/500.0 p/Rent";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.AccommodationExpenditureCommand");
    }

    @Test
    void parseEntertainmentCommand() {
        String inputString = "entertainment d/2000-01-01 a/45.90 p/Singing";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.EntertainmentExpenditureCommand");
    }

    @Test
    void parseFoodCommand() {
        String inputString = "food d/2000-01-01 a/9.90 p/Lunch";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.FoodExpenditureCommand");
    }

    @Test
    void parseOthersCommand() {
        String inputString = "other d/2000-01-01 a/2.19 p/Miscellaneous";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.OtherExpenditureCommand");
    }

    @Test
    void parseTransportCommand() {
        String inputString = "transport d/2000-01-01 a/2.00 p/MRT fair";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.TransportExpenditureCommand");
    }

    @Test
    void parseTuitionCommand() {
        String inputString = "tuition d/2000-01-01 a/30.00 p/Chinese lessons";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.TuitionExpenditureCommand");
    }

    @Test
    void parseDeleteCommand() {
        String inputString = "delete 1";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.DeleteCommand");
    }

    @Test
    void parseEditCommand() {
        String inputString = "edit 3 d/2000-01-01 a/10.90 p/Lunch";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.EditCommand");
    }

    @Test
    void parseLendCommand() {
        // Format: category d/date, n/name, a/amount, b/deadline, p/description
        String inputString = "lend d/2000-01-01 n/Bob a/100.0 b/2024-07-20 p/To buy flowers";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.LendExpenditureCommand");
    }

    @Test
    void parseBorrowCommand() {
        // Format: category d/date, n/name, a/amount, b/deadline, p/description
        String inputString = "borrow d/2000-01-01 n/Alice a/100.0 b/2024-07-20 p/For school loans";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.BorrowExpenditureCommand");
    }

    // Checking for invalid inputs
    @Test
    void invalidInputDueToCommand() {
        String inputString = "bye";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");
        inputString = "navigate";
        finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");
        inputString = "Exit";
        finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");
    }

    @Test
    void invalidInputDueToMissingParameters() {
        String inputMissingDescription = "academic d/2000-01-01 a/200.0";
        Command finalCommand = MainInputParser.parseInputs(inputMissingDescription);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");

        String inputMissingDate = "academic a/200.0 p/Tuition";
        finalCommand = MainInputParser.parseInputs(inputMissingDate);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");

        String inputMissingAmount = "academic d/2000-01-01 p/Tuition";
        finalCommand = MainInputParser.parseInputs(inputMissingAmount);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");
    }

    @Test
    void invalidInputDueToWrongInputFormat() {
        String wrongDate = "academic d/01-01-2000 a/200.0 p/Tuition";
        String wrongAmount = "academic d/2000-01-01 a/Twenty p/Tuition";
        Command finalCommand = MainInputParser.parseInputs(wrongDate);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");
        finalCommand = MainInputParser.parseInputs(wrongAmount);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");
    }

    @Test
    void invalidInputDueToWrongPositionInput() {
        String posOfDate = "food a/9.90 p/Lunch d/2000-01-01";
        String posOfAmount = "food a/9.90 d/2000-01-01 p/Lunch";
        Command finalCommand = MainInputParser.parseInputs(posOfDate);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");
        finalCommand = MainInputParser.parseInputs(posOfAmount);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");
    }

    @Test
    void invalidInputDueToWrongBackSlashInput() {
        String swapAWithD = "academic a/2000-01-01 d/200.0 p/Tuition";
        String replaceDWithF = "academic d/2000-01-01 f/200.0 p/Tuition";
        Command finalCommand = MainInputParser.parseInputs(swapAWithD);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");
        finalCommand = MainInputParser.parseInputs(replaceDWithF);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.InvalidCommand");
    }

    @Test
    void parseMarkCommand() {
        String inputString = "mark 1";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.MarkCommand");
    }

    @Test
    void parseUnmarkCommand() {
        String inputString = "unmark 1";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.UnmarkCommand");
    }

    @Test
    void parseSortCommand() {
        String inputString = "sort ascend";
        Command finalCommand = MainInputParser.parseInputs(inputString);
        assertEquals(finalCommand.getClass().getName(), "seedu.commands.SortCommand");
    }

}
