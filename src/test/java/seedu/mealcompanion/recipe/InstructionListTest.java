package seedu.mealcompanion.recipe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InstructionListTest {
    @Test
    void testToString_correctStringFormat() {
        InstructionList instructions = new InstructionList();
        instructions.add(new Instruction("first instruction"));
        instructions.add(new Instruction("second instruction"));
        String expectedString = "1. first instruction" + System.lineSeparator() +  "2. second instruction"
                + System.lineSeparator();
        assertEquals(instructions.toString(), expectedString);
    }
}
