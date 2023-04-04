package seedu.commands;

import seedu.exceptions.ExtraArgumentsException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.ExampleUi;
import seedu.ui.GeneralUi;

public class ExamplesCommand extends Command{

    private String input;

    public ExamplesCommand(String command, String type) throws LifeTrackerException {
        String[] split = type.split(" ");
        if (command.length() == type.length() || split.length < 2) {
            throw new MissingArgumentsException(command, "[exercise/meal]");
        } else if (split.length > 2) {
            throw new ExtraArgumentsException();
        }
        this.input = split[1];
    }

    public void printExamples(String input, GeneralUi ui) throws LifeTrackerException {
        ExampleUi exampleUi = new ExampleUi();
        if(input.equals("meal")) {
            exampleUi.displayMealExamples();
        } else if (input.equals("exercise")) {
            exampleUi.displayExerciseExamples();
        } else {
            throw new LifeTrackerException("You can only input exercise/meal for this command!");
        }
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage,
                        MealStorage mealStorage, UserStorage userStorage, ExerciseStorage exerciseStorage)
            throws LifeTrackerException {
        printExamples(input, ui);
    }
}
