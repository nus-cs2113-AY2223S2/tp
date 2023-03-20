package utils.exceptions;

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

    public static InvalidSyntaxException buildMissingOptionMessage(String option) {
        return new InvalidSyntaxException("Looks like you're missing some flags: " + option);
    }

    public static InvalidSyntaxException buildMissingArgumentMessage(String option) {
        return new InvalidSyntaxException("Looks like you're missing an argument for \"" + option + "\"...");
    }
}
