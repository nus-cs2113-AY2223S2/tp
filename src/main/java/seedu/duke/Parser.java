package seedu.duke;

import seedu.duke.Command.Command;
import seedu.duke.Command.DeleteCommand;
import seedu.duke.Command.ListCompanyCommand;
import seedu.duke.Command.ListVenueCommand;

public interface Parser {
    static Command parse(String input){

        Ui ui = new Ui();
        String[] inputWords = input.split(" ");
        String command = inputWords[0];
        switch (command) {
        case "list":
            if (inputWords[1].equals("companies")) {
                ListCompanyCommand companyCommand = new ListCompanyCommand(command);
                return companyCommand;
            } else if (inputWords[1].equals("venues")) {
                ListVenueCommand venueCommand = new ListVenueCommand(command);
                return venueCommand;
            }
            break;
        case "add":
            System.out.println(input.replaceFirst(command, "").trim());
            break;
        case "delete":
            int taskNum = Integer.parseInt(inputWords[1]) - 1;
            DeleteCommand deleteCommand = new DeleteCommand(command, taskNum);
            return deleteCommand;
        case "help":
            ui.showGuide();
            break;
        case "exit":
            ui.showExitMessage();
            break;
        default:
            System.out.println("Unknown input!");
        }
        return null;
    }
}
