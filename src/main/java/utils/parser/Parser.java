package utils.parser;

import java.util.Arrays;
import java.util.List;
import utils.command.Command;
import utils.command.ExportCommand;
import utils.command.TerminateCommand;
import utils.exceptions.InkaException;
import utils.exceptions.UnrecognizedCommandException;

public class Parser {

    /* Card-related tokens */
    private static final String CARD_KEYWORD = "card";

    /* Tag-related tokens */
    private static final String TAG_KEYWORD = "tag";

    /* Miscellaneous tokens */
    private static final String EXPORT_KEYWORD = "export";
    private static final String EXIT_KEYWORD = "bye";

    // Keyword-specific parsers
    private final CardTokenParser cardTokenParser = new CardTokenParser();
    private final TagTokenParser tagTokenParser = new TagTokenParser();

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
        case CARD_KEYWORD:
            return cardTokenParser.parseTokens(optionTokens);

        case TAG_KEYWORD:
            return tagTokenParser.parseToken(optionTokens);

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
