package seedu.duke;

import seedu.duke.Command.*;

public interface Parser {
    static Command parse(String input) throws WrongFormatException{

        Ui ui = new Ui();
        String[] inputWords = input.split(" ");
        String command = inputWords[0];
        switch (command) {
        case "list":
            if (inputWords.length == 1){
                throw new WrongFormatException();
            }
            if (inputWords[1].equals("companies")) {
                ListCompanyCommand companyCommand = new ListCompanyCommand(command);
                return companyCommand;
            } else if (inputWords[1].equals("venues")) {
                ListVenueCommand venueCommand = new ListVenueCommand(command);
                return venueCommand;
            }
            throw new WrongFormatException();
        case "add":
            System.out.println(inputWords[0]);
            System.out.println(inputWords[1]);
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
            throw new WrongFormatException();
        }
        Command c = new Command(command);
        return c;
    }
}
