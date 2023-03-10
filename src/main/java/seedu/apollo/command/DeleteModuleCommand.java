package seedu.apollo.command;
import seedu.apollo.Storage;
import seedu.apollo.module.ModuleList;
import seedu.apollo.Ui;
/**
     * For {@code delmod} command.
     * Delete Module Command class that finds the module and removes it from the ModuleList 
     */
public class DeleteModuleCommand extends Command{

    protected String keyword;
    
    public void execute(ModuleList modules, Ui ui, Storage storage){
        modules.deleteModule(modules.findModule(keyword));
        //update storage later
        ui.printModuleDeleteMessage(keyword);
    }

}
