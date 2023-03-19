package seedu.duke.parser;

import seedu.duke.ui.Ui;
import seedu.duke.exception.WrongFormatException;
import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ListCompanyCommand;
import seedu.duke.command.ListVenueCommand;
import seedu.duke.command.DeleteCommand;

public interface Parser {

    static Command parse(String input) throws WrongFormatException, NumberFormatException {
        Ui ui = new Ui();
        String[] inputWords = input.split(" ");
        String command = inputWords[0];
        switch (command) {
        case "list":
            if (inputWords.length == 1){
                throw new WrongFormatException();
            }
            if (inputWords[1].equals("companies")) {
                ListCompanyCommand companyCommand = new ListCompanyCommand(command + " companies");
                return companyCommand;
            } else if (inputWords[1].equals("venues")) {
                ListVenueCommand venueCommand = new ListVenueCommand(command + " venues");
                return venueCommand;
            }
            throw new WrongFormatException();
        case "add":
            input = input.replaceFirst("add n/", "").trim();
            int indexOfSlash = input.indexOf("/");
            int lastIndexOfSlash = input.lastIndexOf("/");
            String companyName = input.substring(0, indexOfSlash - 2);
            int contactNumber = Integer.parseInt(input.substring(indexOfSlash + 1, lastIndexOfSlash - 2));
            String contactEmail = input.substring(lastIndexOfSlash + 1);
            AddCommand addCommand = new AddCommand(command, companyName, contactNumber, contactEmail);
            return addCommand;
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
            throw new WrongFormatException();
        }
        Command default_command = new Command(command);
        return default_command;
    }
}
