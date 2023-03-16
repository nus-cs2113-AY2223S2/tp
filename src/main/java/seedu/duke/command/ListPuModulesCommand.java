package seedu.duke.command;

import seedu.duke.Module;

import java.util.ArrayList;

public class ListPuModulesCommand extends Command {

    private int univId;
    private String universityName;

    public ListPuModulesCommand(int univId, String universityName) {
        this.univId = univId;
        this.universityName = universityName;
    }
    @Override
    public void execute() {
        ui.printPUModules(univId);
        ui.printPUModListMessage(universityName);  //Todo: exception this is not found, it is a empty string
    }
}
