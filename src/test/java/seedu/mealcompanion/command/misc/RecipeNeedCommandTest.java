package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author jingyaaa
class RecipeNeedCommandTest {

    private final ByteArrayOutputStream newOutStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(newOutStream));
    }

    @Test
    void execute() throws MealCompanionException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();

        RecipeNeedCommand command = new RecipeNeedCommand("Cup of Water");
        command.execute(mealCompanionSession);
        String expectedOutput = "These are the ingredient(s) you are missing:"
                + System.lineSeparator()
                + "1. water (quantity: 300.0)"
                + System.lineSeparator();
        assertEquals(expectedOutput, newOutStream.toString());

        AddCommand addCommand = new AddCommand("water", "150.0");
        addCommand.execute(mealCompanionSession);
        newOutStream.reset();
        command.execute(mealCompanionSession);
        expectedOutput = "These are the ingredient(s) you are missing:"
                + System.lineSeparator()
                + "1. water (quantity: 150.0)"
                + System.lineSeparator();
        assertEquals(expectedOutput, newOutStream.toString());

        addCommand.execute(mealCompanionSession);
        newOutStream.reset();
        command.execute(mealCompanionSession);
        expectedOutput = "These are the ingredient(s) you are missing:"
                + System.lineSeparator()
                + "You have all the ingredients to make this recipe!"
                + System.lineSeparator();
        assertEquals(expectedOutput, newOutStream.toString());

    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }
}
