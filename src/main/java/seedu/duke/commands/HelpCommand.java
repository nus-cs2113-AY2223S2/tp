package seedu.duke.commands;

import seedu.duke.utils.Ui;

public class HelpCommand {
    public void run() {
        String table = Ui.printTable();
        System.out.println(table);
    }
}
