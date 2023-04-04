package seedu.duke.commands;

import seedu.duke.utils.Ui;

public class HelpCommand {
    public void run() {
        System.out.println(Ui.LINE);
        String table = Ui.printTable();
        System.out.println(table);
        System.out.println(Ui.LINE);
    }
}
