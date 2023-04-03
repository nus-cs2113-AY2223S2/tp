package utils.exceptions;

import java.util.List;

/**
 * Provide custom error messages for exceptions thrown during parsing
 */
public class InvalidSyntaxException extends InkaException {

    private InvalidSyntaxException(String message) {
        super(message);
    }

    public static InvalidSyntaxException buildGenericMessage() {
        return new InvalidSyntaxException("That command looks weird... Did you enter it correctly?");
    }

    public static InvalidSyntaxException buildBadFormatMessage(String badInput) {
        return new InvalidSyntaxException("Your input \"" + badInput + "\" doesn't look right...");
    }

    /**
     * Custom error message when multiple mutually exclusive options are selected
     */
    public static InvalidSyntaxException buildAlreadySelectedMessage(String formattedFlags) {
        return new InvalidSyntaxException(
                "These flags are mutually exclusive! Please use only one at a time: " + formattedFlags);
    }

    /**
     * Custom error message when option is missing required argument
     */
    public static InvalidSyntaxException buildMissingArgumentMessage(String formattedFlag) {
        return new InvalidSyntaxException(
                "Looks like you're missing an argument for " + formattedFlag + "...");
    }

    /**
     * Custom error message when required options are missing
     */
    public static InvalidSyntaxException buildMissingOptionMessage(List<String> formattedFlags) {
        String message = "Looks like you're missing some flags:" + System.lineSeparator();
        for (String flag : formattedFlags) {
            message += "\t" + flag + System.lineSeparator();
        }

        return new InvalidSyntaxException(message);
    }

    /**
     * Custom error message for unrecognized option
     */
    public static InvalidSyntaxException buildUnrecognizedOptionMessage(String formattedFlag) {
        return new InvalidSyntaxException("Not sure what the " + formattedFlag + " flag means here...");
    }
}
