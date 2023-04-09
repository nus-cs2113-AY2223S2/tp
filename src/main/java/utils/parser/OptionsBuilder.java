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
    private String keyword;

    public OptionsBuilder(String model, String action) {
        this.model = model;
        this.keyword = action;
    }

    public Options buildOptions() {
        switch (model) {
        case Parser.CARD_KEYWORD:
            switch (keyword) {
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
        case Parser.TAG_KEYWORD:
            switch (keyword) {
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
        case Parser.DECK_KEYWORD:
            switch (keyword) {
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
        options.addOptionGroup(buildCardSelectOption());
        options.addOptionGroup(buildTagSelectOption());
        return options;
    }

    public static Options buildUntagOptions() {
        Options options = new Options();
        options.addOptionGroup(buildCardSelectOption());
        options.addOptionGroup(buildTagSelectOption());
        return options;
    }

    public static Options buildViewOptions() {
        Options options = new Options();
        options.addOptionGroup(buildCardSelectOption());

        return options;
    }

    public static Options buildDeleteOptions(String model) {
        Options options = new Options();
        switch (model) {
        case Parser.CARD_KEYWORD:
            options.addOptionGroup(buildCardSelectOption());
            break;
        case Parser.TAG_KEYWORD:
            options.addOptionGroup(buildTagSelectOption());
            break;
        case Parser.DECK_KEYWORD:
            options.addRequiredOption(FLAG_DECK, FLAG_LONG_DECK, true, "deck name");
            options.addOption(FLAG_CARD, FLAG_LONG_CARD, true, "card name (optional)");
            options.addOption(FLAG_TAG, FLAG_LONG_TAG, true, "tag name (optional)");
            break;
        default:
        }
        return options;
    }

    public static Options buildDeckOptions(String model) {
        Options options = new Options();
        switch (model) {
        case Parser.CARD_KEYWORD:
            options.addOptionGroup(buildCardSelectOption());
            break;
        case Parser.TAG_KEYWORD:
            options.addOptionGroup(buildTagSelectOption());
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
            options.addOption(FLAG_TAG, FLAG_LONG_TAG, true, "tag UUID or name (optional)");
            options.addOption(FLAG_TAG_INDEX, FLAG_LONG_TAG_INDEX, true, "tag index (optional)");
            break;
        case Parser.DECK_KEYWORD:
            options.addOption(FLAG_CARD, FLAG_LONG_CARD, true, "deck name to list cards from (optional)");
            options.addOption(FLAG_TAG, FLAG_LONG_TAG, true, "deck name to list tags from (optional)");
            break;
        default:
            return null;
        }
        return options;
    }

    private static Options buildRunOptions() {
        Options options = new Options();
        options.addRequiredOption(FLAG_DECK, FLAG_LONG_DECK,true, "deck to review");
        return options;
    }

    /**
     * Build an {@link Option} for selecting Card based on either {@link model.CardUUID} or card index from list
     *
     * @return Configured OptionGroup
     */
    protected static OptionGroup buildCardSelectOption() {
        // Mutually exclusive options
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setRequired(true);

        Option cardUuidOption = new Option(FLAG_CARD, FLAG_LONG_CARD, true, "card UUID");
        optionGroup.addOption(cardUuidOption);

        Option cardIndexOption = new Option(FLAG_CARD_INDEX, FLAG_LONG_CARD_INDEX, true, "card index");
        cardIndexOption.setType(Number.class);
        optionGroup.addOption(cardIndexOption);

        return optionGroup;
    }

    protected static OptionGroup buildTagSelectOption() {
        // Mutually exclusive options
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setRequired(true);

        Option tagStringOption = new Option(FLAG_TAG, FLAG_LONG_TAG, true, "tag UUID or name");
        optionGroup.addOption(tagStringOption);

        Option tagIndexOption = new Option(FLAG_TAG_INDEX, FLAG_LONG_TAG_INDEX, true, "tag index");
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
