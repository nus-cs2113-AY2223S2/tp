package utils.parser;

import java.util.List;
import model.Card;
import org.apache.commons.cli.Options;
import utils.command.AddCardCommand;
import utils.command.AddCardToTagCommand;
import utils.command.Command;
import utils.command.DeleteCardCommand;
import utils.command.ListCardCommand;
import utils.command.ViewCardCommand;
import utils.exceptions.AddEmptyAnswer;
import utils.exceptions.AddEmptyQuestion;
import utils.exceptions.AddEmptyQuestionAndAnswer;
import utils.exceptions.AddGoneWrong;
import utils.exceptions.DeleteMissingNumber;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.exceptions.UnrecognizedCommandException;

public class CardTokenParser {

    private static final String CARD_ADD_ACTION = "add";
    private static final String CARD_DELETE_ACTION = "delete";
    private static final String CARD_LIST_ACTION = "list";
    private static final String CARD_TAG_ACTION = "tag";
    private static final String CARD_VIEW_ACTION = "view";

    public CardTokenParser() {

    }

    private static Options buildTagOptions() {
        Options options = new Options();
        options.addRequiredOption("c", "card", true, "card UUID");
        options.addRequiredOption("t", "tag", true, "tag name");

        return options;
    }

    public Command parseTokens(List<String> tokens) throws InkaException {
        if (tokens.size() == 0) {
            throw new InvalidSyntaxException();
        }

        String action = tokens.get(0);
        List<String> flags = tokens.subList(1, tokens.size());

        switch (action) {
        case CARD_ADD_ACTION:
            return handleAdd(flags);
        case CARD_DELETE_ACTION:
            return handleDelete(flags);
        case CARD_LIST_ACTION:
            return handleList(flags);
        case CARD_TAG_ACTION:
            return handleTag(flags);
        case CARD_VIEW_ACTION:
            return handleView(flags);
        default:
            throw new UnrecognizedCommandException();
        }
    }

    private Command handleAdd(List<String> tokens) throws InkaException {
        if (tokens.size() != 2) {
            throw new AddGoneWrong();
        }

        boolean emptyQuestion = tokens.get(0).isBlank();
        boolean emptyAnswer = tokens.get(1).isBlank();

        if (emptyAnswer && emptyQuestion) {
            throw new AddEmptyQuestionAndAnswer();
        } else if (emptyQuestion) {
            throw new AddEmptyQuestion();
        } else if (emptyAnswer) {
            throw new AddEmptyAnswer();
        }

        String question = tokens.get(0).trim();
        String answer = tokens.get(1).trim();

        Card card = new Card(question, answer);
        return new AddCardCommand(card);
    }

    private Command handleDelete(List<String> tokens) throws InkaException {
        if (tokens.size() != 1) {
            throw new DeleteMissingNumber();
        }

        // Check for invalid index
        // TODO: Move OOB check elsewhere
        // TODO: Unhandled NumberFormatException
        int deleteIndex = Integer.parseInt(tokens.get(0));
        assert deleteIndex >= 0 : "deleteIndex should be a number";

        return new DeleteCardCommand(deleteIndex);
    }

    private Command handleList(List<String> tokens) {
        return new ListCardCommand();
    }

    private Command handleTag(List<String> tokens) throws InkaException {
        if (tokens.size() != 2) {
            throw new InvalidSyntaxException();
        }

        String cardUUID = tokens.get(0);
        String tagName = tokens.get(1);

        return new AddCardToTagCommand(tagName, cardUUID);
    }

    private Command handleView(List<String> tokens) throws InkaException {
        if (tokens.size() != 1) {
            throw new InvalidSyntaxException();
        }

        String cardUUID = tokens.get(0);
        return new ViewCardCommand(cardUUID);
    }
}
