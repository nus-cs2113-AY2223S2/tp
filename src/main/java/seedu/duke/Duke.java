package seedu.duke;

import seedu.duke.commons.LogMaster;
import seedu.duke.commons.Version;
import seedu.duke.logic.commandhandler.CommandHandler;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.storage.JsonUserCareerStorage;
import seedu.duke.storage.JsonUserPlansStorage;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.UserCareerStorage;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.storage.UserPlansStorage;
import seedu.duke.ui.UiManager;
import seedu.duke.logic.commandhandler.states.ExerciseStateHandler;
import seedu.duke.data.userdata.userplan.UserPlan;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

public class Duke {
    private static final String USER_FILEPATH = "userData.json";
    private static final String PLANS_FILEPATH = "plansData.json";
    private final UiManager uiManager;
    private final GenerateExercise exerciseGenerator;
    private final ExerciseStateHandler exerciseHandler;
    private Storage storage;
    private UserCareerData userCareerData;
    private HashMap<String, Integer> userExerciseHistory;
    private static final Logger logger = LogMaster.getLogger(Duke.class);
    private UserPlan planner;

    public static final Version VERSION = new Version(0, 2, 0, true);

    /**
     *
     */
    public Duke () {
        uiManager = new UiManager();
        exerciseGenerator = new GenerateExercise();
        UserPlansStorage userPlansStorage = new JsonUserPlansStorage(PLANS_FILEPATH);
        UserCareerStorage userCareerStorage = new JsonUserCareerStorage(USER_FILEPATH);
        storage = new StorageManager(userCareerStorage, userPlansStorage);
        exerciseHandler = new ExerciseStateHandler(storage);
        this.userCareerData = storage.loadUserData();
        this.planner = storage.loadUserPlans();
        assert this.userCareerData != null : "User career is null, data should not be empty";
        assert this.planner != null : "Planner is null, data should not be empty";
    }

    public static void launch(){
        new Duke().run();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run () {
        CommandHandler commandHandler = new CommandHandler();
        Scanner in = new Scanner(System.in);
        uiManager.greetUser();
        while (true) {
            commandHandler.handleUserCommands(in.nextLine(), uiManager, exerciseGenerator, userCareerData, exerciseHandler,
                                              storage, planner);
        }
    }


//    @Override
//    public void start() {
//        logger.info("Starting AddressBook " + Duke.VERSION);
//        Ui.start();
//    }


}
