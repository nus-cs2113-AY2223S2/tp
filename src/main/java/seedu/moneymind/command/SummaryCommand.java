package seedu.moneymind.command;

import seedu.moneymind.ui.Ui;

import static seedu.moneymind.string.Strings.SUMMARY_LIST;

public class SummaryCommand implements Command{
    /**
     * Shows the summary of commands.
     */
    @Override
    public void execute(Ui ui) {
        System.out.println(SUMMARY_LIST);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
