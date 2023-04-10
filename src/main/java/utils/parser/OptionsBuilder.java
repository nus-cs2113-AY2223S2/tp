package utils.parser;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

public class OptionsBuilder {

    // Flags
    protected static final String FLAG_CARD = "c";
    protected static final String FLAG_LONG_CARD = "card";
    protected static final String FLAG_DECK = "d";
    protected static final String FLAG_LONG_DECK = "deck";
    protected static final String FLAG_TAG = "t";
    protected static final String FLAG_LONG_TAG = "tag";
    protected static final String FLAG_CARD_INDEX = "i";
    protected static final String FLAG_LONG_CARD_INDEX = "index";
    protected static final String FLAG_TAG_INDEX = "x";
    protected static final String FLAG_LONG_TAG_INDEX = "tagindex";
    protected static final String FLAG_QUESTION = "q";
    protected static final String FLAG_LONG_QUESTION = "question";
    protected static final String FLAG_ANSWER = "a";
    protected static final String FLAG_LONG_ANSWER = "answer";

    private String model;
    private String action;

    public OptionsBuilder(String model, String action) {
        this.model = model;
        this.action = action;
    }

    public Options buildOptions() {
        switch (model) {
        case Parser.CARD_KEYWORD:
            return buildCardModelOptions(action);
        case Parser.TAG_KEYWORD:
            return buildTagModelOptions(action);
        case Parser.DECK_KEYWORD:
            return buildDeckModelOptions(action);
        default:
            // Should be exhaustive
            assert false;
            return null;
        }
    }

    private Options buildDeckModelOptions(String action) {
        switch (action) {
        case DeckKeywordParser.DELETE_ACTION:
            return buildDeleteOptions(Parser.DECK_KEYWORD);
        case DeckKeywordParser.EDIT_ACTION:
            return buildEditOptions(Parser.DECK_KEYWORD);
        case DeckKeywordParser.LIST_ACTION:
            return buildListOptions(Parser.DECK_KEYWORD);
        case DeckKeywordParser.RUN_ACTION:
            return buildRunOptions();
        default:
            // Should be exhaustive
            assert false;
            return null;
        }
    }

    private Options buildTagModelOptions(String action) {
        switch (action) {
        case TagKeywordParser.DELETE_ACTION:
            return buildDeleteOptions(Parser.TAG_KEYWORD);
        case TagKeywordParser.DECK_ACTION:
            return buildDeckOptions(Parser.TAG_KEYWORD);
        case TagKeywordParser.EDIT_ACTION:
            return buildEditOptions(Parser.TAG_KEYWORD);
        case TagKeywordParser.LIST_ACTION:
            return buildListOptions(Parser.TAG_KEYWORD);
        default:
            // Should be exhaustive
            assert false;
            return null;
        }
    }

    public static Options buildCardModelOptions(String action) {
        switch (action) {
        case CardKeywordParser.ADD_ACTION:
            return buildAddOptions();
        case CardKeywordParser.TAG_ACTION:
            return buildTagOptions();
        case CardKeywordParser.UNTAG_ACTION:
            return buildUntagOptions();
        case CardKeywordParser.VIEW_ACTION:
            return buildViewOptions();
        case CardKeywordParser.DELETE_ACTION:
            return buildDeleteOptions(Parser.CARD_KEYWORD);
        case CardKeywordParser.DECK_ACTION:
            return buildDeckOptions(Parser.CARD_KEYWORD);
        default:
            // Should be exhaustive
            assert false;
            return null;
        }
    }

    public static Options buildAddOptions() {
        Options options = new Options();

        Option questionOption = buildMultipleTokenOption(FLAG_QUESTION, FLAG_LONG_QUESTION, true, "card question",
                true);
        options.addOption(questionOption);

        Option answerOption = buildMultipleTokenOption(FLAG_ANSWER, FLAG_LONG_ANSWER, true, "card answer", true);
        options.addOption(answerOption);

        return options;
    }

    public static Options buildTagOptions() {
        Options options = new Options();
        options.addOptionGroup(buildCardSelectOption(true));
        options.addOptionGroup(buildTagSelectOption(true));
        return options;
    }

    public static Options buildUntagOptions() {
        Options options = new Options();
        options.addOptionGroup(buildCardSelectOption(true));
        options.addOptionGroup(buildTagSelectOption(true));
        return options;
    }

    public static Options buildViewOptions() {
        Options options = new Options();
        options.addOptionGroup(buildCardSelectOption(true));

        return options;
    }

    public static Options buildDeleteOptions(String model) {
        Options options = new Options();
        switch (model) {
        case Parser.CARD_KEYWORD:
            options.addOptionGroup(buildCardSelectOption(true));
            break;
        case Parser.TAG_KEYWORD:
            options.addOptionGroup(buildTagSelectOption(true));
            break;
        case Parser.DECK_KEYWORD:
            options.addRequiredOption(FLAG_DECK, FLAG_LONG_DECK, true, "deck name");
            options.addOptionGroup(buildCardSelectOption(false));
            options.addOptionGroup(buildTagSelectOption(false));
            break;
        default:
        }
        return options;
    }

    public static Options buildDeckOptions(String model) {
        Options options = new Options();
        switch (model) {
        case Parser.CARD_KEYWORD:
            options.addOptionGroup(buildCardSelectOption(true));
            break;
        case Parser.TAG_KEYWORD:
            options.addOptionGroup(buildTagSelectOption(true));
            break;
        default:
        }
        options.addRequiredOption(FLAG_DECK, FLAG_LONG_DECK, true, "deck name");
        return options;
    }

    private static Options buildEditOptions(String model) {

        Options options = new Options();
        switch (model) {
        case Parser.TAG_KEYWORD:
            options.addRequiredOption("o", "old", true, "Old tag name");
            Option newTag = buildMultipleTokenOption("n", "new", true, "New tag name", true);
            options.addOption(newTag);
            break;
        case Parser.DECK_KEYWORD:
            options.addRequiredOption("o", "old", true, "Old deck name");
            options.addRequiredOption("n", "new", true, "New deck name");
            break;
        default:
            return null;
        }
        return options;
    }

    private static Options buildListOptions(String model) {
        Options options = new Options();
        switch (model) {
        case Parser.TAG_KEYWORD:
            options.addOption(FLAG_TAG, FLAG_LONG_TAG, true, "tag name (optional)");
            options.addOption(FLAG_TAG_INDEX, FLAG_LONG_TAG_INDEX, true, "tag index (optional)");
            break;
        case Parser.DECK_KEYWORD:
            options.addOption(FLAG_DECK, FLAG_LONG_DECK, true, "deck name to list cards and tags from (optional)");
            break;
        default:
            return null;
        }
        return options;
    }

    private static Options buildRunOptions() {
        Options options = new Options();
        options.addRequiredOption(FLAG_DECK, FLAG_LONG_DECK, true, "name of the deck to review");
        return options;
    }

    /**
     * Build an {@link Option} for selecting Card based on either {@link model.CardUUID} or card index from list
     *
     * @return Configured OptionGroup
     */
    protected static OptionGroup buildCardSelectOption(boolean required) {
        // Mutually exclusive options
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setRequired(required);

        Option cardUuidOption = new Option(FLAG_CARD, FLAG_LONG_CARD, true, "card UUID" +
                (required ? "" : " (optional)"));
        optionGroup.addOption(cardUuidOption);

        Option cardIndexOption = new Option(FLAG_CARD_INDEX, FLAG_LONG_CARD_INDEX, true, "card index" +
                (required ? "" : " (optional)"));
        cardIndexOption.setType(Number.class);
        optionGroup.addOption(cardIndexOption);

        return optionGroup;
    }

    protected static OptionGroup buildTagSelectOption(boolean required) {
        // Mutually exclusive options
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setRequired(required);

        Option tagStringOption = new Option(FLAG_TAG, FLAG_LONG_TAG, true, "tag name" +
                (required ? "" : " (optional)"));
        optionGroup.addOption(tagStringOption);

        Option tagIndexOption = new Option(FLAG_TAG_INDEX, FLAG_LONG_TAG_INDEX, true, "tag index" +
                (required ? "" : " (optional)"));
        tagIndexOption.setType(Number.class);
        optionGroup.addOption(tagIndexOption);

        return optionGroup;
    }

    /**
     * Wrapper around {@link Option} constructor to set option to accept multiple tokens (whitespace-separated
     * arguments). The arguments to this option should then be obtained using
     * {@link org.apache.commons.cli.CommandLine#getOptionValues(char)}.
     *
     * @param option      See {@link Option#Option(String, String, boolean, String)}
     * @param longOption  See {@link Option#Option(String, String, boolean, String)}
     * @param hasArg      See {@link Option#Option(String, String, boolean, String)}
     * @param description See {@link Option#Option(String, String, boolean, String)}
     * @param required    If Option is a required option
     * @return Configured Option
     */
    protected static Option buildMultipleTokenOption(String option, String longOption, boolean hasArg,
            String description,
            boolean required) {
        Option opt = new Option(option, longOption, hasArg, description);
        opt.setArgs(Option.UNLIMITED_VALUES);
        opt.setRequired(required);

        return opt;
    }
}
