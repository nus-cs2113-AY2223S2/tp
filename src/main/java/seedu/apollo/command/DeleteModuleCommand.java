package seedu.apollo.command;
import seedu.apollo.Storage;
import seedu.apollo.module.ModuleList;
import seedu.apollo.Ui;
/**
     * For {@code delmod} command.
     * Delete Module Command class that finds the module using moduleCode and removes it from the ModuleList
     */
public class DeleteModuleCommand extends Command{

    protected String moduleCode;


    public void execute(ModuleList modules, Ui ui, Storage storage){
        modules.deleteModule(modules.findModule(moduleCode));
        //update storage later
        ui.printModuleDeleteMessage(moduleCode);
    }

}
