package seedu.duke;


import seedu.calorietracker.CaloriesRecorder;
import seedu.calorietracker.FoodList;
import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.IncorrectSyntaxCommand;
import seedu.exceptions.InvalidSyntaxException;
import seedu.parser.Parser;
import seedu.storage.readfile.WorkoutReadFile;
import seedu.storage.writefile.WorkoutWriteFile;
import seedu.ui.Ui;
import seedu.workout.Day;
import seedu.workout.WorkoutList;


public class Duke {
    private static final String FILE_PATH_WORKOUT = "data/workoutRecording.txt";
    private static final String FOOD_CALORIE = "data/foodCalories.txt";
    private static final String TOTAL_CALORIE = "data/dailyCalories.txt";
    private WorkoutList workoutList = new WorkoutList();
    private Day day;
    // private CalorieTracker calorieTracker = new CalorieTracker();
    private CaloriesRecorder caloriesRecorder;

    private FoodList foodList;


    public Duke() {
        //workoutList = ReadFile.readWorkoutFromFile(FILE_PATH);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        //  workoutList = ReadFile.readWorkoutFromFile(FILE_PATH);
        //day = new Day();
        workoutList = WorkoutReadFile.readWorkoutFromFile(FILE_PATH_WORKOUT);
        foodList = new FoodList();
        caloriesRecorder = new CaloriesRecorder();
        //foodList = FoodCaloriesReadFile.readFoodCalorieToFile(FOOD_CALORIE);
        //calorieTracker = DailyCaloriesReadFile.readDailyCalorieToFile(TOTAL_CALORIE);

        /* caloriesRecorder = new CaloriesRecorder();
        foodList = new FoodList();*/
        Ui.showWelcomeMessage();
        executeCommandUntilExit();
    }

    private void executeCommandUntilExit() {
        Command command;
        do {

            String userInput = Ui.getUserInput();
            try {
                command = new Parser().processCommand(userInput);
            } catch (InvalidSyntaxException ise) {
                command = new IncorrectSyntaxCommand(ise.toString());
            }
            command.setData(workoutList, caloriesRecorder, foodList);
            System.out.println(command.execute());
        } while (!ExitCommand.isExit(command));
        WorkoutWriteFile.writeWorkoutToFile(Duke.FILE_PATH_WORKOUT, workoutList);
        // FoodCaloriesWriteFile.writeFoodCaloriesToFile(FOOD_CALORIE, foodList);
        //DailyCaloriesWriteFile.writeDailyCaloriesToFile(TOTAL_CALORIE, rcalorieTracke);
    }
}

