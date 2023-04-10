package seedu.pettracker.commands;

import seedu.pettracker.exceptions.InvalidStatException;
import seedu.pettracker.exceptions.PetNotFoundException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.PetList;

public class RemoveStatCommand extends Command {
    protected String petName;
    protected String statName;

    public RemoveStatCommand(String petName, String statName) {
        super();
        this.petName = petName;
        this.statName = statName;
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
            ui.printInvalidStatMessage();
        } catch (PetNotFoundException e) {
            ui.printPetNotFoundMessage();
        }
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
