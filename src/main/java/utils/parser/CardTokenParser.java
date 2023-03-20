package utils.parser;

import java.util.List;
import java.util.stream.Collectors;
import model.Card;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import utils.command.AddCardCommand;
import utils.command.AddCardToTagCommand;
import utils.command.Command;
import utils.command.DeleteCardCommand;
import utils.command.ListCardCommand;
import utils.command.ViewCardCommand;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.exceptions.UnrecognizedCommandException;

public class CardTokenParser {

    private static final String CARD_ADD_ACTION = "add";
    private static final String CARD_DELETE_ACTION = "delete";
    private static final String CARD_LIST_ACTION = "list";
    private static final String CARD_TAG_ACTION = "tag";
    private static final String CARD_VIEW_ACTION = "view";

    private DefaultParser parser;

    public CardTokenParser() {
        this.parser = new DefaultParser(false);
    }

    private static Options buildAddOptions() {
        Options options = new Options();

        Option questionOption = new Option("q", "question", true, "card question");
        questionOption.setRequired(true);
        questionOption.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(questionOption);

        Option answerOption = new Option("a", "answer", true, "card answer");
        answerOption.setRequired(true);
        answerOption.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(answerOption);

        return options;
    }

    private static Options buildDeleteOptions() {
        Options options = new Options();

        Option indexOption = new Option("i", "index", true, "card index");
        indexOption.setRequired(true);
        indexOption.setType(Number.class);
        options.addOption(indexOption);

        return options;
    }

    private static Options buildTagOptions() {
        Options options = new Options();
        options.addRequiredOption("c", "card", true, "card UUID");
        options.addRequiredOption("t", "tag", true, "tag name");

        return options;
    }

    private static Options buildViewOptions() {
        Options options = new Options();
        options.addRequiredOption("c", "card", true, "card UUID");

        return options;
    }

    @SuppressWarnings("unchecked") // Safe, CLI library just returns List instead of List<String>
    public Command parseTokens(List<String> tokens) throws InkaException {
        if (tokens.size() == 0) {
            throw InvalidSyntaxException.buildGenericMessage();
        }

        String action = tokens.get(0);
        List<String> flags = tokens.subList(1, tokens.size());

        try {
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

    private Command handleAdd(List<String> tokens) throws ParseException {
        CommandLine cmd = parser.parse(buildAddOptions(), tokens.toArray(new String[0]));

        String question = String.join(" ", cmd.getOptionValues("q"));
        String answer = String.join(" ", cmd.getOptionValues("a"));
        Card card = new Card(question, answer);

        return new AddCardCommand(card);
    }

    private Command handleDelete(List<String> tokens) throws ParseException {
        CommandLine cmd = parser.parse(buildDeleteOptions(), tokens.toArray(new String[0]));

        int deleteIndex = ((Number) cmd.getParsedOptionValue("i")).intValue();

        return new DeleteCardCommand(deleteIndex);
    }

    private Command handleList(List<String> tokens) {
        return new ListCardCommand();
    }

    private Command handleTag(List<String> tokens) throws ParseException, InkaException {
        CommandLine cmd = parser.parse(buildTagOptions(), tokens.toArray(new String[0]));

        String cardUUID = cmd.getOptionValue("c");
        String tagName = cmd.getOptionValue("t");

        return new AddCardToTagCommand(tagName, cardUUID);
    }

    private Command handleView(List<String> tokens) throws ParseException, InkaException {
        CommandLine cmd = parser.parse(buildViewOptions(), tokens.toArray(new String[0]));

        String cardUUID = cmd.getOptionValue("c");
        return new ViewCardCommand(cardUUID);
    }

}
