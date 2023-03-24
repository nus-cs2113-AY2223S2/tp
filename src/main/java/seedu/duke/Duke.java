package seedu.duke;

import seedu.duke.logic.commandhandler.CommandHandler;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.storage.StorageHandler;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.ui.Ui;
import seedu.duke.logic.commandhandler.states.ExerciseStateHandler;
import seedu.duke.data.userdata.userplan.UserPlan;

import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    private static final String USER_FILEPATH = "userData.json";
    private static final String PLANS_FILEPATH = "plansData.json";
    private final Ui ui;
    private final GenerateExercise exerciseGenerator;
    private final ExerciseStateHandler exerciseHandler;
    private final StorageHandler storageHandler;
    private UserCareerData userCareerData;
    private HashMap<String, Integer> userExerciseHistory;
    private UserPlan planner;

    public Duke () {
        ui = new Ui();
        exerciseGenerator = new GenerateExercise();
        storageHandler = new StorageHandler(USER_FILEPATH, PLANS_FILEPATH);
        exerciseHandler = new ExerciseStateHandler(storageHandler);
        this.userCareerData = storageHandler.loadUserData();
        this.planner = storageHandler.loadUserPlans();
        assert this.userCareerData != null : "User career is null, data should not be empty";
        assert this.planner != null : "Planner is null, data should not be empty";
    }

    public static void main (String[] args) {
        new Duke().run();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run () {
        CommandHandler commandHandler = new CommandHandler();
        Scanner in = new Scanner(System.in);
        ui.greetUser();
        while (true) {
            commandHandler.handleUserCommands(in.nextLine(), ui, exerciseGenerator, userCareerData, exerciseHandler,
                                              storageHandler, planner);
        }
    }

}
