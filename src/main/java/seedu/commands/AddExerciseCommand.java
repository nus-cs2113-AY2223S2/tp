package seedu.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import seedu.constants.DateConstants;
import seedu.entities.Exercise;
import seedu.exceptions.InvalidArgumentsException;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidDateException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.ExerciseUi;
import seedu.ui.GeneralUi;

public class AddExerciseCommand extends Command {
    private String commandWord;
    private String userInput;
    private String exerciseName;
    private String exerciseDescription;
    private float calorieBurnt;
    private LocalDate date;
    private DateTimeFormatter dtf;

    public AddExerciseCommand(String commandWord, String userInput) {
        this.commandWord = commandWord;
        this.userInput = userInput;
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                ExerciseStorage exerciseStorage) throws LifeTrackerException {
        this.dtf = DateConstants.PARSE_DTF;
        this.parseCommand();
        Exercise newExercise = new Exercise(exerciseName, exerciseDescription, calorieBurnt, date);
        exerciseStorage.saveExercise(newExercise);
        ui.printMotivateMessage();
        ExerciseUi.addedExercise(newExercise);
        ui.displayDayCalories(exerciseStorage, date, mealStorage);
    }

    private void parseCommand() throws LifeTrackerException {
        int exerciseNameIndex;
        int exerciseDescriptionIndex;
        int calorieBurntIndex;
        int dateIndex;
        String dateString;
        String exerciseNameIdentifier = "/type";
        String exerciseDescriptionIdentifier = "/description";
        String calorieBurntIdentifier = "/calories";
        String dateIdentifier = "/on";

        exerciseNameIndex = userInput.indexOf(exerciseNameIdentifier);
        if (exerciseNameIndex == -1) {
            throw new MissingArgumentsException(commandWord, exerciseNameIdentifier);
        } else if (exerciseNameIndex > commandWord.length() + 1) {
            throw new InvalidCommandException();
        }
        exerciseDescriptionIndex = userInput.indexOf(exerciseDescriptionIdentifier);
        if (exerciseDescriptionIndex == -1) {
            throw new MissingArgumentsException(commandWord, exerciseDescriptionIdentifier);
        }
        calorieBurntIndex = userInput.indexOf(calorieBurntIdentifier);
        if (calorieBurntIndex == -1) {
            throw new MissingArgumentsException(commandWord, calorieBurntIdentifier);
        }
        dateIndex = userInput.indexOf(dateIdentifier);
        if (dateIndex == -1) {
            throw new MissingArgumentsException(commandWord, dateIdentifier);
        }

        exerciseName = userInput.substring(
                exerciseNameIndex+exerciseNameIdentifier.length(), exerciseDescriptionIndex-1).trim();
        exerciseDescription = userInput.substring(
                exerciseDescriptionIndex+exerciseDescriptionIdentifier.length(), calorieBurntIndex-1).trim();
        try {           
            calorieBurnt = Float.parseFloat(userInput.substring(
                    calorieBurntIndex+calorieBurntIdentifier.length(), dateIndex-1).trim());
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(commandWord, calorieBurntIdentifier);
        }

        dateString = userInput.substring(dateIndex+dateIdentifier.length()).trim();
        try {
            if (dateString.matches("\\d{1,2}/")) {
                throw new InvalidDateException(dateString);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(dateString);
        }
    }
}
