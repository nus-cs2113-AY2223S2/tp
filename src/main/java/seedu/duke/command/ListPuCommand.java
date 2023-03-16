package seedu.duke.command;

public class ListPuCommand extends Command {
    @Override
    public void execute() {
        ui.printPUListMessage();
        ui.printPUList();
    }

}
