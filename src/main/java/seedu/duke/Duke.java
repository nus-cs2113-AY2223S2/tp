package seedu.duke;

import seedu.duke.achievements.AchievementListHandler;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.logic.commandhandler.CommandHandler;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.storage.JsonUserCareerStorage;
import seedu.duke.storage.JsonUserPlansStorage;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.UserCareerStorage;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.storage.UserPlansStorage;
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
    private Storage storage;
    private UserCareerData userCareerData;
    private HashMap<String, Integer> userExerciseHistory;
    private AchievementListHandler achievementListHandler;
    private UserPlan planner;

    public Duke () {
        ui = new Ui();
        exerciseGenerator = new GenerateExercise();
        UserPlansStorage userPlansStorage = new JsonUserPlansStorage(PLANS_FILEPATH);
        UserCareerStorage userCareerStorage = new JsonUserCareerStorage(USER_FILEPATH);
        storage = new StorageManager(userCareerStorage, userPlansStorage);
        exerciseHandler = new ExerciseStateHandler(storage);
        achievementListHandler = new AchievementListHandler();
        this.achievementListHandler.loadAchievementsFromFile();
        this.userCareerData = storage.loadUserData();
        this.planner = storage.loadUserPlans();
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
            try {
                String rawInput = in.nextLine();
                commandHandler.handleUserCommands(rawInput, ui, exerciseGenerator, userCareerData, exerciseHandler,
                                                  storage, planner, achievementListHandler, in);
            } catch (DukeError dukeError) {
                System.out.println(dukeError.getMessage());
            } catch (Exception e) {
                if (e.getMessage() == "No line found") {
                    System.out.println("Keyboard interrupt detected, Bye!");
                    return;
                } else {
                    System.out.println("An unexpected error has occurred");
                }
            }
        }
    }

}
