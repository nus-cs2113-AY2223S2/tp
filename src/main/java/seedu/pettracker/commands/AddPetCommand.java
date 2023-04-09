package seedu.pettracker.commands;

import seedu.pettracker.exceptions.DuplicatePetException;
import seedu.pettracker.exceptions.EmptyPetNameException;
import seedu.pettracker.exceptions.InvalidPetNameException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.PetList;

public class AddPetCommand extends Command {
    protected String petName;

    public AddPetCommand(String commandArgs) {
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
            PetList.addPet(petName);
            PetList.savePetsToStorage(storage, ui);
            ui.addPetCommandMessage(petName);
        } catch (EmptyPetNameException e) {
            ui.petNameEmptyMessage();
        } catch (DuplicatePetException e) {
            ui.duplicatePetMessage();
        } catch (InvalidPetNameException e) {
            ui.printInvalidPetNameMessage();
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
