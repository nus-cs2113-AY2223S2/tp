package seedu.duke.recipe;

public class Step {
    protected String description;
    protected boolean isTimed;
    public Step(String inputDescription) {
        description = inputDescription;
    }
    public String getStepDescription() {
        return description;
    }
    @Override
    public String toString() {
        return description;
    }
}
