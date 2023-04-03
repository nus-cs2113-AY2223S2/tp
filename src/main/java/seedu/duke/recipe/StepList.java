package seedu.duke.recipe;

import java.util.ArrayList;
import seedu.duke.ui.UI;
import seedu.duke.ui.StringLib;

public class StepList {
    protected ArrayList<Step> stepList;
    protected int currStepNumber;

    /**
     * Class constructor without arguments.
     */
    public StepList() {
        stepList = new ArrayList<>();
        currStepNumber = 0;
    }
    /**
     * Class constructor with argument. An existing array list of steps
     * is used as the argument
     *
     * @param stepList - list of all steps in the recipe.
     */
    public StepList(ArrayList<Step> stepList) {
        this.stepList = stepList;
        currStepNumber = stepList.size();
    }
    private void addStep(Step step) {
        stepList.add(step);
        currStepNumber++;
        assert (currStepNumber == stepList.size());
    }
    private void removeStep(int stepIndex) {
        stepList.remove(stepIndex - 1);
        currStepNumber--;
        assert (currStepNumber == stepList.size());
    }
    public void editStep(int stepIndex, UI ui) {
        System.out.println(StringLib.ENTER_STEP_DESCRIPTION);
        String description = ui.readCommand();
        Step newStep = new Step(description);
        stepList.set(stepIndex, newStep);
        System.out.println(StringLib.STEP_EDIT_SUCCESS);
        System.out.print((stepIndex + 1) + ". ");
        System.out.println(stepList.get(stepIndex).toString());
    }

    public void editStep(int stepIndex, String description) {
        Step newStep = new Step(description);
        stepList.set(stepIndex, newStep);
        System.out.println(StringLib.STEP_EDIT_SUCCESS);
        System.out.print((stepIndex + 1) + ". ");
        System.out.println(stepList.get(stepIndex).toString());
    }
    public void showFullStepList() {
        System.out.println("There are " + currStepNumber + " steps in the list");
        for (int i = 0; i < currStepNumber; i++) {
            System.out.println((i + 1) + ". " + stepList.get(i).getStep());
        }
    }
    public void showStepByStep(UI ui) {
        String input;
        for (int i = 0; i < currStepNumber; i++) {
            System.out.println((i + 1) + ". " + stepList.get(i).getStep());
            if (ui.readCommand().equals(StringLib.STEP_VIEW_QUIT_KEYWORD)) {
                return;
            }
        }
    }
    public void showStepList(UI ui) {
        if (currStepNumber == 0) {
            System.out.println(StringLib.RECIPE_NO_STEPS);
            return;
        }
        System.out.println("There are " + currStepNumber + " steps in the list");
        System.out.println(StringLib.STEPBYSTEP_PROMPT);
        String input = ui.readCommand();
        if (input.toLowerCase().equals("yes")) {
            System.out.println(StringLib.STEPBYSTEP_EARLY_TERMINATION_PROMPT);
            showStepByStep(ui);
        } else {
            showFullStepList();
        }
    }

    public int getCurrStepNumber() {
        return currStepNumber;
    }
    public ArrayList<Step> getList() {
        return stepList;
    }

}
