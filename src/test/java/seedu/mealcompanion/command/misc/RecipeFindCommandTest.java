package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.recipe.RecipeFindCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeFindCommandTest {

    private final ByteArrayOutputStream newOutStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(newOutStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    void execute() {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        new RecipeFindCommand("abc").execute(mealCompanionSession);
        String expectedOutput = "There are no recipes found!" + System.lineSeparator();
        assertEquals(expectedOutput, newOutStream.toString());

        newOutStream.reset();
        new RecipeFindCommand("123").execute(mealCompanionSession);
        expectedOutput = "There are no recipes found!" + System.lineSeparator();
        assertEquals(expectedOutput, newOutStream.toString());

        newOutStream.reset();
        new RecipeFindCommand("burger").execute(mealCompanionSession);
        expectedOutput = "These are the recipes found:" + System.lineSeparator()
                + "1. Beef Burger" + System.lineSeparator()
                + "3. Chicken Burger" + System.lineSeparator()
                + "4. Fish Burger" + System.lineSeparator();
        assertEquals(expectedOutput, newOutStream.toString());

        newOutStream.reset();
        new RecipeFindCommand("B").execute(mealCompanionSession);
        expectedOutput = "These are the recipes found:" + System.lineSeparator()
                + "1. Beef Burger" + System.lineSeparator()
                + "3. Chicken Burger" + System.lineSeparator()
                + "4. Fish Burger" + System.lineSeparator();
        assertEquals(expectedOutput, newOutStream.toString());
    }
}
