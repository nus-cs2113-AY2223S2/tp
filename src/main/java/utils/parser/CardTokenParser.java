package utils.parser;

import java.util.List;
import model.Card;
import org.apache.commons.cli.Option;
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
import utils.exceptions.DeleteRangeInvalid;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidCommandException;

public class CardTokenParser {

    private final static String CARD_ADD_ACTION = "add";
    private final static String CARD_DELETE_ACTION = "delete";
    private final static String CARD_LIST_ACTION = "list";
    private final static String CARD_TAG_ACTION = "tag";
    private final static String CARD_VIEW_ACTION = "view";
    private final Options addOptions = buildAddOptions();
    private final Options deleteOptions = buildDeleteOptions();
    private final Options tagOptions = buildTagOptions();
    private final Options viewOptions = buildViewOptions();

    public CardTokenParser() {

    }

    /**
     * Builds options for use with the {@link #CARD_ADD_ACTION} action
     */
    private static Options buildAddOptions() {
        Options options = new Options();
        //
        Option addOption = new Option(CARD_ADD_ACTION, true, "add a new card");
    }

    /**
     * Builds options for use with the {@link #CARD_LIST_ACTION} action
     */
    private static Options buildListOptions() {
        // TODO: Possibly change verbosity of output?
        return new Options();
    }

    /**
     * Builds options for use with the {@link #CARD_DELETE_ACTION} action
     */
    private static Options buildDeleteOptions() {

    }

    /**
     * Builds options for use with the {@link #CARD_VIEW_ACTION} action
     */
    private static Options buildViewOptions() {

    }

    private static Options buildTagOptions() {
        Options options = new Options();
        options.addRequiredOption("c", "card", true, "card UUID");
        options.addRequiredOption("t", "tag", true, "tag name");

        return options;
    }

    public Command parseTokens(List<String> tokens) throws InkaException {

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
                throw new InvalidCommandException();
        }

        // TODO:
        if (userCommandSplit[0].startsWith("card add")) {
            if (userCommandSplit.length < 3) {
                throw new AddGoneWrong();
            } else if (userCommandSplit[1].isBlank() && userCommandSplit[2].isBlank()) {
                assert userCommandSplit.length < 3 : "Questions and answers should be specified ";
                throw new AddEmptyQuestionAndAnswer();
            } else if (userCommandSplit[1].isBlank()) {
                throw new AddEmptyQuestion();
            } else if (userCommandSplit[2].isBlank()) {
                throw new AddEmptyAnswer();
            }
            String question = userCommandSplit[1];
            String answer = userCommandSplit[2];
            Card card = new Card(question, answer);
            return new AddCardCommand(card); // main command return
        } else if (userCommandSplit[0].startsWith("card view")) {
            String cardUUID = userCommandSplit[1];
            return new ViewCardCommand(cardUUID);
        } else if (userCommandSplit[0].startsWith("card delete")) {
            if (userCommandSplit.length == 1) {
                throw new DeleteMissingNumber();
            } else if (Integer.parseInt(userCommandSplit[1]) < 1
                    || Integer.parseInt(userCommandSplit[1]) > cardList.size()) {

                throw new DeleteRangeInvalid();
            }
            int deleteIndex = Integer.parseInt(userCommandSplit[1]);
            assert deleteIndex >= 0 : "deleteIndex should be a number";
            return new DeleteCardCommand(deleteIndex);
        } else if (userCommandSplit[0].startsWith("card tag")) {

        }
    }

    private Command handleAdd(List<String> tokens) {
    }

    private Command handleDelete(List<String> tokens) {
    }

    private Command handleList(List<String> tokens) {
        return new ListCardCommand();
    }

    private Command handleTag(List<String> tokens) {


        return new AddCardToTagCommand(tagName, cardUUID);
    }

    private Command handleView(List<String> tokens) {
    }
}
