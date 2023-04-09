package commands.deadline;

import manager.DeadlineManager;
import ui.TextUi;
public class FindDeadlineCommand extends commands.Command {
    public static final String COMMAND_WORD = "find_deadline";

    private String keyword;

    /**
     * Creates a find deadline command that finds deadlines matching the keyword.
     *
     * @param keyword the keyword to search for.
     */
    public FindDeadlineCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find deadline command.
     *
     * @param ui manages user input.
     */
    @Override
    public void execute(TextUi ui) {
        DeadlineManager.findDeadline(this.keyword, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
