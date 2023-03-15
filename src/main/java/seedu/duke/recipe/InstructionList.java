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

    public void add(Instruction instruction) {
        instructions.add(instruction);
    }

    public int size() {
        return instructions.size();
    }

    public Instruction get(int index) {
        return instructions.get(index);
    }
    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }
}
