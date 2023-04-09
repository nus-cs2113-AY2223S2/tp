package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.entities.CaloricIntake;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.exceptions.NegativeFieldInfoException;
import seedu.exceptions.InvalidFieldNameException;
import seedu.exceptions.InvalidFieldInfoFormatException;
import seedu.exceptions.ExtraArgumentsException;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class UpdateUserCommandTest {
    private final UserStorage userStorage = new UserStorage("./data/userData.csv");
    private final FoodStorage foodStorage = new FoodStorage();
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);
    private final GeneralUi ui = new GeneralUi();
    private final CaloricIntake meals = new CaloricIntake(mealStorage.getMealByDate(LocalDate.now()));

    @Test
    void updateUser_missingFieldToUpdate_expectMissingArgumentsException() throws LifeTrackerException {
        String commandWord = "update";
        String userInput = "update";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        MissingArgumentsException thrown = assertThrows(MissingArgumentsException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedErrorMessage = "Oops! Missing argument [user information field]/ [new information] " +
                "for command update";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }

    @Test
    void updateUser_invalidFieldName_expectInvalidFieldNameException() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /nil test";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        InvalidFieldNameException thrown = assertThrows(InvalidFieldNameException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "Oops! Invalid field name /nil for command update";
        assertEquals(expectedMessage,thrown.getMessage());
    }
    @Test
    void updateUser_extraArguments_expectExtraArgumentsException() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /nil test test";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        ExtraArgumentsException thrown = assertThrows(ExtraArgumentsException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "Oops! Too many arguments in your input!";
        assertEquals(expectedMessage,thrown.getMessage());
    }

    @Test
    void updateUser_updateNameAsNumbers_expectInvalidFieldInfoFormatException() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /name 123";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        InvalidFieldInfoFormatException thrown = assertThrows(InvalidFieldInfoFormatException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "123 is not of valid format for /name please try again!";
        assertEquals(expectedMessage,thrown.getMessage());
    }
    @Test
    void updateUser_updateName_expectSuccessfullyUpdatedMessage() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /name test";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "Successfully updated user settings!";
        assertTrue(output.contains(expectedMessage));
    }

    @Test
    void updateUser_updateWeightAsAlphabets_expectInvalidFieldInfoFormatException() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /weight abc";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        InvalidFieldInfoFormatException thrown = assertThrows(InvalidFieldInfoFormatException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "abc is not of valid format for /weight please try again!";
        assertEquals(expectedMessage,thrown.getMessage());
    }
    @Test
    void updateUser_updateWeightAsNegativeValue_expectNegativeFieldInfoException() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /weight -10";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        NegativeFieldInfoException thrown = assertThrows(NegativeFieldInfoException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "Oops! You cannot enter a negative value for /weight in command update";
        assertEquals(expectedMessage,thrown.getMessage());
    }
    @Test
    void updateUser_updateWeight_expectSuccessfullyUpdatedMessage() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /weight 78";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "Successfully updated user settings!";
        assertTrue(output.contains(expectedMessage));
    }
    @Test
    void updateUser_updateHeightAsAlphabets_expectInvalidFieldInfoFormatException() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /height abc";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        InvalidFieldInfoFormatException thrown = assertThrows(InvalidFieldInfoFormatException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "abc is not of valid format for /height please try again!";
        assertEquals(expectedMessage,thrown.getMessage());
    }
    @Test
    void updateUser_updateHeightAsNegativeValue_expectNegativeFieldInfoException() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /height -10";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        NegativeFieldInfoException thrown = assertThrows(NegativeFieldInfoException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "Oops! You cannot enter a negative value for /height in command update";
        assertEquals(expectedMessage,thrown.getMessage());
    }

    @Test
    void updateUser_updateHeight_expectSuccessfullyUpdatedMessage() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /height 178";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "Successfully updated user settings!";
        assertTrue(output.contains(expectedMessage));
    }
    @Test
    void updateUser_updateAgeAsAlphabets_expectInvalidFieldInfoFormatException() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /age abc";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        InvalidFieldInfoFormatException thrown = assertThrows(InvalidFieldInfoFormatException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "abc is not of valid format for /age please try again!";
        assertEquals(expectedMessage,thrown.getMessage());
    }
    @Test
    void updateUser_updateAgeAsNegativeValue_expectNegativeFieldInfoException() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /age -10";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        NegativeFieldInfoException thrown = assertThrows(NegativeFieldInfoException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "Oops! You cannot enter a negative value for /age in command update";
        assertEquals(expectedMessage,thrown.getMessage());
    }
    @Test
    void updateUser_updateAge_expectSuccessfullyUpdatedMessage() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /age 23";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "Successfully updated user settings!";
        assertTrue(output.contains(expectedMessage));
    }
    @Test
    void updateUser_updateGenderAsNumbers_expectInvalidFieldInfoFormatException() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /gender 123";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        InvalidFieldInfoFormatException thrown = assertThrows(InvalidFieldInfoFormatException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "123 is not of valid format for /gender please try again!";
        assertEquals(expectedMessage,thrown.getMessage());
    }
    @Test
    void updateUser_updateGender_expectSuccessfullyUpdatedMessage() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /gender male";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "Successfully updated user settings!";
        assertTrue(output.contains(expectedMessage));
    }

    @Test
    void updateUser_updateTargetWeightAsAlphabets_expectInvalidFieldInfoFormatException() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /targetWeight abc";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        InvalidFieldInfoFormatException thrown = assertThrows(InvalidFieldInfoFormatException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "abc is not of valid format for /targetWeight please try again!";
        assertEquals(expectedMessage,thrown.getMessage());
    }
    @Test
    void updateUser_updateTargetWeightAsNegativeValue_expectNegativeFieldInfoException() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /targetWeight -10";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        NegativeFieldInfoException thrown = assertThrows(NegativeFieldInfoException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "Oops! You cannot enter a negative value for /targetWeight in command update";
        assertEquals(expectedMessage,thrown.getMessage());
    }
    @Test
    void updateUser_updateTargetWeight_expectSuccessfullyUpdatedMessage() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update /targetWeight 60";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        PrintStream oldOut = System.out;
        System.setOut(oldOut);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        command.execute(ui,null,mealStorage,userStorage,null);
        String output = new String(stream.toByteArray());
        String expectedMessage = "Successfully updated user settings!";
        assertTrue(output.contains(expectedMessage));
    }
}
