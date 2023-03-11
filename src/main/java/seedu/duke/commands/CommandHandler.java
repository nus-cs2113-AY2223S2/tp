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
            case "generate":
                if (userCommands.length == 2) {
                    command = new GenerateRandomCommand(userCommands[1]);
                } else if (userCommands.length == 4) {
                    if (userCommands[1].equals("difficulty")) {
                        command = new GenerateSpecificDifficultyCommand(userCommands[2], userCommands[3]);
                    } else {
                        throw new DukeError("You did not type in the correct format for generating a command");
                    }
                } else {
                    throw new DukeError("You did not type in the correct format for generating a command");
                }
                break;
            case "bye":
            case "exit":
                ui.byeUser();
                System.exit(0);
                break;
            default:
                System.out.println("Unknown Command");
                break;
            }
        } catch (DukeError e) {
            System.out.println(e.getMessage());
            errorExists = true;
        }
        if (!errorExists){
            command.executeCommand(ui, exerciseGenerator);
        }
    }
}
