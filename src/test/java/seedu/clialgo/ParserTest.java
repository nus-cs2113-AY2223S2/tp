package seedu.clialgo;

import org.junit.jupiter.api.Test;
import seedu.clialgo.command.AddCommand;
import seedu.clialgo.command.Command;
import seedu.clialgo.command.ExitCommand;
import seedu.clialgo.command.ExitTestModeCommand;
import seedu.clialgo.command.ExportCommand;
import seedu.clialgo.command.FilterCommand;
import seedu.clialgo.command.HelpCommand;
import seedu.clialgo.command.InvalidCommand;
import seedu.clialgo.command.InvalidImportanceCommand;
import seedu.clialgo.command.InvalidTopicCommand;
import seedu.clialgo.command.ListCommand;
import seedu.clialgo.command.RemoveCommand;
import seedu.clialgo.command.TestModeCommand;
import seedu.clialgo.command.TopoCommand;
import seedu.clialgo.logic.TopicManager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

//@@author heejet
class ParserTest {
    /** Test isValidCommand */
    @Test
    void isValidCommand() {
        Parser parser = new Parser();
        ArrayList<String> correctCommands = new ArrayList<>(
                Arrays.asList("help", "add", "remove", "filter", "exit")
        );
        ArrayList<String> wrongCommands = new ArrayList<String>(
                Arrays.asList("Help", "aDd", "remov", "filtre", "exits", "")
        );
        for (String correctCommand : correctCommands) {
            assertTrue(parser.isValidCommand(correctCommand));
        }
        for (String wrongCommand : wrongCommands) {
            assertFalse(parser.isValidCommand(wrongCommand));
        }
    }

    /** Test isValidKeyword */
    @Test
    void isValidKeyword() {
        Parser parser = new Parser();
        ArrayList<String> correctKeywords = new ArrayList<>(
                Arrays.asList("topic")
        );
        ArrayList<String> wrongKeywords = new ArrayList<String>(
                Arrays.asList("topiC", "toic", "")
        );
        for (String correctKeyword : correctKeywords) {
            assertTrue(parser.isValidKeyword(correctKeyword));
        }
        for (String wrongKeyword : wrongKeywords) {
            assertFalse(parser.isValidKeyword(wrongKeyword));
        }
    }

    /** Test isCorrectMarker */
    @Test
    void isCorrectMarker_correctInput_expectTrue() {
        Parser parser = new Parser();
        String nameMarkerInput = "n/NAME";
        String commandMarkerInput = "c/COMMAND";
        String topicMarkerInput = "t/TOPIC";
        String keywordMarkerInput = "k/KEYWORD";
        assertTrue(parser.isCorrectMarker(nameMarkerInput, Parser.NAME_MARKER));
        assertTrue(parser.isCorrectMarker(commandMarkerInput, Parser.COMMAND_MARKER));
        assertTrue(parser.isCorrectMarker(topicMarkerInput, Parser.TOPIC_MARKER));
        assertTrue(parser.isCorrectMarker(keywordMarkerInput, Parser.KEYWORD_MARKER));
    }

    @Test
    void isCorrectMarker_wrongInput_expectFalse() {
        Parser parser = new Parser();
        ArrayList<String> wrongNameInputs = new ArrayList<String>(
                Arrays.asList("c/COMMAND", "t/TOPIC", "k/KEYWORD", "/nNAME", "N/NAME")
        );
        ArrayList<String> wrongCommandInputs = new ArrayList<String>(
                Arrays.asList("n/NAME", "t/TOPIC", "k/KEYWORD", "/cCOMMAND", "C/COMMAND")
        );
        ArrayList<String> wrongTopicInputs = new ArrayList<String>(
                Arrays.asList("c/COMMAND", "n/NAME", "k/KEYWORD", "/tTOPIC", "T/TOPIC")
        );
        ArrayList<String> wrongKeywordInputs = new ArrayList<String>(
                Arrays.asList("c/COMMAND", "t/TOPIC", "n/NAME", "/kKEYWORD", "K/KEYWORD")
        );

        for (String wrongNameInput : wrongNameInputs) {
            assertFalse(parser.isCorrectMarker(wrongNameInput, Parser.NAME_MARKER));
        }
        for (String wrongCommandInput : wrongCommandInputs) {
            assertFalse(parser.isCorrectMarker(wrongCommandInput, Parser.COMMAND_MARKER));
        }
        for (String wrongTopicInput : wrongTopicInputs) {
            assertFalse(parser.isCorrectMarker(wrongTopicInput, Parser.TOPIC_MARKER));
        }
        for (String wrongKeywordInput : wrongKeywordInputs) {
            assertFalse(parser.isCorrectMarker(wrongKeywordInput, Parser.KEYWORD_MARKER));
        }
    }

    @Test
    void isCorrectMarker_invalidInput_expectException() {
        Parser parser = new Parser();
        String oneLetterInput = "c";
        String emptyString = "";
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> parser.isCorrectMarker(oneLetterInput, Parser.COMMAND_MARKER)
        );
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> parser.isCorrectMarker(emptyString, Parser.COMMAND_MARKER)
        );
    }

    /** Test parse */
    @Test
    void parse_normalInput_expectCorrectCommandObject() {
        Parser parser = new Parser();
        TopicManager topics = new TopicManager();

        String addInput = "add n/Name of File t/LINKED_LIST";
        AddCommand correctAddOutput = new AddCommand("Name of File", "LINKED_LIST");
        Command actualAddOutput = parser.parse(addInput, topics);
        assertTrue(correctAddOutput.equals(actualAddOutput));

        String addInputWithImportance = "add n/note name t/SORTING i/8";
        AddCommand correctAddInputWithImportanceOutput = new AddCommand("note name", "SORTING", 8);
        Command actualAddInputWithImportanceOutput = parser.parse(addInputWithImportance, topics);
        assertTrue(correctAddInputWithImportanceOutput.equals(actualAddInputWithImportanceOutput));

        String removeInput = "remove n/note name";
        RemoveCommand correctRemoveInputOutput = new RemoveCommand("note name");
        Command actualRemoveInputOutput = parser.parse(removeInput, topics);
        assertTrue(correctRemoveInputOutput.equals(actualRemoveInputOutput));

        String helpInputNoCommand = "help";
        HelpCommand correctHelpNoCommandOutput = new HelpCommand();
        Command actualHelpNoCommandOutput = parser.parse(helpInputNoCommand, topics);
        assertTrue(correctHelpNoCommandOutput.equals(actualHelpNoCommandOutput));

        String helpAdd = "help c/add";
        HelpCommand correctHelpAddOutput = new HelpCommand("add");
        Command actualHelpAddOutput = parser.parse(helpAdd, topics);
        assertTrue(correctHelpAddOutput.equals(actualHelpAddOutput));

        String helpRemove = "help c/remove";
        HelpCommand correctHelpRemoveOutput = new HelpCommand("remove");
        Command actualHelpRemoveOutput = parser.parse(helpRemove, topics);
        assertTrue(correctHelpRemoveOutput.equals(actualHelpRemoveOutput));

        String helpFilter = "help c/filter";
        HelpCommand correctHelpFilterOutput = new HelpCommand("filter");
        Command actualHelpFilterOutput = parser.parse(helpFilter, topics);
        assertTrue(correctHelpFilterOutput.equals(actualHelpFilterOutput));

        String helpList = "help c/list";
        HelpCommand correctHelpListOutput = new HelpCommand("list");
        Command actualHelpListOutput = parser.parse(helpList, topics);
        assertTrue(correctHelpListOutput.equals(actualHelpListOutput));

        String listInput = "list";
        ListCommand correctListOutput = new ListCommand();
        Command actualListOutput = parser.parse(listInput, topics);
        assertTrue(correctListOutput.equals(actualListOutput));

        String filterNoTopicInput = "filter k/topic";
        FilterCommand correctFilterNoTopicOutput = new FilterCommand("topic", null);
        Command actualFilterNoTopicOutput = parser.parse(filterNoTopicInput, topics);
        assertTrue(correctFilterNoTopicOutput.equals(actualFilterNoTopicOutput));

        String filterTopicInput = "filter k/topic t/BINARY_SEARCH_TREE";
        FilterCommand correctFilterTopicOutput = new FilterCommand("topic", "BINARY_SEARCH_TREE");
        Command actualFilterTopicOutput = parser.parse(filterTopicInput, topics);
        assertTrue(correctFilterTopicOutput.equals(actualFilterTopicOutput));

        String exitInput = "exit";
        ExitCommand correctExitOutput = new ExitCommand();
        Command actualExitOutput = parser.parse(exitInput, topics);
        assertTrue(correctExitOutput.equals(actualExitOutput));

        String startTestInput = "start-test-mode";
        TestModeCommand correctStartTestOutput = new TestModeCommand();
        Command actualStartTestOutput = parser.parse(startTestInput, topics);
        assertTrue(correctStartTestOutput.equals(actualStartTestOutput));

        String exitTestInput = "exit-test-mode";
        ExitTestModeCommand correctExitTestOutput = new ExitTestModeCommand();
        Command actualExitTestOutput = parser.parse(exitTestInput, topics);
        assertTrue(correctExitTestOutput.equals(actualExitTestOutput));
    }

    @Test
    void parse_invalidImportance_expectInvalidImportanceCommandObject() {
        Parser parser = new Parser();
        TopicManager topics = new TopicManager();

        ArrayList<String> invalidImportanceInputs = new ArrayList<>(
                Arrays.asList("100", "-2", "dfsdhk", ".", "4fg")
        );
        for (String importanceInput : invalidImportanceInputs) {
            String input = "add n/note name t/SORTING i/" + importanceInput;
            InvalidImportanceCommand expectedOutput = new InvalidImportanceCommand(importanceInput);

            Command actualOutput = parser.parse(input, topics);
            assertTrue(expectedOutput.equals(actualOutput));
        }
    }
    @Test
    void parse_invalidInput_expectInvalidCommandObject() {
        Parser parser = new Parser();
        TopicManager topics = new TopicManager();

        InvalidCommand expectedOutput = new InvalidCommand();

        ArrayList<String> invalidKeywordFields = new ArrayList<>(
                Arrays.asList("", "dfsdhk", ".", "IMPORTANCE", " ", "Topic", null)
        );
        for (String invalidKeywordField : invalidKeywordFields) {
            String wrongKeywordInput = "filter k/" + invalidKeywordField;

            Command actualWrongKeywordOutput = parser.parse(wrongKeywordInput, topics);
            assertTrue(expectedOutput.equals(actualWrongKeywordOutput));
        }

        ArrayList<String> invalidAddFields = new ArrayList<>(
                Arrays.asList(" t/SORTING", "", "note ", "note t/", null)
        );
        for (String invalidAddField: invalidAddFields) {
            String wrongAddInput = "add n/" + invalidAddField;

            Command actualWrongAddOutput = parser.parse(wrongAddInput, topics);
            assertTrue(expectedOutput.equals(actualWrongAddOutput));
        }

        ArrayList<String> invalidSingleCommands= new ArrayList<>(
                Arrays.asList("list ....", "export ashds", "exit       . ")
        );
        for (String invalidSingleCommand: invalidSingleCommands) {

            Command actualInvalidSingleCommandOutput = parser.parse(invalidSingleCommand, topics);
            assertTrue(expectedOutput.equals(actualInvalidSingleCommandOutput));
        }
    }

    @Test
    void parse_invalidHelpCommand_expectInvalidCommandObject() {
        Parser parser = new Parser();
        TopicManager topics = new TopicManager();
        ArrayList<String> invalidCommandFields = new ArrayList<>(
                Arrays.asList("ADD", "dfsdhk", "1remove", ".", " ")
        );
        InvalidCommand expectedOutput = new InvalidCommand();
        for (String invalidCommandField : invalidCommandFields) {
            String wronginvalidHelpCommandInput = "help c/" + invalidCommandField;

            Command actualWrongFirstMarkerOutput = parser.parse(wronginvalidHelpCommandInput, topics);
            assertTrue(expectedOutput.equals(actualWrongFirstMarkerOutput));
        }
    }

    @Test
    void parse_wrongMarkerInput_expectInvalidCommandObject() {
        Parser parser = new Parser();
        TopicManager topics = new TopicManager();
        String wrongFirstMarkerInput = "add c/Name of File t/LINKED_LIST";
        InvalidCommand correctWrongFirstMarkerOutput = new InvalidCommand();
        Command actualWrongFirstMarkerOutput = parser.parse(wrongFirstMarkerInput, topics);
        assertTrue(correctWrongFirstMarkerOutput.equals(actualWrongFirstMarkerOutput));

        String wrongSecondMarkerInput = "add n/Name of File c/LINKED_LIST";
        InvalidCommand correctWrongSecondMarkerOutput = new InvalidCommand();
        Command actualWrongSecondMarkerOutput = parser.parse(wrongSecondMarkerInput, topics);
        assertTrue(correctWrongSecondMarkerOutput.equals(actualWrongSecondMarkerOutput));

        String wrongRemoveMarkerInput = "remove t/Name of File";
        InvalidCommand correctWrongRemoveMarkerInputOutput = new InvalidCommand();
        Command actualWrongRemoveMarkerInputOutput = parser.parse(wrongRemoveMarkerInput, topics);
        assertTrue(correctWrongRemoveMarkerInputOutput.equals(actualWrongRemoveMarkerInputOutput));

        String wrongSingleMarker = "help t/add";
        InvalidCommand correctWrongSingleMarkerOutput = new InvalidCommand();
        Command actualWrongSingleMarkerOutput = parser.parse(wrongSingleMarker, topics);
        assertTrue(correctWrongSingleMarkerOutput.equals(actualWrongSingleMarkerOutput));

        String optionalFieldInput = "filter t/keyword";
        InvalidCommand correctOptionalFieldOutput = new InvalidCommand();
        Command actualOptionalFieldOutput = parser.parse(optionalFieldInput, topics);
        assertTrue(correctOptionalFieldOutput.equals(actualOptionalFieldOutput));

        String wrongOptionalMarker = "filter k/topic t/BINARY_SEARCH_TREE";
        FilterCommand correctWrongOptionalMarkerOutput = new FilterCommand("topic", "BINARY_SEARCH_TREE");
        Command actualWrongOptionalMarkerOutput = parser.parse(wrongOptionalMarker, topics);
        assertTrue(correctWrongOptionalMarkerOutput.equals(actualWrongOptionalMarkerOutput));
    }

    @Test
    void parse_wrongTopicInput_expectInvalidTopicObject() {
        Parser parser = new Parser();
        TopicManager topics = new TopicManager();
        String wrongSpellingInput = "add n/Name of File t/LINKED LIST";
        InvalidTopicCommand correctWrongSpellingOutput = new InvalidTopicCommand("LINKED LIST");
        Command actualWrongSpellingOutput = parser.parse(wrongSpellingInput, topics);
        assertTrue(correctWrongSpellingOutput.equals(actualWrongSpellingOutput));

        String lowerCaseTopicInput = "add n/Name of File t/sorting";
        InvalidTopicCommand correctLowerCaseTopicOutput = new InvalidTopicCommand("sorting");
        Command actualLowerCaseTopicOutput = parser.parse(lowerCaseTopicInput, topics);
        assertTrue(correctLowerCaseTopicOutput.equals(actualLowerCaseTopicOutput));

        String outOfSyllabusInput = "filter k/keyword t/FENWICK_TREE";
        InvalidTopicCommand correctOutOfSyllabusOutput = new InvalidTopicCommand("FENWICK_TREE");
        Command actualOutOfSyllabusOutput = parser.parse(outOfSyllabusInput, topics);
        assertTrue(correctOutOfSyllabusOutput.equals(actualOutOfSyllabusOutput));
    }

    @Test
    void parse_emptyFieldInput_expectInvalidCommandObject() {
        Parser parser = new Parser();
        TopicManager topics = new TopicManager();
        String emptyFirstFieldInput = "add n/ t/LINKED_LIST";
        InvalidCommand correctEmptyFirstFieldOutput = new InvalidCommand();
        Command actualEmptyFirstFieldOutput = parser.parse(emptyFirstFieldInput, topics);
        assertTrue(correctEmptyFirstFieldOutput.equals(actualEmptyFirstFieldOutput));

        String emptySecondFieldInput = "add n/Name of File t/";
        InvalidCommand correctEmptySecondFieldOutput = new InvalidCommand();
        Command actualEmptySecondFieldOutput = parser.parse(emptySecondFieldInput, topics);
        assertTrue(correctEmptySecondFieldOutput.equals(actualEmptySecondFieldOutput));

        String removeInput = "remove n/";
        InvalidCommand correctRemoveOutput = new InvalidCommand();
        Command actualCorrectRemoveOutput  = parser.parse(removeInput, topics);
        assertTrue(correctRemoveOutput.equals(actualCorrectRemoveOutput));

        String emptySingleFieldInput = "help c/";
        InvalidCommand correctEmptySingleFieldOutput = new InvalidCommand();
        Command actualEmptySingleFieldOutput = parser.parse(emptySingleFieldInput, topics);
        assertTrue(correctEmptySingleFieldOutput.equals(actualEmptySingleFieldOutput));

        String emptyOptionalFieldInput = "filter k/topic t/";
        FilterCommand correctEmptyOptionalFieldOutput = new FilterCommand("topic", null);
        Command actualEmptyOptionalFieldOutput = parser.parse(emptyOptionalFieldInput, topics);
        assertTrue(correctEmptyOptionalFieldOutput.equals(actualEmptyOptionalFieldOutput));
    }

    @Test
    void parse_nullInput_expectInvalidCommandObject() {
        Parser parser = new Parser();
        TopicManager topics = new TopicManager();

        ArrayList<String> commands = new ArrayList<>(
                Arrays.asList("add", "remove", "filter", "", null)
        );

        for (String command : commands) {
            String noSpaceInput = command;
            InvalidCommand correctNoSpaceOutput = new InvalidCommand();
            Command actualNoSpaceOutput = parser.parse(noSpaceInput, topics);
            assertTrue(correctNoSpaceOutput.equals(actualNoSpaceOutput));

            String withSpaceInput = command + "        ";
            InvalidCommand correctWithSpaceOutput = new InvalidCommand();
            Command actualWithSpaceOutput = parser.parse(withSpaceInput, topics);
            assertTrue(correctWithSpaceOutput.equals(actualWithSpaceOutput));
        }
    }

    @Test
    void parse_invalidExport_expectInvalidCommandObject() {
        Parser parser = new Parser();
        TopicManager topics = new TopicManager();

        ArrayList<String> inputCommands = new ArrayList<>(
                Arrays.asList("export.", "Export", "expt", "exportexport", "ex port")
        );

        InvalidCommand expectedOutput = new InvalidCommand();

        for (String command : inputCommands) {
            Command actualNoSpaceOutput = parser.parse(command, topics);
            assertTrue(expectedOutput.equals(actualNoSpaceOutput));
        }
    }

    @Test
    void parse_validExport_expectExportCommandObject() {
        Parser parser = new Parser();
        TopicManager topics = new TopicManager();

        String input = "export";

        ExportCommand expectedOutput = new ExportCommand();

        Command actualNoSpaceOutput = parser.parse(input, topics);
        assertTrue(expectedOutput.equals(actualNoSpaceOutput));
    }

    @Test
    void parse_invalidTopo_expectInvalidCommandObject() {
        Parser parser = new Parser();
        TopicManager topics = new TopicManager();

        ArrayList<String> nameFields = new ArrayList<>(
                Arrays.asList("", "N/noteName", "k/noteName", null, "n/")
        );

        InvalidCommand expectedOutput = new InvalidCommand();

        for (String nameField : nameFields) {
            String input = "topo " + nameField;
            Command actualOutput = parser.parse(input, topics);
            assertTrue(expectedOutput.equals(actualOutput));
        }
    }

    @Test
    void parse_validTopo_expectTopoCommandObject() {
        Parser parser = new Parser();
        TopicManager topics = new TopicManager();

        ArrayList<String> nameFields = new ArrayList<>(
                Arrays.asList("linked list", "     linked list      ", "42343", "h2312", "asd_323")
        );

        for (String nameField : nameFields) {
            String input = "topo n/" + nameField;
            Command actualOutput = parser.parse(input, topics);
            TopoCommand expectedOutput = new TopoCommand(nameField.trim());
            assertTrue(expectedOutput.equals(actualOutput));
        }
    }
}
