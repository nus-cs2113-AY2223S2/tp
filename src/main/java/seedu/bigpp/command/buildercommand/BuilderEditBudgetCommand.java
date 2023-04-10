package seedu.bigpp.command.buildercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.ui.UI;
import seedu.bigpp.ui.UIState;

public class BuilderEditBudgetCommand extends Command {

    public BuilderEditBudgetCommand(String arguments) {
        super.setArguments(arguments);
    }

    /**
     * Change the budget of the current PC that the builder is working on
     * @return the new budget of the PC
     */
    @Override
    public String executeCommand(DataStorage dataStorage) throws PPException {
        assert UI.getUiState() == UIState.PCBUILDER : "UI state should be PCBUILDER";

        String argument = super.getArguments();
        if (argument.equals("")) {
            throw new PPException("Please enter a budget after the command");
        }
        if (argument.matches(".*\\D.*") && !argument.matches("-1")) {
            throw new PPException("Please enter a budget that is an integer");
        }

        int budget = 0;

        try {
            budget = Integer.parseInt(super.getArguments());
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a postive integer within 16 bits");
        }
        if (budget <= 0 && budget != -1) {
            throw new PPException("Please enter a budget that is greater than 0");
        }

        int pcIndex = UI.pcBuilderMenu.getPCIndex();
        float currentCost = dataStorage.pcList.get(pcIndex).getCost();

        if (budget != -1 && budget < currentCost) {
            throw new PPException(
                    "You have set a budget that is lower than the"
                            + " current cost of the build, please set a higher budget.");
        }

        dataStorage.pcList.get(pcIndex).setBudget(budget);
        return "Current build budget is now: " + dataStorage.pcList.get(pcIndex).getBudgetString();
    }
}
