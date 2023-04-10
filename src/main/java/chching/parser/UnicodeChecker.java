package chching.parser;

/**
 * Models a class to check if the input is valid.
 *
 */

public class UnicodeChecker {
    /**
     * Method to check if the input is valid.
     *
     * @param input Input from users
     */
    public static boolean isValidStringInput(String input) {
        for (char c : input.toCharArray()) {
            if (!(Character.UnicodeBlock.of(c) == Character.UnicodeBlock.BASIC_LATIN)) {
                return false;
            }

        }
        return true;

    }
}
