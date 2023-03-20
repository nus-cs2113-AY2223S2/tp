package utils.parser;

import java.util.List;
import utils.command.Command;
import utils.command.DeleteTagCommand;
import utils.command.ListCardsUnderTagCommand;
import utils.command.ListTagsCommand;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.exceptions.UnrecognizedCommandException;

public class TagTokenParser {

    private static final String TAG_LIST_ACTION = "add";
    private static final String TAG_DELETE_ACTION = "delete";

    public TagTokenParser() {

    }

    public Command parseToken(List<String> tokens) throws InkaException {
        if (tokens.size() == 0) {
            throw InvalidSyntaxException.buildGenericMessage();
        }

        String action = tokens.get(0);
        List<String> flags = tokens.subList(1, tokens.size());

        switch (action) {
        case TAG_DELETE_ACTION:
            return handleDelete(flags);
        case TAG_LIST_ACTION:
            return handleList(flags);
        default:
            throw new UnrecognizedCommandException();
        }
    }

    private Command handleList(List<String> flags) {
        if (flags.size() > 1) {
            String tagName = flags.get(0);
            return new ListCardsUnderTagCommand(tagName);
        } else {
            return new ListTagsCommand();
        }
    }

    private Command handleDelete(List<String> flags) throws InkaException {
        if (flags.size() != 1) {
            throw InvalidSyntaxException.buildGenericMessage();
        }

        String tagName = flags.get(0);
        return new DeleteTagCommand(tagName);
    }
}
