package utils.parser;

import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import utils.command.AddTagToDeckCommand;
import utils.command.Command;
import utils.command.DeleteTagCommand;
import utils.command.EditTagNameCommand;
import utils.command.ListCardsUnderTagCommand;
import utils.command.ListTagsCommand;
import utils.command.PrintHelpCommand;
import utils.exceptions.InkaException;
import utils.exceptions.UnrecognizedCommandException;

public class TagKeywordParser extends KeywordParser {

    public static final String DELETE_ACTION = "delete";
    public static final String EDIT_ACTION = "edit";
    public static final String HELP_ACTION = "help";
    public static final String LIST_ACTION = "list";
    public static final String DECK_ACTION = "deck";

    private static Options buildDeleteOptions() {
        Options options = new Options();
        options.addRequiredOption("t", "tag", true, "tag name");

        return options;
    }

    private static Options buildDeckOptions() {
        Options options = new Options();
        options.addRequiredOption("t", "tag", true, "tag name");
        options.addRequiredOption("d", "deck", true, "deck name");

        return options;
    }

    private static Options buildEditOptions() {
        Options options = new Options();
        options.addRequiredOption("o", "old", true, "Old tag name");

        Option newTag = buildMultipleTokenOption("n", "new", true, "New tag name", true);
        options.addOption(newTag);

        return options;
    }

    private static Options buildListOptions() {
        Options options = new Options();
        options.addOption("t", "tag", true, "tag name (optional)");

        return options;
    }

    @Override
    protected Command handleAction(String action, List<String> tokens)
            throws ParseException, InkaException {
        switch (action) {
        case DECK_ACTION:
            return handleDeck(tokens);
        case DELETE_ACTION:
            return handleDelete(tokens);
        case EDIT_ACTION:
            return handleEdit(tokens);
        case HELP_ACTION:
            return handleHelp();
        case LIST_ACTION:
            return handleList(tokens);
        default:
            throw new UnrecognizedCommandException();
        }
    }

    private Command handleDelete(List<String> tokens) throws ParseException {
        CommandLine cmd = parser.parse(buildDeleteOptions(), tokens.toArray(new String[0]));

        String tagName = cmd.getOptionValue("t");
        return new DeleteTagCommand(tagName);
    }

    private Command handleEdit(List<String> tokens) throws ParseException {
        CommandLine cmd = parser.parse(buildEditOptions(), tokens.toArray(new String[0]));

        String oldTagName = cmd.getOptionValue("o");
        String newTagName = cmd.getOptionValue("n");
        return new EditTagNameCommand(oldTagName, newTagName);
    }

    private Command handleHelp() {
        // Combine all actions
        String[] actionList = {EDIT_ACTION, DELETE_ACTION, LIST_ACTION};
        String[] headerList = {"Edit existing tags", "Delete tags", "List tags"};
        Options[] optionsList = {buildEditOptions(), buildDeleteOptions(), buildListOptions()};

        String helpMessage = formatHelpMessage("tag", actionList, headerList, optionsList);
        return new PrintHelpCommand(helpMessage);
    }

    private Command handleList(List<String> tokens) throws ParseException {
        CommandLine cmd = parser.parse(buildListOptions(), tokens.toArray(new String[0]));

        if (cmd.hasOption("t")) {
            String tagName = cmd.getOptionValue("t");
            return new ListCardsUnderTagCommand(tagName);
        } else {
            return new ListTagsCommand();
        }
    }

    private Command handleDeck(List<String> tokens) throws ParseException, InkaException {
        CommandLine cmd = parser.parse(buildDeckOptions(), tokens.toArray(new String[0]));

        String tagUUID = cmd.getOptionValue("t");
        String deckName = cmd.getOptionValue("d");

        return new AddTagToDeckCommand(deckName, tagUUID);
    }
}
