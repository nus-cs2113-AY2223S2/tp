package seedu.duke.commands;

import seedu.duke.errors.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.ui.Ui;

public class CommandHandler {

    private static boolean errorExists = false;

    public void handleUserCommands(String rawUserCommands, Ui ui, GenerateExercise exerciseGenerator) {
        String[] userCommands = rawUserCommands.split(" ");
        Command command = null;
        errorExists = false;
        try {
            switch (userCommands[0]) {
            case "quick":
                if (userCommands.length == 2) {
                    command = new QuickStartCommand(userCommands[1]);
                    break;
                } else {
                    throw new DukeError("You did not type in the correct format for generating a quick command");
                }
            case "generate":
                command = new GenerateFilterCommand(userCommands);
                break;
            case "bye":
            case "exit":
                ui.byeUser();
                System.exit(0);
                break;
            default:
                System.out.println("Unknown Command");
                errorExists = true;
                break;
            }
        } catch (DukeError e) {
            System.out.println(e.getMessage());
            errorExists = true;
        }
        if (!errorExists) {
            try {
                command.executeCommand(ui, exerciseGenerator);
            } catch (DukeError e){
                System.out.println(e.getMessage());
            }
        }
    }
}
