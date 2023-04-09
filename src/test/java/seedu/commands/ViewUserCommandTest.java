package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.entities.CaloricIntake;
import seedu.exceptions.InvalidFieldNameException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewUserCommandTest {
    private final UserStorage userStorage = new UserStorage("./data/userData.csv");
    private final FoodStorage foodStorage = new FoodStorage();
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);
    private final GeneralUi ui = new GeneralUi();
    private final CaloricIntake meals = new CaloricIntake(mealStorage.getMealByDate(LocalDate.now()));
    @Test
    void viewUser_missingFieldName_expectMissingArgumentsException() throws LifeTrackerException {
        String commandWord = "view";
        String userInput = "view";
        ViewUserCommand command = new ViewUserCommand(commandWord, userInput);
        MissingArgumentsException thrown = assertThrows(MissingArgumentsException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedErrorMessage = "Oops! Missing argument [fieldName] for command view";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }

    @Test
    void viewUser_invalidFieldName_expectInvalidFieldNameException() throws LifeTrackerException{
        String commandWord = "view";
        String userInput = "view /nil";
        ViewUserCommand command = new ViewUserCommand(commandWord, userInput);
        InvalidFieldNameException thrown = assertThrows(InvalidFieldNameException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "Oops! Invalid field name /nil for command view";
        assertEquals(expectedMessage,thrown.getMessage());
    }
    @Test
    void viewUser_viewName_expectName() throws LifeTrackerException{
        String commandWord = "view";
        String userInput = "view /name";
        ViewUserCommand command = new ViewUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = userStorage.getUser().getName();
        assertTrue(output.contains(expectedMessage));
    }

    @Test
    void viewUser_viewWeight_expectWeight() throws LifeTrackerException{
        String commandWord = "view";
        String userInput = "view /weight";
        ViewUserCommand command = new ViewUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "Weight: " + userStorage.getUser().getWeight() + " kg";
        assertTrue(output.contains(expectedMessage));
    }

    @Test
    void viewUser_viewHeight_expectHeight() throws LifeTrackerException{
        String commandWord = "view";
        String userInput = "view /height";
        ViewUserCommand command = new ViewUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "Height: " + userStorage.getUser().getHeight() + " cm";
        assertTrue(output.contains(expectedMessage));
    }

    @Test
    void viewUser_viewAge_expectAge() throws LifeTrackerException{
        String commandWord = "view";
        String userInput = "view /age";
        ViewUserCommand command = new ViewUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "Age: " + userStorage.getUser().getAge() + " years old";
        assertTrue(output.contains(expectedMessage));
    }

    @Test
    void viewUser_viewGender_expectGender() throws LifeTrackerException{
        String commandWord = "view";
        String userInput = "view /gender";
        ViewUserCommand command = new ViewUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "Gender: " + userStorage.getUser().getGender();
        assertTrue(output.contains(expectedMessage));
    }

    @Test
    void viewUser_viewCaloricLimit_expectCaloricLimit() throws LifeTrackerException{
        String commandWord = "view";
        String userInput = "view /caloricLimit";
        ViewUserCommand command = new ViewUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "This is your daily caloric limit: " + System.lineSeparator() +
                + userStorage.getUser().getCaloricLimit() + " Kcal";
        assertTrue(output.contains(expectedMessage));
    }

    @Test
    void viewUser_viewCaloriesLeft_expectCaloriesLeft() throws LifeTrackerException{
        String commandWord = "view";
        String userInput = "view /caloriesLeft";
        ViewUserCommand command = new ViewUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "This is the amount of calories that " +
                "you can consume before exceeding your limit for today: " + System.lineSeparator() +
                + userStorage.getUser().getCaloriesLeft(meals.getTotalDailyCalories()) + " Kcal";
        assertTrue(output.contains(expectedMessage));
    }

    @Test
    void viewUser_viewTargetWeight_expectTargetWeight() throws LifeTrackerException{
        String commandWord = "view";
        String userInput = "view /targetWeight";
        ViewUserCommand command = new ViewUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "Target Weight: " + userStorage.getUser().getTargetWeight()+ " kg";
        assertTrue(output.contains(expectedMessage));
    }
}
