package seedu.duke;

import seedu.duke.commands.CommandHandler;
import seedu.duke.exceptions.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.storage.StorageHandler;
import seedu.duke.userdata.UserCareerData;
import seedu.duke.ui.Ui;
import seedu.duke.states.ExerciseStateHandler;

import java.util.Scanner;

public class Duke {
    private static final String FILEPATH = "userData.json";
    private final Ui ui;
    private final GenerateExercise exerciseGenerator;
    private final ExerciseStateHandler exerciseHandler;
    private UserCareerData userCareerData;
    private final StorageHandler storageHandler;

    public Duke () {
        ui = new Ui();
        exerciseGenerator = new GenerateExercise();
        storageHandler = new StorageHandler(FILEPATH);
        exerciseHandler = new ExerciseStateHandler(storageHandler);
        this.userCareerData = storageHandler.loadUserCareer();

    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run () {
        CommandHandler commandHandler = new CommandHandler();
        Scanner in = new Scanner(System.in);
        ui.greetUser();
        while (true) {
            commandHandler.handleUserCommands(in.nextLine(), ui, exerciseGenerator, userCareerData, exerciseHandler,
                                              storageHandler);
        }
    }

    public static void main (String[] args) {
        new Duke().run();
    }

}
