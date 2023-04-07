package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.entities.CaloricIntake;
import seedu.exceptions.InvalidChoiceException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
import java.time.LocalDate;
//import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UpdateUserCommandTest {
    private final UserStorage userStorage = new UserStorage("./data/userData.csv");
    private final FoodStorage foodStorage = new FoodStorage();
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);
    private final GeneralUi ui = new GeneralUi();
    private final CaloricIntake meals = new CaloricIntake(mealStorage.getMealByDate(LocalDate.now()));

    @Test
    void updateUser_missingNumber_expectMissingArgumentsException() throws LifeTrackerException {
        String commandWord = "update";
        String userInput = "update";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        MissingArgumentsException thrown = assertThrows(MissingArgumentsException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedErrorMessage = "Oops! Missing argument [number] for command update";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }

    @Test
    void updateUser_wrongNumber_expectInvalidChoiceMessage() throws LifeTrackerException{
        String commandWord = "update";
        String userInput = "update 9";
        UpdateUserCommand command = new UpdateUserCommand(commandWord, userInput);
        InvalidChoiceException thrown = assertThrows(InvalidChoiceException.class, () -> {
            command.execute(ui,null,mealStorage,userStorage,null);
        });
        String expectedMessage = "This is an invalid choice! Please input a valid choice!";
        assertEquals(expectedMessage,thrown.getMessage());
    }
}
