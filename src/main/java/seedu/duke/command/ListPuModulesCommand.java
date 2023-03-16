package seedu.duke.command;

public class ListPuModulesCommand extends Command {

    private int univId;
    private String universityName;

    public ListPuModulesCommand(int univId, String universityName) {
        this.univId = univId;
        this.universityName = universityName;
    }

    @Override
    public void execute() {
        ui.printPUModListMessage(universityName);  //Todo: exception this is not found, it is a empty string
        ui.printPUModules(univId);
    }
}
