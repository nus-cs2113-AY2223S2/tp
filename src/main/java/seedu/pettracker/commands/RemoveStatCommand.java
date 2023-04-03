package seedu.pettracker.commands;

import seedu.pettracker.exceptions.InvalidStatException;
import seedu.pettracker.exceptions.PetNotFoundException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.PetList;

public class RemoveStatCommand extends Command {
    protected String petName;
    protected String statName;

    public RemoveStatCommand(String commandArgs) {
        super();
        this.petName = parseArgs(commandArgs)[0];
        this.statName = parseArgs(commandArgs)[1];
    }

    /**
     * Executes the given command
     *
     * @param ui      Ui to do printing if required
     * @param storage Storage to save files if required
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        try {
            PetList.removeStat(petName, statName);
            PetList.savePetsToStorage(storage, ui);
            ui.removeStatCommandMessage(petName, statName);
        } catch (InvalidStatException e) {
            ui.invalidStatMessage();
        } catch (PetNotFoundException e) {
            ui.petNotFoundMessage();
        }
    }

    // TODO: Implement this method
    @Override
    public String[] parseArgs(String commandArgs) {
        return commandArgs.split(" ", 2);
    }

    /**
     * Sets isExit to be true to exit the program
     *
     * @return isExit boolean value for program to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
