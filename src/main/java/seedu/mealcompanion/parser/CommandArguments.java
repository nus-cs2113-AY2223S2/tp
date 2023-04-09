package seedu.mealcompanion.parser;

import java.util.HashMap;

/**
 * Represents an optional positional argument and a set of optional flag argument
 * parsed from <code>CommandTokens</code>.
 */
// Adapted from Ethan's IP
public class CommandArguments {
    private String positionalArgument;
    private HashMap<String, String> flagArguments;

    /**
     * Parses command arguments from a <code>CommandTokens</code> object.
     * This assumes that the command portion of the tokens have already been consumed via <code>advance()</code>.
     *
     * @param remainingTokens the remaining tokens to be parsed as arguments.
     */
    public CommandArguments(CommandTokens remainingTokens) {
        this.positionalArgument = null;
        this.flagArguments = new HashMap<>();

        StringBuilder buffer = new StringBuilder();
        String currentFlagLabel = null;

        while (remainingTokens.peek() != null) {
            String currentToken = remainingTokens.peek();
            remainingTokens.advance();
            String possibleFlagLabel = getFlagLabel(currentToken);
            // If we encounter a continuation of the current argument
            if (possibleFlagLabel == null) {
                if (buffer.length() != 0) {
                    buffer.append(" ");
                }
                buffer.append(currentToken);
                continue;
            }
            // If we encounter a flag label
            // Check if we haven't encountered a flag label previously, then write into positionalArgument
            // Otherwise write the appropriate flag
            if (currentFlagLabel == null) {
                this.positionalArgument = buffer.toString();
            } else {
                flagArguments.put(currentFlagLabel, buffer.toString());
            }
            buffer = new StringBuilder();
            currentFlagLabel = possibleFlagLabel;
        }

        if (buffer.length() == 0) {
            return;
        }

        // Finish writing the current argument
        if (currentFlagLabel == null) {
            this.positionalArgument = buffer.toString();
        } else {
            flagArguments.put(currentFlagLabel, buffer.toString());
        }
    }

    private String getFlagLabel(String token) {
        if (!token.startsWith("/")) {
            return null;
        }
        return token.substring(1);
    }

    /**
     * Returns the positional argument.
     *
     * @return the positional argument of the parsed command.
     */
    public String getPositionalArgument() {
        return positionalArgument;
    }

    /**
     * Returns the flag argument corresponding to the flag provided.
     *
     * @param flag the name of the flag to return the corresponding value for.
     * @return the flag argument value of the passed flag.
     */
    public String getFlagArgument(String flag) {
        return this.flagArguments.get(flag);
    }
}
