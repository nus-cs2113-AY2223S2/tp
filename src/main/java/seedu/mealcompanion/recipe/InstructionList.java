package seedu.mealcompanion.recipe;

import seedu.mealcompanion.ingredient.Ingredient;

import java.util.ArrayList;

//@@author ngyida
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

    @Override
    public String toString() {
        StringBuilder instructionListDetails = new StringBuilder();
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            String instructionDetail = instruction.getInstruction();
            instructionListDetails.append((i+1) + ". " + instructionDetail + System.lineSeparator());
        }
        return String.valueOf(instructionListDetails);
    }
}
