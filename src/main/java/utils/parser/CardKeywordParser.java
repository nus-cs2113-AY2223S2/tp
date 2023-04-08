package utils.parser;

import java.util.List;
import model.Card;
import model.CardSelector;
import model.TagSelector;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import utils.command.AddCardCommand;
import utils.command.AddCardToDeckCommand;
import utils.command.AddCardToTagCommand;
import utils.command.Command;
import utils.command.DeleteCardCommand;
import utils.command.ListCardCommand;
import utils.command.PrintHelpCommand;
import utils.command.RemoveTagFromCardCommand;
import utils.command.ViewCardCommand;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.exceptions.UnrecognizedCommandException;

public class CardKeywordParser extends KeywordParser {

    public static final String CARD_MODEL = "card";
    public static final String ADD_ACTION = "add";
    public static final String DELETE_ACTION = "delete";
    public static final String HELP_ACTION = "help";
    public static final String LIST_ACTION = "list";
    public static final String TAG_ACTION = "tag";

    public static final String UNTAG_ACTION = "untag";
    public static final String VIEW_ACTION = "view";
    public static final String DECK_ACTION = "deck";

    @Override
    protected Command handleAction(String action, List<String> tokens) throws ParseException, InkaException {
        switch (action) {
        case ADD_ACTION:
            return handleAdd(tokens);
        case DELETE_ACTION:
            return handleDelete(tokens);
        case DECK_ACTION:
            return handleDeck(tokens);
        case HELP_ACTION:
            return handleHelp(tokens);
        case LIST_ACTION:
            return handleList(tokens);
        case TAG_ACTION:
            return handleTag(tokens);
        case UNTAG_ACTION:
            return handleUntag(tokens);
        case VIEW_ACTION:
            return handleView(tokens);
        default:
            throw new UnrecognizedCommandException();
        }
    }

    private Command handleAdd(List<String> tokens) throws ParseException, InvalidSyntaxException {

        if (tokens.size() != 2) {
            throw InvalidSyntaxException.buildTooManyTokensMessage();
        }

        Options addOptions = new OptionsBuilder(CARD_MODEL, ADD_ACTION).buildOptions();
        CommandLine cmd = parseUsingOptions(addOptions, tokens);

        String question = String.join(" ", cmd.getOptionValues("q"));
        String answer = String.join(" ", cmd.getOptionValues("a"));
        Card card = new Card(question, answer);

        return new AddCardCommand(card);
    }

    private Command handleDelete(List<String> tokens) throws ParseException, InkaException {

        if (tokens.size() != 1) {
            throw InvalidSyntaxException.buildTooManyTokensMessage();
        }

        Options deleteOptions = new OptionsBuilder(CARD_MODEL, DELETE_ACTION).buildOptions();
        CommandLine cmd = parseUsingOptions(deleteOptions, tokens);

        CardSelector cardSelector = getSelectedCard(cmd);

        return new DeleteCardCommand(cardSelector);
    }

    private Command handleHelp(List<String> tokens) throws InvalidSyntaxException {

        if (tokens.size() != 0) {
            throw InvalidSyntaxException.buildTooManyTokensMessage();
        }

        Options addOptions = new OptionsBuilder(CARD_MODEL, ADD_ACTION).buildOptions();
        Options deleteOptions = new OptionsBuilder(CARD_MODEL, DELETE_ACTION).buildOptions();
        Options tagOptions = new OptionsBuilder(CARD_MODEL, TAG_ACTION).buildOptions();
        Options untagOptions = new OptionsBuilder(CARD_MODEL, UNTAG_ACTION).buildOptions();
        Options viewOptions = new OptionsBuilder(CARD_MODEL, VIEW_ACTION).buildOptions();
        Options deckOptions = new OptionsBuilder(CARD_MODEL, DECK_ACTION).buildOptions();
        // Combine all action
        String[] actionList = {ADD_ACTION, DELETE_ACTION, LIST_ACTION, TAG_ACTION, UNTAG_ACTION, VIEW_ACTION,
                DECK_ACTION};
        String[] headerList = new String[]{"Adding cards", "Deleting cards", "List all cards", "Tagging cards",
                "Untagging cards",
                "View"
                        + " cards", "Adding cards to Deck"};
        Options[] optionsList = {addOptions, deleteOptions, new Options(), tagOptions, untagOptions, viewOptions,
                deckOptions};
        String helpMessage = formatHelpMessage("card", actionList, headerList, optionsList);

        return new PrintHelpCommand(helpMessage);
    }

    private Command handleList(List<String> tokens) throws InvalidSyntaxException {

        if (tokens.size() != 0) {
            throw InvalidSyntaxException.buildTooManyTokensMessage();
        }

        return new ListCardCommand();
    }

    private Command handleTag(List<String> tokens) throws ParseException, InkaException {

        if (tokens.size() != 2) {
            throw InvalidSyntaxException.buildTooManyTokensMessage();
        }

        Options tagOptions = new OptionsBuilder(CARD_MODEL, TAG_ACTION).buildOptions();
        CommandLine cmd = parseUsingOptions(tagOptions, tokens);

        CardSelector cardSelector = getSelectedCard(cmd);
        TagSelector tagSelector = getSelectedTag(cmd);
        return new AddCardToTagCommand(tagSelector, cardSelector);
    }

    private Command handleUntag(List<String> tokens) throws ParseException, InkaException {

        if (tokens.size() != 2) {
            throw InvalidSyntaxException.buildTooManyTokensMessage();
        }

        Options tagOptions = new OptionsBuilder(CARD_MODEL, TAG_ACTION).buildOptions();
        CommandLine cmd = parseUsingOptions(tagOptions, tokens);

        CardSelector cardSelector = getSelectedCard(cmd);
        TagSelector tagSelector = getSelectedTag(cmd);
        return new RemoveTagFromCardCommand(tagSelector, cardSelector);
    }

    private Command handleDeck(List<String> tokens) throws ParseException, InkaException {

        if (tokens.size() != 2) {
            throw InvalidSyntaxException.buildTooManyTokensMessage();
        }

        Options deckOptions = new OptionsBuilder(CARD_MODEL, DECK_ACTION).buildOptions();
        CommandLine cmd = parseUsingOptions(deckOptions, tokens);

        CardSelector cardSelector = getSelectedCard(cmd);
        String deckName = cmd.getOptionValue("d");
        return new AddCardToDeckCommand(deckName, cardSelector);
    }

    private Command handleView(List<String> tokens) throws ParseException, InkaException {

        if (tokens.size() != 1) {
            throw InvalidSyntaxException.buildTooManyTokensMessage();
        }

        Options viewOptions = new OptionsBuilder(CARD_MODEL, VIEW_ACTION).buildOptions();
        CommandLine cmd = parseUsingOptions(viewOptions, tokens);

        CardSelector cardSelector = getSelectedCard(cmd);
        return new ViewCardCommand(cardSelector);
    }
}
