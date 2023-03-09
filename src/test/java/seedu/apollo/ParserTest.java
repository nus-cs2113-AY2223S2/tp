package seedu.apollo;

import org.junit.jupiter.api.Test;
import seedu.apollo.command.Command;
import seedu.apollo.exception.InvalidDeadline;
import seedu.apollo.exception.InvalidEvent;

import java.rmi.UnexpectedException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;


class ParserTest {

    @Test
    void parseDateTime_dateLocalDateTime_expectDateString() {

        LocalDateTime date = LocalDateTime.parse("2023-01-01T23:59");
        String dateString = null;
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
        String output = Parser.parseDateTime(date, dateString, pattern);
        assertEquals("Jan 01 2023, 11:59PM", output);

    }

    @Test
    void parseDateTime_dateString_expectDateString() {

        LocalDateTime date = null;
        String dateString = "test";
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma");
        String output = Parser.parseDateTime(date, dateString, pattern);
        assertEquals("test", output);

    }

    @Test
    void parseDeadline_normalDeadline_expectDescriptionAndBy() throws InvalidDeadline {
        String param = "test /by tomorrow";
        String[] descriptionAndBy = Parser.parseDeadline(param);
        assertEquals("test", descriptionAndBy[0]);
        assertEquals("tomorrow", descriptionAndBy[1]);
    }

    @Test
    void parseDeadline_noBy_expectException() {
        String param = "test";
        assertThrows(InvalidDeadline.class,
                () -> Parser.parseDeadline(param));
    }

    @Test
    void parseDeadline_emptyBy_expectException() {
        String param = "test /by ";
        assertThrows(InvalidDeadline.class,
                () -> Parser.parseDeadline(param));
    }

    @Test
    void parseDeadline_noDescription_expectException() {
        String param = "  /by tomorrow";
        assertThrows(InvalidDeadline.class,
                () -> Parser.parseDeadline(param));
    }

    @Test
    void getCommand_invalidCommand_expectNull( ) throws UnexpectedException {
        Ui ui = new Ui();
        int size = 1;
        String userCommand = "draw";
        Command newCommand = Parser.getCommand(userCommand,ui,size);
        assertNull(newCommand);

    }
    
    @Test
    void parseEvent_normalEvent_expectDescriptionAndFromAndTo() throws InvalidEvent {
        String param = "test /from today /to tomorrow";
        String[] descriptionAndFromAndTo = Parser.parseEvent(param);
        assertEquals("test", descriptionAndFromAndTo[0]);
        assertEquals("today", descriptionAndFromAndTo[1]);
        assertEquals("tomorrow", descriptionAndFromAndTo[2]);
    }

    @Test
    void parseEvent_noDescription_expectException() {
        String param = "  /from today /to tomorrow";
        assertThrows(InvalidEvent.class,
                () -> Parser.parseEvent(param));
    }

    @Test
    void parseEvent_emptyFrom_expectException() {
        String param = "test /from /to tomorrow";
        assertThrows(InvalidEvent.class,
                () -> Parser.parseEvent(param));

    }

    @Test
    void parseEvent_emptyTo_expectException() {
        String param = "test /from today /to ";
        assertThrows(InvalidEvent.class,
                () -> Parser.parseEvent(param));
    }

    @Test
    void parseEvent_emptyFromAndTo_expectException() {
        String param = "test /from /to";
        assertThrows(InvalidEvent.class,
                () -> Parser.parseEvent(param));
    }

    @Test
    void parseEvent_noFromAndTo_expectException() {
        String param = "test";
        assertThrows(InvalidEvent.class,
                () -> Parser.parseEvent(param));
    }

    @Test
    void parseEvent_noFrom_expectException() {
        String param = "test /to tomorrow";
        assertThrows(InvalidEvent.class,
                () -> Parser.parseEvent(param));
    }

    @Test
    void parseEvent_noBy_expectException() {
        String param = "test /from today";
        assertThrows(InvalidEvent.class,
                () -> Parser.parseEvent(param));
    }
    
    @Test
    void getCommand_noKeywordFind_expectNull() throws UnexpectedException {
        String userCommand = "find";
        Ui ui = new Ui();
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size);
        assertNull(newCommand);
    }

}
