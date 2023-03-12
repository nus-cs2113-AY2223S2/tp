package seedu.duke.recipe;

import java.util.ArrayList;

public class InstructionList {
    private ArrayList<Instruction> instructions;

    public InstructionList() {
        instructions = new ArrayList<>();
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }
}
