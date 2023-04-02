package seedu.commands;

import seedu.exceptions.LifeTrackerException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;
import java.util.Objects;
public class HelpCommand extends Command {

    private final String command;

    public HelpCommand() {
        command = null;
    }

    public HelpCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
            throws LifeTrackerException {
        if (command == null) {
            ui.printHelpPage();
            return;
        }
        switch (command) {
        case "view":
            ui.printHelpView();
            return;
        case "update":
            ui.printHelpUpdate();
            return;
        case "add":
            ui.printHelpAdd();
            return;
        case "delete":
            ui.printHelpDelete();
            return;
        case "list":
            ui.printHelpList();
            return;
        case "filter":
            ui.printHelpFilter();
            return;
        case "nutrition":
            ui.printHelpNutrition();
            return;
        case "exercise":
            ui.printHelpExercise();
            return;
        case "track":
            ui.printHelpTrack();
            return;
        default:
            ui.printHelpBye();
        }
    }
}