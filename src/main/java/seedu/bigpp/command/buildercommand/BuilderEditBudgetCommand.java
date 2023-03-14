package seedu.bigpp.command.buildercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.exceptions.builderexceptions.BuilderInvalidNumberBudgetException;
import seedu.bigpp.exceptions.builderexceptions.BuilderMissingBudgetException;
import seedu.bigpp.exceptions.builderexceptions.BuilderInvalidTypeBudgetException;
import seedu.bigpp.pc.PCList;
import seedu.bigpp.ui.UI;

public class BuilderEditBudgetCommand extends Command {

    public BuilderEditBudgetCommand(String arguments) {
        super.setArguments(arguments);
    }

    /**
     * Change the budget of the current PC that the builder is working on
     * 
     * @return the new budget of the PC
     */
    @Override
    public String executeCommand() throws PPException {
        String argument = super.getArguments();
        if (argument.equals("")) {
            throw new BuilderMissingBudgetException();
        }
        if (argument.matches(".*\\D.*")) {
            throw new BuilderInvalidTypeBudgetException();
        }

        int budget = Integer.parseInt(super.getArguments());

        if (budget <= 0) {
            throw new BuilderInvalidNumberBudgetException();
        }

        int pcIndex = UI.builderMenu.getPCIndex();
        PCList.getPC(pcIndex).setBudget(budget);
        return "Current build budget is now: " + budget;
    }
}
