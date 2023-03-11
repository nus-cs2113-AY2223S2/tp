package seedu.Parser;

public class SplitCommand {
    static String[] splitCommand(int cut, String command) {
        String beforeCut = command.substring(0, cut-2);
        String afterCut = command.substring(cut+1);
        String[] content = new String[2];
        content[0] = beforeCut;
        content[1] = afterCut;

        return content;
    }
}
