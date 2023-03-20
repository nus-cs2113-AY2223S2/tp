package utils.parser;

import java.util.List;
import utils.command.Command;
import utils.command.DeleteTagCommand;
import utils.command.ListCardsUnderTagCommand;
import utils.command.ListTagsCommand;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidCommandException;

public class TagTokenParser {

    public TagTokenParser() {

    }

    public Command parseToken(List<String> tokens) throws InkaException {
        // TODO:
        if (userCommandSplit[0].startsWith("tag list")) {
            if (userCommandSplit.length > 1) {
                String tagName = userCommandSplit[1];
                return new ListCardsUnderTagCommand(tagName);
            } else {
                return new ListTagsCommand();
            }
        } else if (userCommandSplit[0].startsWith("tag delete")) {
            //please check for exception
            String tagName = userCommandSplit[1];
            return new DeleteTagCommand(tagName);
        }

        throw new InvalidCommandException();
    }
}
}
