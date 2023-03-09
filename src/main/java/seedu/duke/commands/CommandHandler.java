package seedu.duke.commands;

import seedu.duke.exceptions.ErrorMessages;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.ui.Ui;

public class CommandHandler {

    public void handleUserCommands(String rawUserCommands, Ui ui, GenerateExercise exerciseGenerator) {
        String[] userCommands = rawUserCommands.split(" ");
        Command command = null;
        switch(userCommands[0]){
        case "/help":
            ui.showHelpList();
            break;

        case "generate":
            if (userCommands.length == 1){
                ErrorMessages.emptyDescriptionErrorMessage();
                break;
            }
            else {
                command = new GenerateRandomCommand(userCommands[1]);
                command.executeCommand(ui, exerciseGenerator);
            }
            break;

        case "random" :
            ui.generateRandomExercises();
            break;

        //case "customised" :

        case "bye":
        case "exit":
            ui.byeUser();
            System.exit(0);
            break;

        default:
            ErrorMessages.unknownCommandMessage();
            ErrorMessages.helpCommandMessage();
            break;
        }

    }
}
