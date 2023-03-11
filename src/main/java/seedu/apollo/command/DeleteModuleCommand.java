package seedu.apollo.command;
import seedu.apollo.Storage;
import seedu.apollo.exception.ModuleNotFoundException;
import seedu.apollo.module.Module;
import seedu.apollo.module.ModuleList;
import seedu.apollo.Ui;
import seedu.apollo.task.TaskList;

import java.io.IOException;

/**
     * For {@code delmod} command.
     * Delete Module Command class that finds the module using moduleCode and removes it from the ModuleList
     */
public class DeleteModuleCommand extends Command{

    protected String moduleCode;
    public DeleteModuleCommand(String moduleCode){
        this.moduleCode = moduleCode;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList){
        try {
            Module toDelete = moduleList.findModule(moduleCode);
            if (toDelete == null) {
                throw new ModuleNotFoundException();
            }
            moduleList.remove(toDelete);
            //update storage later
            ui.printModuleDeleteMessage(moduleCode);
            storage.updateModule(moduleList);
        } catch (ModuleNotFoundException e) {
            ui.printUnsuccessfulModuleDelete(moduleCode);
        } catch (IOException e) {
            ui.printErrorForIO();
        }
    }

}
