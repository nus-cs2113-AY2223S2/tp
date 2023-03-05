package seedu.duke.commands;

import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.ui.Ui;

public class CommandHandler {

    public void handleUserCommands(String rawUserCommands, Ui ui, GenerateExercise exerciseGenerator){
        String[] userCommands = rawUserCommands.split(" ");
        Command command = null;
        switch(userCommands[0]){

        case "generate":
            command = new GenerateRandomCommand(userCommands[1]);
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
        command.executeCommand(ui,exerciseGenerator);
    }
}
