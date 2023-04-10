package seedu.duke.command;

public class ListPuCommand extends Command {

    /**
     * Executes ListPuCommand where it calls UI Class to print out list of Partner Universities and their Abbreviations.
     */
    @Override
    public void execute() {
        ui.printPUListMessage();
        ui.printPUList();
    }

}
