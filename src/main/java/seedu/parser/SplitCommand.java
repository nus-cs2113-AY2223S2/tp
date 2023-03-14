package seedu.parser;

public class SplitCommand {
    public static final int SUBSTRING_OFFSET = 2;
    public static final int SIZE_OF_SPLIT_INPUT = 2;

    static String[] splitCommand(int splitIndex, String command) {
        int beforeCharacter = splitIndex - SUBSTRING_OFFSET;
        int afterCharacter = splitIndex + SUBSTRING_OFFSET;
        String beforeCut = command.substring(0, beforeCharacter);
        String afterCut = command.substring(afterCharacter);
        String[] content = new String[SIZE_OF_SPLIT_INPUT];
        content[0] = beforeCut;
        content[1] = afterCut;

        return content;
    }
}
