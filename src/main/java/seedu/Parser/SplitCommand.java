package seedu.Parser;

public class SplitCommand {
    private static final int SUBSTRING_OFFSET = 2;
    public static final int SIZE_OF_SPLIT_INPUT = 2;

    static String[] splitCommand(int splitIndex, String command) {
        int lastCharacter = splitIndex - SUBSTRING_OFFSET;
        int firstCharacter = splitIndex + SUBSTRING_OFFSET;
        String beforeCut = command.substring(0, lastCharacter);
        String afterCut = command.substring(firstCharacter);
        String[] content = new String[SIZE_OF_SPLIT_INPUT];
        content[0] = beforeCut;
        content[1] = afterCut;

        return content;
    }
}
