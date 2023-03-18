package seedu.duke;

import seedu.duke.commands.CommandHandler;
import seedu.duke.exceptions.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.storage.LoadUserData;
import seedu.duke.storage.UserCareerData;
import seedu.duke.ui.Ui;
import seedu.duke.states.ExerciseStateHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final Ui ui;
    private final GenerateExercise exerciseGenerator;
    private final ExerciseStateHandler exerciseHandler;
    private UserCareerData userCareerData;

    public Duke() {
        exerciseHandler = new ExerciseStateHandler();
        ui = new Ui();
        exerciseGenerator = new GenerateExercise();
        try {
            this.userCareerData = LoadUserData.loadUserCareer("userData.json");
        } catch (DukeError e) {
            System.out.println(e.getMessage());
            this.userCareerData = new UserCareerData();
        }

    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        CommandHandler commandHandler = new CommandHandler();
        Scanner in = new Scanner(System.in);
        ui.greetUser();
        while (true) {
            commandHandler.handleUserCommands(in.nextLine(), ui, exerciseGenerator, userCareerData, exerciseHandler);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
