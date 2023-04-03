package utils.parser;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;
import model.CardSelector;
import org.apache.commons.cli.AlreadySelectedException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import utils.command.Command;
import utils.exceptions.InkaException;
import utils.exceptions.InvalidSyntaxException;
import utils.exceptions.InvalidUUIDException;
import utils.exceptions.UnrecognizedCommandException;

/**
 * Abstract class for parsing keyword-specific commands
 */
public abstract class KeywordParser {

    protected static final String FLAG_CARD_UUID = "c";
    protected static final String FLAG_LONG_CARD_UUID = "card";
    protected static final String FLAG_CARD_INDEX = "i";
    protected static final String FLAG_LONG_CARD_INDEX = "index";

    protected static final int FORMAT_HELP_WIDTH = 200;
    protected static final int FORMAT_HELP_LEFT_PAD = 0;
    protected static final int FORMAT_HELP_DESC_PAD = 10;

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

    /**
     * Build an {@link Option} for selecting Card based on either {@link model.CardUUID} or card index from list
     *
     * @return Configured OptionGroup
     */
    protected static OptionGroup buildCardSelectOption() {
        // Mutually exclusive options
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setRequired(true);

        Option cardUuidOption = new Option(FLAG_CARD_UUID, FLAG_LONG_CARD_UUID, true, "card UUID");
        optionGroup.addOption(cardUuidOption);

        Option cardIndexOption = new Option(FLAG_CARD_INDEX, FLAG_LONG_CARD_INDEX, true, "card index");
        cardIndexOption.setType(Number.class);
        optionGroup.addOption(cardIndexOption);

        return optionGroup;
    }

    protected static CardSelector getSelectedCard(CommandLine cmd) throws ParseException, InvalidUUIDException {

        if (cmd.hasOption(FLAG_CARD_UUID)) {
            String cardUUID = cmd.getOptionValue(FLAG_CARD_UUID);
            return new CardSelector(cardUUID);
        } else if (cmd.hasOption(FLAG_CARD_INDEX)) {
            int index = ((Long) cmd.getParsedOptionValue(FLAG_CARD_INDEX)).intValue();
            return new CardSelector(index);
        }

        // Shouldn't be called
        assert false;
        return null;
    }

    /**
     * Formats mutually exclusive options as '-flag1 / -flag2 / -flag3'
     */
    private static String formatOptionGroup(OptionGroup optionGroup) {
        return optionGroup.getNames().stream().map(flag -> "-" + flag).collect(Collectors.joining(" / "));
    }

    /**
     * Formats option as '-flag'
     */
    private static String formatOption(Option option) {
        return "-" + option.getOpt();
    }

    public Command parseTokens(List<String> tokens) throws InkaException {
        if (tokens.size() == 0) {
            throw InvalidSyntaxException.buildGenericMessage();
        }

        String action = tokens.get(0);
        List<String> flags = tokens.subList(1, tokens.size());

        try {
            return handleAction(action, flags);
        } catch (ParseException ex) {
            throw handleException(ex);
        }
    }

    /**
     * Handler to convert {@link ParseException} to the relevant {@link InkaException}
     *
     * @param ex Exception thrown by Apache CLI parser
     * @return Converted custom exception
     */
    private InkaException handleException(ParseException ex) {

        // No pattern matching :(
        if (ex instanceof AlreadySelectedException) {
            return convertAlreadySelectedException((AlreadySelectedException) ex);
        } else if (ex instanceof MissingArgumentException) {
            return convertMissingArgumentException((MissingArgumentException) ex);
        } else if (ex instanceof MissingOptionException) {
            return convertMissingOptionException((MissingOptionException) ex);
        } else if (ex instanceof UnrecognizedOptionException) {
            return convertUnrecognizedOptionException((UnrecognizedOptionException) ex);
        } else {
            // Something else has somehow broken
            return InvalidSyntaxException.buildGenericMessage();
        }
    }

    /**
     * Converter for {@link AlreadySelectedException}
     *
     * @param ex Exception thrown when multiple mutually exclusive options in {@link OptionGroup} are selected
     * @return Converted custom exception
     */
    private InkaException convertAlreadySelectedException(AlreadySelectedException ex) {
        String formattedFlags = formatOptionGroup(ex.getOptionGroup());
        return InvalidSyntaxException.buildAlreadySelectedMessage(formattedFlags);
    }

    /**
     * Converter for {@link MissingArgumentException}
     *
     * @param ex Exception thrown when {@link Option} is missing an argument
     * @return Converted custom exception
     */
    private InkaException convertMissingArgumentException(MissingArgumentException ex) {
        String formattedFlag = formatOption(ex.getOption());
        return InvalidSyntaxException.buildMissingArgumentMessage(formattedFlag);
    }

    /**
     * Converter for {@link MissingOptionException}
     *
     * @param ex Exception thrown when a required {@link Option} is not present
     * @return Converted custom exception
     */
    private InkaException convertMissingOptionException(MissingOptionException ex) {
        // TODO: Cleanup
        return InvalidSyntaxException.buildGenericMessage();
        // Apache will return an untyped List containing either String or OptionGroup
//        List<String> missingOptions = new ArrayList<>();
//        for (Object obj : ex.getMissingOptions()) {
//            if (obj instanceof String) {
//                // Single option
//                missingOptions.add((String) obj);
//            } else if (obj instanceof OptionGroup) {
//                // Mutually exclusive options
//                OptionGroup optionGroup = (OptionGroup) obj;
//                String optionsInGroup = optionGroup.getOptions().stream().map(option -> "-" + option.getOpt())
//                        .collect(Collectors.joining("/"));
//                missingOptions.add(optionsInGroup);
//            }
//        }
//
//        String missingOptionsStr = String.join(", ", missingOptions);
//        return InvalidSyntaxException.buildMissingOptionMessage(missingOptionsStr);
    }

    /**
     * Converter for {@link UnrecognizedOptionException}
     *
     * @param ex Exception thrown when an unrecognized {@link Option} is present
     * @return Converted custom exception
     */
    private InkaException convertUnrecognizedOptionException(UnrecognizedOptionException ex) {
        // TODO
        return InvalidSyntaxException.buildGenericMessage();
    }

    /**
     * Derived classes should override this method and handle the action corresponding
     *
     * @param action Action of the corresponding keyword
     * @param tokens Rest of the user input
     * @return Parsed command
     * @throws ParseException               Syntax errors in command
     * @throws UnrecognizedCommandException Unrecognized action for this keyword
     */
    protected abstract Command handleAction(String action, List<String> tokens)
            throws ParseException, InkaException;

    /**
     * Combines help messages for all actions into a single message
     *
     * @param keyword     Associated keyword
     * @param actionList  Actions
     * @param headerList  Descriptions of actions
     * @param optionsList All action Options for this keyword
     * @return Formatted help string
     */
    protected String formatHelpMessage(String keyword, String[] actionList, String[] headerList,
            Options[] optionsList) {
        assert optionsList.length == headerList.length;
        assert optionsList.length == actionList.length;

        HelpFormatter formatter = new HelpFormatter();

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        for (int i = 0; i < optionsList.length; i++) {
            String syntax = "`" + keyword + " " + actionList[i] + "`";
            formatter.printHelp(printWriter, FORMAT_HELP_WIDTH, syntax, headerList[i], optionsList[i],
                    FORMAT_HELP_LEFT_PAD, FORMAT_HELP_DESC_PAD, "\n",
                    false);
        }

        // Fix for extra newlines at end
        return stringWriter.toString().trim() + System.lineSeparator();
    }
}
