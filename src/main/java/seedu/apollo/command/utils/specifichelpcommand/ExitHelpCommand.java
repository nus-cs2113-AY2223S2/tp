package seedu.apollo.command.utils.specifichelpcommand;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.command.utils.HelpCommand;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;
public class ExitHelpCommand extends HelpCommand {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) {
        ui.printExitHelpMessage();
    }
}
