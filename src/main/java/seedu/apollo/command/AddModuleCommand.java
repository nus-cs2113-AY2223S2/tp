package seedu.apollo.command;

import seedu.apollo.Storage;
import seedu.apollo.Ui;
import seedu.apollo.exception.InvalidModule;
import seedu.apollo.module.Module;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;

import java.util.ArrayList;

public class AddModuleCommand extends Command {

    private Module module;

    public AddModuleCommand(String moduleCode, ArrayList<Module> allModules) throws InvalidModule {
        for (Module moduleData : allModules) {
            if (moduleData.getCode().toLowerCase().equals(moduleCode.toLowerCase())) {
                this.module = moduleData;
                return;
            }
        }

        throw new InvalidModule();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ModuleList moduleList) {
        if (module != null) {
            moduleList.addModule(module);
            ui.printAddModuleMessage(module);
        }
    }

}
