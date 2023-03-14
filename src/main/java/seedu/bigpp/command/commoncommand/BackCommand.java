package seedu.bigpp.command.commoncommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.ui.UI;

public class BackCommand extends Command {

    @Override
    public String executeCommand() {
        switch (UI.getUiState()) {
        case PCVIEWER:
            return "Cannot go back from PCViewer";

        case PCBUILDER:
            UI.setPCViewerMode();
            return "returned to PCViewer";

        default:
            return "";
        }
    }
}
