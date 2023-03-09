
package com.clanki.parser;

import com.clanki.commands.AddCommand;
import com.clanki.commands.Command;
import com.clanki.objects.exceptions.EmptyFlashcardAnswerException;
import com.clanki.objects.exceptions.EmptyFlashcardQuestionException;
import com.clanki.objects.exceptions.InvalidAddFlashcardInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

}
