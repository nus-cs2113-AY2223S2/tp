package utils.parser;

import java.util.List;
import model.TagSelector;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import utils.command.Command;
import utils.command.DeleteDeckCommand;
import utils.command.EditDeckNameCommand;
import utils.command.ListCardsUnderDeckCommand;
import utils.command.ListDecksCommand;
import utils.command.ListTagsUnderDeckCommand;
import utils.command.PrintHelpCommand;
import utils.command.RemoveCardFromDeckCommand;
import utils.command.RemoveTagFromDeckCommand;
import utils.command.RunCommand;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.exceptions.UnrecognizedCommandException;

public class DeckKeywordParser extends KeywordParser {
    public static final String DELETE_ACTION = "delete";
    public static final String EDIT_ACTION = "edit";
    public static final String HELP_ACTION = "help";
    public static final String LIST_ACTION = "list";
    public static final String RUN_ACTION = "run";

    @Override
    protected Command handleAction(String action, List<String> tokens)
            throws ParseException, InkaException {
        switch (action) {
        case DELETE_ACTION:
            return handleDelete(tokens);
        case EDIT_ACTION:
            return handleEdit(tokens);
        case HELP_ACTION:
            return handleHelp(tokens);
        case LIST_ACTION:
            return handleList(tokens);
        case RUN_ACTION:
            return handleRun(tokens);
        default:
            throw new UnrecognizedCommandException();
        }
    }

    private Command handleDelete(List<String> tokens) throws ParseException, InkaException {

        Options deleteOptions = new OptionsBuilder(Parser.DECK_KEYWORD, DELETE_ACTION).buildOptions();
        CommandLine cmd = parseUsingOptions(deleteOptions, tokens);

        TagSelector tagSelector = getSelectedTag(cmd);
        String deckName = cmd.getOptionValue("d");
        if (cmd.hasOption("c")) {
            return new RemoveCardFromDeckCommand(cmd.getOptionValue("c"), deckName);
        } else if (cmd.hasOption("t")) {
            return new RemoveTagFromDeckCommand(tagSelector, deckName);
        } else {
            return new DeleteDeckCommand(deckName);
        }
    }

    private Command handleList(List<String> tokens) throws ParseException, InvalidSyntaxException {

        Options listOptions = new OptionsBuilder(Parser.DECK_KEYWORD, LIST_ACTION).buildOptions();
        CommandLine cmd = parseUsingOptions(listOptions, tokens);

        if (cmd.hasOption("c")) {
            String deckName = cmd.getOptionValue("c");
            return new ListCardsUnderDeckCommand(deckName);
        } else if (cmd.hasOption("t")) {
            String deckName = cmd.getOptionValue("t");
            return new ListTagsUnderDeckCommand(deckName);
        } else {
            return new ListDecksCommand();
        }
    }

    private Command handleEdit(List<String> tokens) throws ParseException, InvalidSyntaxException {

        Options editOptions = new OptionsBuilder(Parser.DECK_KEYWORD, EDIT_ACTION).buildOptions();
        CommandLine cmd = parseUsingOptions(editOptions, tokens);

        String oldDeckName = cmd.getOptionValue("o");
        String newDeckName = cmd.getOptionValue("n");
        return new EditDeckNameCommand(oldDeckName, newDeckName);
    }

    private Command handleHelp(List<String> tokens) throws InvalidSyntaxException {

        if (tokens.size() != 0) {
            throw InvalidSyntaxException.buildTooManyTokensMessage();
        }

        Options editOptions = new OptionsBuilder(Parser.DECK_KEYWORD, EDIT_ACTION).buildOptions();
        Options deleteOptions = new OptionsBuilder(Parser.DECK_KEYWORD, DELETE_ACTION).buildOptions();
        Options listOptions = new OptionsBuilder(Parser.DECK_KEYWORD, LIST_ACTION).buildOptions();
        // Combine all actions
        String[] actionList = {EDIT_ACTION, DELETE_ACTION, LIST_ACTION};
        String[] headerList = {"Edit existing decks", "Delete decks", "List decks"};
        Options[] optionsList = {editOptions, deleteOptions, listOptions};

        String helpMessage = formatHelpMessage("deck", actionList, headerList, optionsList);
        return new PrintHelpCommand(helpMessage);
    }

    private Command handleRun(List<String> tokens) throws ParseException, InvalidSyntaxException {

        Options runOptions = new OptionsBuilder(Parser.DECK_KEYWORD, RUN_ACTION).buildOptions();
        CommandLine cmd = parseUsingOptions(runOptions, tokens);

        String deckName = cmd.getOptionValue("d");
        return new RunCommand(deckName);
    }
}
