package seedu.duke.recipe;

import java.util.ArrayList;

public class InstructionList {
    private ArrayList<Instruction> instructions;

    public InstructionList() {
        instructions = new ArrayList<>();
    }

    public void add(Instruction instruction) {
        instructions.add(instruction);
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }
}
