package seedu.moneymind.command;

import seedu.moneymind.ui.Ui;

import static seedu.moneymind.string.Strings.INTRODUCTION_HELP_COMMAND;
import static seedu.moneymind.string.Strings.HELP_INSTRUCTION;
import static seedu.moneymind.string.Strings.SUMMARY_INSTRUCTION;
import static seedu.moneymind.string.Strings.CATEGORY_INSTRUCTION;
import static seedu.moneymind.string.Strings.EVENT_INSTRUCTION;
import static seedu.moneymind.string.Strings.VIEW_INSTRUCTION;
import static seedu.moneymind.string.Strings.EDIT_INSTRUCTION;
import static seedu.moneymind.string.Strings.DELETE_INSTRUCTION;
import static seedu.moneymind.string.Strings.SEARCH_INSTRUCTION;
import static seedu.moneymind.string.Strings.BYE_INSTRUCTION;

/**
 * Represents the help command.
 */
public class HelpCommand implements Command {

    @Override
    public void execute(Ui ui) {
        System.out.println(INTRODUCTION_HELP_COMMAND);
        System.out.println(HELP_INSTRUCTION);
        System.out.println(SUMMARY_INSTRUCTION);
        System.out.println(CATEGORY_INSTRUCTION);
        System.out.println(EVENT_INSTRUCTION);
        System.out.println(VIEW_INSTRUCTION);
        System.out.println(EDIT_INSTRUCTION);
        System.out.println(DELETE_INSTRUCTION);
        System.out.println(SEARCH_INSTRUCTION);
        System.out.println(BYE_INSTRUCTION);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
