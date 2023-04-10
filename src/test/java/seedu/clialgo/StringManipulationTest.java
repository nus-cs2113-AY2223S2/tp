package seedu.clialgo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

import seedu.clialgo.exceptions.parser.EmptyFieldException;
import seedu.clialgo.exceptions.parser.NullInputException;

//@@author heejet
class StringManipulationTest {
    public static final String NAME_MARKER = "n/";
    public static final String COMMAND_MARKER = "c/";
    public static final String TOPIC_MARKER = "t/";
    public static final String KEYWORD_MARKER = "k/";
    public static final String WHITE_SPACE = " ";

    /** Test getFirstWord */
    @Test
    void getFirstWord_normalInputWhiteSpace_expectFirstWord() throws NullInputException {
        String twoWordsInput = "first word";
        String singleLetterFirstWordInput = "a word";
        String oneWordInput = "a";
        assertEquals(
                "first",
                StringManipulation.getFirstWord(twoWordsInput, WHITE_SPACE)
        );
        assertEquals(
                "a",
                StringManipulation.getFirstWord(singleLetterFirstWordInput, WHITE_SPACE)
        );
        assertEquals(
                "a",
                StringManipulation.getFirstWord(oneWordInput, WHITE_SPACE)
        );
    }

    @Test
    void getFirstWord_normalInputMarker_expectFirstWord() throws NullInputException {
        String commandMarkerInput = "help c/add";
        String topicMarkerInput = "name of file t/TOPIC_NAME";
        String keywordMarkerInput = "filter k/topic";
        assertEquals(
                "help",
                StringManipulation.getFirstWord(commandMarkerInput, COMMAND_MARKER)
        );
        assertEquals(
                "name of file",
                StringManipulation.getFirstWord(topicMarkerInput, TOPIC_MARKER)
        );
        assertEquals(
                "filter",
                StringManipulation.getFirstWord(keywordMarkerInput, KEYWORD_MARKER)
        );
    }

    @Test
    void getFirstWord_nullInput_expectNullInputException() {
        String noInput = "";
        String nullInput = null;
        assertThrows(
                NullInputException.class,
                () -> StringManipulation.getFirstWord(noInput, WHITE_SPACE)
        );
        assertThrows(
                NullInputException.class,
                () -> StringManipulation.getFirstWord(nullInput, WHITE_SPACE)
        );
    }

    /** Test removeFirstWord */
    @Test
    void removeFirstWord_normalInputWhiteSpace_expectSecondWord() throws NullInputException {
        String twoWordsInput = "first word";
        String singleLetterFirstWordInput = "a word";
        String oneWordInput = "a";
        assertEquals(
                "word",
                StringManipulation.removeFirstWord(twoWordsInput, WHITE_SPACE)
        );
        assertEquals(
                "word",
                StringManipulation.removeFirstWord(singleLetterFirstWordInput, WHITE_SPACE)
        );
        assertNull(StringManipulation.removeFirstWord(oneWordInput, WHITE_SPACE));
    }

    @Test
    void removeFirstWord_normalInputMarker_expectSecondWord() throws NullInputException {
        String commandMarkerInput = "help c/add";
        String topicMarkerInput = "name of file t/TOPIC_NAME";
        String keywordMarkerInput = "filter k/topic";
        assertEquals(
                "add",
                StringManipulation.removeFirstWord(commandMarkerInput, COMMAND_MARKER)
        );
        assertEquals(
                "TOPIC_NAME",
                StringManipulation.removeFirstWord(topicMarkerInput, TOPIC_MARKER)
        );
        assertEquals(
                "topic",
                StringManipulation.removeFirstWord(keywordMarkerInput, KEYWORD_MARKER)
        );
    }

    @Test
    void removeFirstWord_nullInput_expectNullInputException() {
        String noInput = "";
        String nullInput = null;
        assertThrows(
                NullInputException.class,
                () -> StringManipulation.removeFirstWord(noInput, WHITE_SPACE)
        );
        assertThrows(
                NullInputException.class,
                () -> StringManipulation.removeFirstWord(nullInput, WHITE_SPACE)
        );
    }

    /** Test removeMarker */
    @Test
    void removeMarker_normalInput_expectKeyword() throws EmptyFieldException, NullInputException {
        String nameMarkerInput = "n/NAME";
        String commandMarkerInput = "c/COMMAND";
        String topicMarkerInput = "t/TOPIC";
        String keywordMarkerInput = "k/KEYWORD";
        assertEquals(
                "NAME",
                StringManipulation.removeMarker(nameMarkerInput, NAME_MARKER)
        );
        assertEquals(
                "COMMAND",
                StringManipulation.removeMarker(commandMarkerInput, COMMAND_MARKER)
        );
        assertEquals(
                "TOPIC",
                StringManipulation.removeMarker(topicMarkerInput, TOPIC_MARKER)
        );
        assertEquals(
                "KEYWORD",
                StringManipulation.removeMarker(keywordMarkerInput, KEYWORD_MARKER)
        );
    }

    @Test
    void removeMarker_emptyFieldInput_expectEmptyFieldException() {
        String emptyFieldInput = "n/";
        assertThrows(
                EmptyFieldException.class,
                () -> StringManipulation.removeMarker(emptyFieldInput, NAME_MARKER)
        );
    }

    @Test
    void removeMarker_nullInput_expectNullInputException() {
        String noInput = "";
        String nullInput = null;
        assertThrows(
                NullInputException.class,
                () -> StringManipulation.removeMarker(noInput, NAME_MARKER)
        );
        assertThrows(
                NullInputException.class,
                () -> StringManipulation.removeMarker(nullInput, NAME_MARKER)
        );
    }
}
