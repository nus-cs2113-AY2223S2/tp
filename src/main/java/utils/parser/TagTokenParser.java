package utils.parser;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import utils.command.Command;
import utils.command.DeleteTagCommand;
import utils.command.ListCardsUnderTagCommand;
import utils.command.ListTagsCommand;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.exceptions.UnrecognizedCommandException;

public class TagTokenParser {

    private static final String TAG_LIST_ACTION = "list";
    private static final String TAG_DELETE_ACTION = "delete";

    private DefaultParser parser;

    public TagTokenParser() {
        this.parser = new DefaultParser(false);
    }

    private static Options buildDeleteOptions() {
        Options options = new Options();
        options.addRequiredOption("t", "tag", true, "tag name");

        return options;
    }

    private static Options buildListOptions() {
        Options options = new Options();
        options.addOption("t", "tag", true, "tag name");

        return options;
    }

    public Command parseToken(List<String> tokens) throws InkaException {
        if (tokens.size() == 0) {
            throw InvalidSyntaxException.buildGenericMessage();
        }

        String action = tokens.get(0);
        List<String> flags = tokens.subList(1, tokens.size());

        try {
            switch (action) {
            case TAG_DELETE_ACTION:
                return handleDelete(flags);
            case TAG_LIST_ACTION:
                return handleList(flags);
            default:
                throw new UnrecognizedCommandException();
            }
        } catch (MissingArgumentException e) {
            String missingArgumentOption = e.getOption().getArgName();
            throw InvalidSyntaxException.buildMissingArgumentMessage(missingArgumentOption);
        } catch (MissingOptionException e) {
            List<String> opts = e.getMissingOptions();
            String missingOptions = opts.stream().map(str -> "-" + str).collect(Collectors.joining(", "));
            throw InvalidSyntaxException.buildMissingOptionMessage(missingOptions);
        } catch (ParseException e) {
            throw InvalidSyntaxException.buildGenericMessage();
        }
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

    private Command handleDelete(List<String> tokens) throws ParseException {
        CommandLine cmd = parser.parse(buildDeleteOptions(), tokens.toArray(new String[0]));

        String tagName = cmd.getOptionValue("t");
        return new DeleteTagCommand(tagName);
    }
}
