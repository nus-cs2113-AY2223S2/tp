package seedu.mealcompanion.recipe;

//@@author ngyida
public class Instruction {
    private String instruction;

    /**
     * Construct the instruction using the specified string.
     *
     * @param instruction the string to set the instruction to
     */
    public Instruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Get the string representing the instruction.
     *
     * @return the string of the instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Set the instruction.
     *
     * @param instruction the string to set the instruction to
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

}
