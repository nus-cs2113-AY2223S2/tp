package seedu.mealcompanion.recipe;

import java.util.ArrayList;

//@@author ngyida
public class InstructionList {
    private ArrayList<Instruction> instructions;

    public InstructionList() {
        instructions = new ArrayList<>();
    }

    /**
     * Get the ArrayList of Instructions.
     *
     * @return ArrayList of Instructions
     */
    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    /**
     * Add an instruction to InstructionList
     *
     * @param instruction the instruction to be added
     */
    public void add(Instruction instruction) {
        instructions.add(instruction);
    }

    /**
     * Return the number of instructions in InstructionList.
     *
     * @return the number of instructions
     */
    public int size() {
        return instructions.size();
    }

    /**
     * Get the instruction specified by index.
     *
     * @param index the index of the instruction to be retrieved
     * @return
     */
    public Instruction get(int index) {
        return instructions.get(index);
    }

    /**
     * Set the instructions in InstructionList.
     *
     * @param instructions the instructions to set to
     */
    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    /**
     * Get a formatted string of instructions with based-1 indexes.
     *
     * @return string of instructions
     */
    @Override
    public String toString() {
        StringBuilder instructionListDetails = new StringBuilder();
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            String instructionDetail = instruction.getInstruction();
            instructionListDetails.append((i + 1) + ". " + instructionDetail + System.lineSeparator());
        }
        return String.valueOf(instructionListDetails);
    }
}
