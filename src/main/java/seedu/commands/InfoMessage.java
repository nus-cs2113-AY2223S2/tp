package seedu.commands;

import seedu.ui.Ui;

public class InfoMessage {

    public static String showLinesAfterExecution(){
        return System.lineSeparator() + Ui.showSeparator();
    }
}
