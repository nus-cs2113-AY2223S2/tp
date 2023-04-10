package seedu.duke.commands;

import seedu.duke.utils.Ui;

public class HelpCommand {
    public void run() {
        Ui.printLine();
        String table = Ui.printTable();
        System.out.println(table);
        Ui.printLine();
    }
}
