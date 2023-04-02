package seedu.pettracker.commands;

import seedu.pettracker.exceptions.InvalidStatException;
import seedu.pettracker.exceptions.NonPositiveIntegerException;
import seedu.pettracker.exceptions.PetNotFoundException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.PetList;

public class EditStatCommand extends Command {
    protected String petName;
    protected String statName;
    protected String statValue;

    public EditStatCommand(String commandArgs) {
        super();
        this.petName = parseArgs(commandArgs)[0];
        this.statName = parseArgs(commandArgs)[1];
        this.statValue = parseArgs(commandArgs)[2];
    }

    // TODO: Implement this method

    /**
     * Executes the given command
     *
     * @param ui      Ui to do printing if required
     * @param storage Storage to save files if required
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        try {
            PetList.editPetStats(petName, statName, statValue);
            PetList.savePetsToStorage(storage, ui);
            ui.editStatCommandMessage(petName, statName, statValue);
        } catch (NonPositiveIntegerException e) {
            ui.integerNotPositiveMessage();
        } catch (NumberFormatException e) {
            ui.nonIntegerMessage();
        } catch (InvalidStatException e) {
            ui.invalidStatMessage();
        } catch (PetNotFoundException e) {
            ui.petNotFoundMessage();
        }
    }

    /**
     * Parses the arguments of the command
     *
     * @param commandArgs String containing the arguments of the command
     * @return String array containing the arguments of the command
     */
    @Override
    public String[] parseArgs(String commandArgs) {
        return commandArgs.split(" ", 3);
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
