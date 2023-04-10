package seedu.main;

import static java.lang.System.exit;

public class Parser {
    private Command commands = new Command();
    public Parser() {

    }

    /**
     * Checks for valid user commands
     *
     * @param userInput The command that the user inputs to the ChatBot
     * @throws MainException if user command is invalid
     */
    public void checkUserInput(String userInput, ModuleList moduleList) throws MainException {
        String[] userCommands = userInput.toUpperCase().split("/");

        switch (userCommands[0].trim()) {
        case "MAN":
            commands.manPageCommand(userCommands,userInput);
            break;
        case "ADD":
            commands.addCommand(userCommands, moduleList);
            break;
        case "FIND":
            commands.findCommand(userCommands, moduleList);
            break;
        case "DELETE":
            commands.deleteCommand(userCommands, moduleList);
            break;
        case "LIST":
            commands.listCommand(userCommands, moduleList);
            break;
        case "EDIT":
            commands.editCommand(userCommands, moduleList);
            break;
        case "GRADE":
            commands.gradeCommand(userCommands, moduleList);
            break;
        case "CALCULATECAP":
            commands.calculateCAPCommand(userCommands, moduleList);
            break;
        case "BYE":
            commands.byeCommand(userCommands, moduleList);
            exit(0);
            break;
        case "TRACK":
            commands.trackCommand(userCommands, moduleList);
            break;
        default:
            throw new MainException("Invalid Command");
        }
    }
}
