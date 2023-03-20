package seedu.duke.parser;

import seedu.duke.command.*;

import seedu.duke.ui.Ui;
import seedu.duke.exception.WrongFormatException;

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
            if (inputWords.length == 1){
                throw new WrongFormatException();
            }
            int companyNum = Integer.parseInt(inputWords[1]) - 1;
            DeleteCommand deleteCommand = new DeleteCommand(command, companyNum);
            return deleteCommand;
        case "load":
            if (inputWords.length == 1) {
                throw new WrongFormatException();
            }
            if (inputWords[1].equals("samples")) {
                LoadSampleCompanyCommand loadSampleCompanyCommand = new LoadSampleCompanyCommand(command + " samples");
                return loadSampleCompanyCommand;
            }
            throw new WrongFormatException();
        case "purge":
            PurgeCommand purgeCommand = new PurgeCommand(command);
            return purgeCommand;
        case "choose":
            if (inputWords.length != 3) {
                throw new WrongFormatException();
            }
            if (inputWords[1].equals("venue")) {
                int venueNum = Integer.parseInt(inputWords[2]) - 1;
                ChooseVenueCommand chooseVenueCommand = new ChooseVenueCommand(command + " venue", venueNum);
                return chooseVenueCommand;
            }
            throw new WrongFormatException();
        case "help":
            ui.showGuide();
            break;
        case "exit":
            ui.showExitMessage();
            break;
        default:
            throw new WrongFormatException();
        }
        Command defaultCommand = new Command(command);
        return defaultCommand;
    }
}
