package utils.command;

import java.io.PrintWriter;
import java.io.StringWriter;
import model.CardList;
import model.DeckList;
import model.TagList;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.storage.IDataStorage;

public class PrintHelpCommand extends Command {

    public static final String CARD_KEYWORD = "card";
    public static final String TAG_KEYWORD = "tag";
    public static final String DECK_KEYWORD = "deck";
    public static final String GENERAL_KEYWORD = "general";
    public static final String EXIT_KEYWORD = "bye";
    public static final String HELP_KEYWORD = "help";

    protected static final int FORMAT_HELP_WIDTH = 200;
    protected static final int FORMAT_HELP_LEFT_PAD = 0;
    protected static final int FORMAT_HELP_DESC_PAD = 10;

    private String model;
    private Options[] helpDetails;

    public PrintHelpCommand(String model, Options[] helpDetails) {
        this.model = model;
        this.helpDetails = helpDetails;
    }

    @Override
    public void execute(CardList cardList, TagList tagList, DeckList deckList, UserInterface ui, IDataStorage storage)
            throws InkaException {

        switch (model) {
        case CARD_KEYWORD:
            ui.printHelp(buildCardHelpMessage(helpDetails));
            break;
        case TAG_KEYWORD:
            ui.printHelp(buildTagHelpMessage(helpDetails));
            break;
        case DECK_KEYWORD:
            ui.printHelp(buildDeckHelpMessage(helpDetails));
            break;
        case GENERAL_KEYWORD:
            ui.printHelp(buildTopLevelHelpMessage());
            break;
        default:
            // Should be exhaustive
            assert false;
        }
    }

    private String buildCardHelpMessage(Options[] optionsList) {
        String[] syntaxList = {
            "card add -q QUESTION -a ANSWER",
            "card list",
            "card delete {-c CARD_UUID | -i CARD_INDEX}",
            "card tag {-c CARD_UUID | -i CARD_INDEX} {-t TAG_NAME | -x TAG_INDEX}",
            "card untag {-c CARD_UUID | -i CARD_INDEX} {-t TAG_NAME | -x TAG_INDEX}",
            "card deck {-c CARD_UUID | -i CARD_INDEX} -d DECK_NAME",
            "card view {-c CARD_UUID | -i CARD_INDEX}"
        };
        String[] headerList = {
            "Adding cards",
            "List all cards",
            "Deleting cards",
            "Tagging cards",
            "Untagging cards",
            "Adding cards to deck",
            "View cards"
        };
        String helpMessage = formatHelpMessage(syntaxList, headerList, optionsList);
        return helpMessage;
    }

    private String buildTagHelpMessage(Options[] optionsList) {
        String[] syntaxList = {
            "tag edit -o OLD_TAG_NAME -n NEW_TAG_NAME",
            "tag delete {-t TAG_NAME | -x TAG_INDEX}",
            "tag list [-t TAG_NAME | -x TAG_INDEX]",
            "tag deck -d DECK_NAME {-t TAG_NAME | -x TAG_INDEX}"
        };
        String[] headerList = {
            "Edit existing tags",
            "Delete tags",
            "List tags",
            "Adding tag to deck"
        };
        String helpMessage = formatHelpMessage(syntaxList, headerList, optionsList);
        return helpMessage;
    }

    private String buildDeckHelpMessage(Options[] optionsList) {
        String[] syntaxList = {
            "deck edit -o OLD_DECK_NAME -n NEW_DECK_NAME",
            "deck delete -d DECK_NAME [{-c CARD_UUID | -i CARD_INDEX} | {-t TAG_NAME | -x TAG_INDEX}]",
            "deck list [-d DECK_NAME]",
            "deck run"
        };
        String[] headerList = {
            "Edit existing decks",
            "Delete decks",
            "List decks",
            "Run a deck"
        };
        String helpMessage = formatHelpMessage(syntaxList, headerList, optionsList);
        return helpMessage;
    }

    protected String formatHelpMessage(String[] syntaxList, String[] headerList, Options[] optionsList) {
        assert optionsList.length == headerList.length;
        assert optionsList.length == syntaxList.length;

        HelpFormatter formatter = new HelpFormatter();

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        for (int i = 0; i < optionsList.length; i++) {
            formatter.printHelp(printWriter, FORMAT_HELP_WIDTH, syntaxList[i], headerList[i], optionsList[i],
                FORMAT_HELP_LEFT_PAD, FORMAT_HELP_DESC_PAD, "\n",
                false);
        }

        // Fix for extra newlines at end
        return stringWriter.toString().trim() + System.lineSeparator();
    }

    private String buildTopLevelHelpMessage() {
        String helpMessage = "";

        helpMessage +=
            "Welcome to Inka! Type " + HELP_KEYWORD + " at any time to show this message"
                + System.lineSeparator();
        helpMessage += "== Deck Management (run `<keyword> help` for more info) ===" + System.lineSeparator();
        helpMessage += formatHelpLine(CARD_KEYWORD, "Card-related functionality");
        helpMessage += formatHelpLine(TAG_KEYWORD, "Tag-related functionality");
        helpMessage += formatHelpLine(DECK_KEYWORD, "Deck-related functionality");
        helpMessage += "================== Miscellaneous Commands =================" + System.lineSeparator();
        helpMessage += formatHelpLine(EXIT_KEYWORD, "Exits Inka");

        return helpMessage;
    }

    private String formatHelpLine(String keyword, String description) {
        final int padLength = 12;

        String formatted = keyword;
        // Right-pad to PAD_LENGTH
        formatted += " ".repeat(padLength - formatted.length());
        formatted += " - ";
        formatted += description;
        formatted += System.lineSeparator();

        return formatted;
    }
}
