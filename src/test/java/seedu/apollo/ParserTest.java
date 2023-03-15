package seedu.apollo;

import org.junit.jupiter.api.Test;
import seedu.apollo.command.Command;
import seedu.apollo.exception.task.InvalidDeadline;
import seedu.apollo.exception.task.InvalidEvent;
import seedu.apollo.ui.Parser;
import seedu.apollo.ui.Ui;

import java.rmi.UnexpectedException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;



class ParserTest {

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
        Command newCommand = Parser.getCommand(userCommand,ui,size, null);
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
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertNull(newCommand);
    }

    @Test
    void getCommand_noModuleAdded_expectNull() throws UnexpectedException {
        String userCommand = "addmod";
        Ui ui = new Ui();
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertNull(newCommand);
    }

    @Test
    void getCommand_noModuleDeleted_expectNull() throws UnexpectedException {
        String userCommand = "delmod";
        Ui ui = new Ui();
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertNull(newCommand);
    }

    @Test
    void getCommand_noMarkIndex_expectNull() throws UnexpectedException {
        String userCommand = "mark";
        Ui ui = new Ui();
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertNull(newCommand);
    }

    @Test
    void getCommand_noUnmarkIndex_expectNull() throws UnexpectedException {
        String userCommand = "unmark";
        Ui ui = new Ui();
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertNull(newCommand);
    }

    @Test
    void getCommand_nonIntegerMarkIndex_expectException() throws UnexpectedException {
        String userCommand = "mark a";
        Ui ui = new Ui();
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertNull(newCommand);
    }
    @Test
    void getCommand_nonIntegerUnmarkIndex_expectException() throws UnexpectedException {
        String userCommand = "unmark a";
        Ui ui = new Ui();
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertNull(newCommand);
    }

    @Test
    void getCommand_noTodoDescription_expectNull() throws UnexpectedException {
        String userCommand = "todo";
        Ui ui = new Ui();
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertNull(newCommand);
    }

    @Test
    void getCommand_noEventDescription_expectNull() throws UnexpectedException {
        String userCommand = "event";
        Ui ui = new Ui();
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertNull(newCommand);
    }

    @Test
    void getCommand_noDeadlineDescription_expectNull() throws UnexpectedException {
        String userCommand = "deadline";
        Ui ui = new Ui();
        int size = 1;
        Command newCommand = Parser.getCommand(userCommand, ui, size, null);
        assertNull(newCommand);
    }


}
