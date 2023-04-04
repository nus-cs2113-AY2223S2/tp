package seedu.pettracker.commands;

import seedu.pettracker.exceptions.InvalidStatException;
import seedu.pettracker.exceptions.NonPositiveIntegerException;
import seedu.pettracker.exceptions.PetNotFoundException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.PetList;

public class AddStatCommand extends Command {
    protected String petName;
    protected String statName;
    protected String statValue;

    public AddStatCommand(String petName, String statName, String statValue) {
        super();
        this.petName = petName;
        this.statName = statName;
        this.statValue = statValue;
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
            PetList.addStat(petName, statName, statValue);
            PetList.savePetsToStorage(storage, ui);
            ui.addStatCommandMessage(petName, statName, statValue);
        } catch (NonPositiveIntegerException e) {
            ui.printIntegerNotPositiveMessage();
        } catch (NumberFormatException e) {
            ui.printNonIntegerMessage();
        } catch (InvalidStatException e) {
            ui.printInvalidStatMessage();
        } catch (PetNotFoundException e) {
            ui.petNotFoundMessage();
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
