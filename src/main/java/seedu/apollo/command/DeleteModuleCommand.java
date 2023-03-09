package seedu.apollo.command;
import seedu.apollo.Storage;
import seedu.apollo.module.ModuleList;
import seedu.apollo.Ui;

public class DeleteModuleCommand extends Command{

    protected String keyword;
    
    public void execute(ModuleList modules, Ui ui, Storage storage){
        modules.deleteModule(modules.findModule(keyword));
        //update storage later
        ui.printModuleDeleteMessage(keyword);
    }

}
