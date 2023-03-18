package seedu.duke.commands;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.storage.UserCareerData;
import seedu.duke.ui.Ui;

public class CommandHandler {

    public void handleUserCommands (String rawUserCommands, Ui ui, GenerateExercise exerciseGenerator,
                                    UserCareerData userCareerData) {
        String[] userCommands = rawUserCommands.split(" ");
        Command command = null;
        boolean errorExists = false;
        try {
            switch (userCommands[0]) {
            case "generate":
                command = new GenerateFilterCommand(userCommands);
                break;
            case "filters":
                ui.printFilters();
                break;
            case "bye":
            case "exit":
                ui.byeUser();
                System.exit(0);
                break;
            case "help":
                command = new HelpCommand();
                break;
            case "readSample":
                command = new SampleReadCommand(userCareerData);
                break;
            case "writeSample":
                //sample data
                command = new SampleSavingCommand(userCareerData,
                                                  exerciseGenerator.generateRandomSetFrom(
                                                          exerciseGenerator.generateSetAll(), 3));
                break;
            default:
                ui.unknownCommand();
                errorExists = true;
                break;
            }
        } catch (DukeError e) {
            System.out.println(e.getMessage());
            errorExists = true;
        }
        if (!errorExists) {
            try {
                if (command != null) {
                    command.executeCommand(ui, exerciseGenerator);
                }

            } catch (DukeError e) {
                System.out.println(e.getMessage());
            }
        }
        ui.splitLine();
    }

}
