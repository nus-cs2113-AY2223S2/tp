// @@author leonghuenweng
package seedu.pocketpal.frontend.commands;

import seedu.pocketpal.backend.Backend;
import seedu.pocketpal.frontend.ui.UI;


public class HelpCommand extends Command {
    /**
     * Print the help command.
     *
     * @param ui      UI to output action result
     * @param backend Backend to process requests
     */
    @Override
    public void execute(UI ui, Backend backend) {
        ui.printHelp();
    }
}
// @@author
