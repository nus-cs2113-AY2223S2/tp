package chching.parser;

public class UnicodeChecker {
    public static final String VALID_CHARACTERS = "[\\P{ASCII}]";

    public static boolean isValidStringInput(String input) {
        for (char c : input.toCharArray()) {
            // System.out.println("testing " + c + ": ");
            // System.out.print(Character.UnicodeBlock.of(c) ==
            // Character.UnicodeBlock.BASIC_LATIN);
            // int i = c;
            // System.out.println(" " + i);
            if (!(Character.UnicodeBlock.of(c) == Character.UnicodeBlock.BASIC_LATIN)) {
                return false;
            }

        }
        // System.out.println("testing 观: ");
        // System.out.println(Character.UnicodeBlock.of('观') ==
        // Character.UnicodeBlock.BASIC_LATIN);
        // System.out.println("testing ✄: ");
        // System.out.println(Character.UnicodeBlock.of('✄') ==
        // Character.UnicodeBlock.BASIC_LATIN);
        return true;
        // String validString = input.replaceAll(VALID_CHARACTERS, "");
        // System.out.println("validstring: " + validString);
        // System.out.println("input: " + input);
        // return validString.equals(input);
    }
}
