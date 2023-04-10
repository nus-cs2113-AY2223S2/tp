package seedu.pettracker.commands;

import seedu.pettracker.exceptions.PetNotFoundException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.PetList;

public class RemovePetCommand extends Command {
    protected String petName;

    public RemovePetCommand(String commandArgs) {
        super();
        this.petName = commandArgs;
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
            PetList.removePet(petName);
            PetList.savePetsToStorage(storage, ui);
            ui.removePetCommandMessage(petName);
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
