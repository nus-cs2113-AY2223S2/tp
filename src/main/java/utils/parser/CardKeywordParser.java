package utils.parser;

import java.util.List;
import model.Card;
import model.CardSelector;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import utils.command.AddCardCommand;
import utils.command.AddCardToDeckCommand;
import utils.command.AddCardToTagCommand;
import utils.command.Command;
import utils.command.DeleteCardCommand;
import utils.command.ListCardCommand;
import utils.command.PrintHelpCommand;
import utils.command.ViewCardCommand;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidUUIDException;
import utils.exceptions.UnrecognizedCommandException;

public class CardKeywordParser extends KeywordParser {

    public static final String ADD_ACTION = "add";
    public static final String DELETE_ACTION = "delete";
    public static final String HELP_ACTION = "help";
    public static final String LIST_ACTION = "list";
    public static final String TAG_ACTION = "tag";
    public static final String VIEW_ACTION = "view";

    public static final String DECK_ACTION = "deck";

    private DefaultParser parser;

    public CardKeywordParser() {
        this.parser = new DefaultParser(false);
    }

    private static Options buildAddOptions() {
        Options options = new Options();

        Option questionOption = buildMultipleTokenOption("q", "question", true, "card question", true);
        options.addOption(questionOption);

        Option answerOption = buildMultipleTokenOption("a", "answer", true, "card answer", true);
        options.addOption(answerOption);

        return options;
    }

    private static Options buildDeleteOptions() {
        Options options = new Options();
        options.addOptionGroup(buildCardSelectOption());

        return options;
    }

    private static Options buildTagOptions() {
        Options options = new Options();
        options.addOptionGroup(buildCardSelectOption());

        Option tag = buildMultipleTokenOption("t", "tag", true, "tag name", true);
        options.addOption(tag);

        return options;
    }

    private static Options buildDeckOptions() {
        Options options = new Options();
        options.addOptionGroup(buildCardSelectOption());
        options.addRequiredOption("d", "deck", true, "deck name");

        return options;
    }

    private static Options buildViewOptions() {
        Options options = new Options();
        options.addOptionGroup(buildCardSelectOption());

        return options;
    }

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
            return handleHelp();
        case LIST_ACTION:
            return handleList();
        case TAG_ACTION:
            return handleTag(tokens);
        case VIEW_ACTION:
            return handleView(tokens);
        default:
            throw new UnrecognizedCommandException();
        }
    }

    private Command handleAdd(List<String> tokens) throws ParseException {
        CommandLine cmd = parser.parse(buildAddOptions(), tokens.toArray(new String[0]));

        String question = String.join(" ", cmd.getOptionValues("q"));
        String answer = String.join(" ", cmd.getOptionValues("a"));
        Card card = new Card(question, answer);

        return new AddCardCommand(card);
    }

    private Command handleDelete(List<String> tokens) throws ParseException, InvalidUUIDException {
        CommandLine cmd = parser.parse(buildDeleteOptions(), tokens.toArray(new String[0]));
        CardSelector cardSelector = getSelectedCard(cmd);

        return new DeleteCardCommand(cardSelector);
    }

    //TODO: Fix issue here
    private Command handleHelp() {
        // Combine all action
        String[] actionList = {ADD_ACTION, DELETE_ACTION, LIST_ACTION, TAG_ACTION, VIEW_ACTION, DECK_ACTION};
        String[] headerList = new String[]{"Adding cards", "Deleting cards", "List all cards", "Tagging cards", "View"
                + " cards", "Adding cards to Deck"};
        Options[] optionsList = {
                buildAddOptions(), buildDeleteOptions(), new Options(), buildTagOptions(),
                buildViewOptions(), buildDeckOptions()
        };
        String helpMessage = formatHelpMessage("card", actionList, headerList, optionsList);
        return new PrintHelpCommand(helpMessage);
    }

    private Command handleList() {
        return new ListCardCommand();
    }

    private Command handleTag(List<String> tokens) throws ParseException, InkaException {
        CommandLine cmd = parser.parse(buildTagOptions(), tokens.toArray(new String[0]));
        CardSelector cardSelector = getSelectedCard(cmd);

        String[] tagNameTokens = cmd.getOptionValues("t");
        if (tagNameTokens.length > 1) {
            // Notify user
            String tagName = String.join("-", tagNameTokens);
            return new AddCardToTagCommand(tagName, cardSelector);
        } else {
            return new AddCardToTagCommand(tagNameTokens[0], cardSelector);
        }
    }

    private Command handleDeck(List<String> tokens) throws ParseException, InkaException {
        CommandLine cmd = parser.parse(buildDeckOptions(), tokens.toArray(new String[0]));
        CardSelector cardSelector = getSelectedCard(cmd);

        String[] deckTokenNames = cmd.getOptionValues("d");
        if(deckTokenNames.length>1) {
            String deckName = String.join("-", deckTokenNames);
            return new AddCardToDeckCommand(deckName, cardSelector);
        } else {
            return new AddCardToDeckCommand(deckTokenNames[0], cardSelector);
        }
    }

    private Command handleView(List<String> tokens) throws ParseException, InkaException {
        CommandLine cmd = parser.parse(buildViewOptions(), tokens.toArray(new String[0]));
        CardSelector cardSelector = getSelectedCard(cmd);

        return new ViewCardCommand(cardSelector);
    }
}
