package utils.parser;

import java.util.Arrays;
import java.util.List;
import utils.command.Command;
import utils.command.ExportCommand;
import utils.command.PrintHelpCommand;
import utils.command.TerminateCommand;
import utils.exceptions.InkaException;
import utils.exceptions.UnrecognizedCommandException;

public class Parser {

    public static final String CARD_KEYWORD = "card";
    public static final String TAG_KEYWORD = "tag";
    public static final String DECK_KEYWORD = "deck";
    public static final String GENERAL_KEYWORD = "general";
    public static final String EXPORT_KEYWORD = "export";
    public static final String EXIT_KEYWORD = "bye";
    public static final String HELP_KEYWORD = "help";

    // Keyword-specific parsers
    private final CardKeywordParser cardKeywordParser = new CardKeywordParser();
    private final TagKeywordParser tagKeywordParser = new TagKeywordParser();
    private final DeckKeywordParser deckKeywordParser = new DeckKeywordParser();

    private boolean isExecuting;

    public Parser() {
        this.isExecuting = true;
    }

    public boolean getIsExecuting() {
        return isExecuting;
    }

    public void setIsExecuting(boolean bool) {
        this.isExecuting = bool;
    }

    public Command parseCommand(String userInput) throws InkaException {
        /*
         *  User input is split into:
         *  [keyword] [action (optional)] [flag(s) (optional)]
         */

        List<String> userInputTokens = Arrays.asList(userInput.split("\\s+"));

        if (userInputTokens.size() == 0) {
            throw new UnrecognizedCommandException();
        }

        // Parse action and flags based on keyword
        String keyword = userInputTokens.get(0);
        List<String> optionTokens = userInputTokens.subList(1, userInputTokens.size());

        switch (keyword) {
        case DECK_KEYWORD:
            return deckKeywordParser.parseTokens(optionTokens);

        case CARD_KEYWORD:
            return cardKeywordParser.parseTokens(optionTokens);

        case TAG_KEYWORD:
            return tagKeywordParser.parseTokens(optionTokens);

        case HELP_KEYWORD:
            return new PrintHelpCommand(Parser.GENERAL_KEYWORD, null);

        case EXPORT_KEYWORD:
            return new ExportCommand();

        case EXIT_KEYWORD:
            this.setIsExecuting(false);
            return new TerminateCommand();

        default:
            throw new UnrecognizedCommandException();
        }
    }
}
