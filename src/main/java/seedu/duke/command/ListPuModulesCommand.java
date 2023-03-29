package seedu.duke.command;

public class ListPuModulesCommand extends Command {

    private int univId;
    private String universityName;
    private String filter;

    public ListPuModulesCommand(int univId, String universityName) {
        this.univId = univId;
        this.universityName = universityName;
    }

    public ListPuModulesCommand(int univId, String universityName, String filter) {
        this.univId = univId;
        this.universityName = universityName;
        this.filter = filter;
    }

    @Override
    public void execute() {
        ui.printPUModListMessage(universityName); // Todo: exception this is not found, it is a
                                                  // empty string
        ui.printPUModules(univId, filter);
    }
}
