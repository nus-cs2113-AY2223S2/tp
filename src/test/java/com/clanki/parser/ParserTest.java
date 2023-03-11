
package com.clanki.parser;

import com.clanki.commands.AddCommand;
import com.clanki.commands.ByeCommand;
import com.clanki.commands.Command;
import com.clanki.commands.UnknownCommand;
import com.clanki.exceptions.EmptyFlashcardAnswerException;
import com.clanki.exceptions.EmptyFlashcardQuestionException;
import com.clanki.exceptions.InvalidAddFlashcardInputException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    public void parseAddCommand_correctlyFormattedInput_successful() {
        Parser parser = new Parser();
        Command parsedCommand = parser.parseCommand("add /q Question /a Answer");
        Command expectedCommand = new AddCommand("Question", "Answer");
        assertEquals(expectedCommand.toString(), parsedCommand.toString());
    }

    @Test
    public void parserAddCommand_incorrectFormattedInput_invalidInputException() {
        Parser parser = new Parser();
        assertThrows(InvalidAddFlashcardInputException.class,
                () -> parser.reformatAddCommandInput("add Question /a Answer"));
        assertThrows(InvalidAddFlashcardInputException.class,
                () -> parser.reformatAddCommandInput("add /q Question /w Answer"));
    }

    @Test
    public void parserAddCommand_partOfInputMissing_emptyInputPartException() {
        Parser parser = new Parser();
        assertThrows(EmptyFlashcardAnswerException.class,
                () -> parser.reformatAddCommandInput("add /q Question /a "));
        assertThrows(EmptyFlashcardQuestionException.class,
                () -> parser.reformatAddCommandInput("add /q  /a Answer"));
    }

    @Test
    public void parserByeCommand_byeCommand_successful() {
        Parser parser = new Parser();
        Command parsedCommand = parser.parseCommand("bye");
        assertTrue(parsedCommand instanceof ByeCommand);
        parsedCommand = parser.parseCommand("bye whatever");
        assertTrue(parsedCommand instanceof ByeCommand);
    }

    @Test
    public void parserUnknownCommand_unknownCommand_successful() {
        Parser parser = new Parser();
        Command parsedCommand = parser.parseCommand("unknown");
        assertTrue(parsedCommand instanceof UnknownCommand);
    }
}
