package seedu.duke.command;

public class Parser {
    public Parser() {
    }

    public String[] parseCommand(String userInput) {
        String userCommand = userInput.split(" ", 2)[0];
        String providedInformation = "";
        switch (userCommand) {
        case "list":
            // Fallthrough
        case "bye":
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "add":
            providedInformation = userInput.split(" ", 2)[1];
            break;
        default:
            userCommand = "unknown command";
            break;
        }
        String[] parsedCommand = {userCommand, providedInformation};
        return parsedCommand;
    }
}
