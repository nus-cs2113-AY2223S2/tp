package seedu.duke.commands;

import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.ui.Ui;

public class CommandHandler {

    public void handleUserCommands(String rawUserCommands, Ui ui, GenerateExercise exerciseGenerator){
        String[] userCommands = rawUserCommands.split(" ");
        Command command = null;
        switch(userCommands[0]){

        case "quick":
            command = new QuickStartCommand(userCommands[1]);
            break;
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
            break;
        }
        command.executeCommand(ui,exerciseGenerator);
    }
}
