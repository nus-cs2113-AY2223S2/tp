package seedu.mealcompanion.parser;

/**
 * Represents a command split into its space-separated tokens.
 * Allows for more performant (compared to substring) matching of individual tokens in a DFS manner.
 */
public class CommandTokens {
    /**
     * An array of tokens in the command
     */
    private final String[] tokens;
    /**
     * An index pointing to the <code>tokens</code> array representing the current token to match
     */
    private int position;

    public CommandTokens(String command) {
        this.tokens = command.split(" ");
        this.position = 0;
    }

    /**
     * Returns the total number of tokens.
     *
     * @return number of tokens.
     */
    public int numTokens() {
        return this.tokens.length;
    }

    /**
     * Returns the current token to match.
     * Returns <code>null</code> if all tokens have been advanced beyond.
     *
     * @return current token.
     */
    public String peek() {
        if (position >= this.numTokens()) {
            return null;
        }
        return tokens[position];
    }

    /**
     * Advances the current token to the next token.
     * May advance beyond the last token, in which case calls to <code>peek()</code> will return null.
     * If currently already beyond the last token, does nothing.
     */
    public void advance() {
        if (position >= this.numTokens()) {
            return;
        }
        position++;
    }

    /**
     * Rewinds the current token to the previous token.
     * If currently at the first token, does nothing,
     */
    public void rewind() {
        if (position == 0) {
            return;
        }
        position--;
    }
}
