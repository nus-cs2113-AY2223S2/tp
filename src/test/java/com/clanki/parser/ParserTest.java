
package com.clanki.parser;

import com.clanki.commands.AddCommand;
import com.clanki.commands.ByeCommand;
import com.clanki.commands.Command;
import com.clanki.commands.DeleteCommand;
import com.clanki.commands.ListCommand;
import com.clanki.commands.ReviewCommand;
import com.clanki.commands.UnknownCommand;
import com.clanki.commands.UpdateCommand;
import com.clanki.exceptions.EmptyFlashcardAnswerException;
import com.clanki.exceptions.EmptyFlashcardQuestionException;
import com.clanki.exceptions.InvalidAddFlashcardInputException;

import com.clanki.exceptions.UpdatedContentIsEmptyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    public void parserAddCommand_correctlyFormattedInput_successful() {
        Command parsedCommand = Parser.parseCommand("add /q Question /a Answer");
        Command expectedCommand = new AddCommand("Question", "Answer");
        assertEquals(expectedCommand.toString(), parsedCommand.toString());
        Command parsedCommandNonCap = Parser.parseCommand("add /q question /a answer");
        Command expectedCommandCap = new AddCommand("Question", "Answer");
        assertEquals(expectedCommandCap.toString(), parsedCommandNonCap.toString());
    }

    @Test
    public void parserAddCommand_answerBeforeQuestion_successful() {
        Command parsedCommand = Parser.parseCommand("add /a Answer /q Question");
        Command expectedCommand = new AddCommand("Question", "Answer");
        assertEquals(expectedCommand.toString(), parsedCommand.toString());
    }

    @Test
    public void parserAddCommand_incorrectFormattedInput_invalidInputException() {
        assertThrows(InvalidAddFlashcardInputException.class,
                () -> Parser.parseCommandStrict("add Question /a Answer"));
        assertThrows(InvalidAddFlashcardInputException.class,
                () -> Parser.parseCommandStrict("add /q Question /w Answer"));
    }

    @Test
    public void parserAddCommand_partOfInputMissing_emptyInputPartException() {
        assertThrows(EmptyFlashcardAnswerException.class,
                () -> Parser.parseCommandStrict("add /q Question /a "));
        assertThrows(EmptyFlashcardQuestionException.class,
                () -> Parser.parseCommandStrict("add /q  /a Answer"));
    }

    @Test
    public void parserByeCommand_byeCommand_successful() {
        Command parsedCommand = Parser.parseCommand("bye");
        assertTrue(parsedCommand instanceof ByeCommand);
        parsedCommand = Parser.parseCommand("bye whatever");
        assertTrue(parsedCommand instanceof ByeCommand);
    }

    @Test
    public void parserReviewCommand_reviewCommand_successful() {
        Command parsedCommand = Parser.parseCommand("review");
        assertTrue(parsedCommand instanceof ReviewCommand);
    }

    @Test
    public void parserUnknownCommand_unknownCommand_successful() {
        Command parsedCommand = Parser.parseCommand("unknown");
        assertTrue(parsedCommand instanceof UnknownCommand);
    }

    @Test
    public void parserUpdateCommand_updateCommand_successful() {
        Command parsedCommand = Parser.parseCommand("update Question");
        assertTrue(parsedCommand instanceof UpdateCommand);
    }

    @Test
    public void parseInputForUpdateCommand_emptyInput_updatedContentEmptyException() {
        assertThrows(UpdatedContentIsEmptyException.class,
                () -> Parser.parseInputForUpdateCommand("1 /a /aa"));
        assertThrows(UpdatedContentIsEmptyException.class,
                () -> Parser.parseInputForUpdateCommand(("1 /a   ")));
    }

    @Test
    public void parseInputForUpdateCommand_wrongIdentifier_invalidIdentifierException() {
        assertThrows(InvalidIdentifierException.class,
                () -> Parser.parseInputForUpdateCommand("1 /w question"));
        assertThrows(InvalidIdentifierException.class,
                () -> Parser.parseInputForUpdateCommand("1 /s "));
        assertThrows(InvalidIdentifierException.class,
                () -> Parser.parseInputForUpdateCommand("1 /e       "));
    }

    @Test
    public void parserDeleteCommand_deleteCommand_successful() {
        Command parsedCommand = Parser.parseCommand("del query");
        assertTrue(parsedCommand instanceof DeleteCommand);
    }

    @Test
    public void parserListCommand_listCommand_successful() {
        Command parsedCommand = Parser.parseCommand("list all");
        assertTrue(parsedCommand instanceof ListCommand);
        parsedCommand = Parser.parseCommand("list 2023-04-06");
        assertTrue(parsedCommand instanceof ListCommand);
    }
}
