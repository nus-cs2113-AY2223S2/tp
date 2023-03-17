package seedu.pocketpal.frontend.commands;

import seedu.pocketpal.backend.Backend;
import seedu.pocketpal.frontend.ui.UI;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    /**
     * Exit from the application.
     *
     * @param ui      UI to output action result
     * @param backend Backend to process requests
     */
    @Override
    public void execute(UI ui, Backend backend) {
    }
}
