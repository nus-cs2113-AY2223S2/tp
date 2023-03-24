package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ChooseVenueCommand;
import seedu.duke.command.ListCompanyCommand;
import seedu.duke.command.PurgeCommand;
import seedu.duke.command.AddCommand;
import seedu.duke.command.LoadSampleCompanyCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.ListVenueCommand;
import seedu.duke.command.ConfirmCommand;
import seedu.duke.command.UnconfirmCommand;
import seedu.duke.command.ListUnconfirmedCommand;

import seedu.duke.ui.Ui;
import seedu.duke.exception.WrongFormatException;

public interface Parser {

    static Command parse(String input) throws WrongFormatException, NumberFormatException {
        Ui ui = new Ui();
        String[] inputWords = input.split(" ");
        String command = inputWords[0];
        switch (command) {
        case "list":
            if (inputWords.length == 1) {
                throw new WrongFormatException();
            }
            if (inputWords[1].equals("companies")) {
                ListCompanyCommand companyCommand = new ListCompanyCommand(command + " companies");
                return companyCommand;
            } else if (inputWords[1].equals("venues")) {
                ListVenueCommand venueCommand = new ListVenueCommand(command + " venues");
                return venueCommand;
            } else if (inputWords[1].equals("unconfirmed")){
                ListUnconfirmedCommand unconfirmedCommand = new ListUnconfirmedCommand(command + " unconfirmed");
                return unconfirmedCommand;
            }
            throw new WrongFormatException();
        case "add":
            input = input.replaceFirst("add n/", "").trim();
            String[] inputs = input.split("/");
            String companyName = inputs[1].trim();
            String industry = inputs[3].trim();
            int contactNumber = Integer.parseInt(inputs[5].trim());
            String contactEmail = inputs[7].trim();
            AddCommand addCommand = new AddCommand(command, industry, companyName, contactNumber, contactEmail);
            return addCommand;
        case "delete":
            if (inputWords.length == 1) {
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
        case "confirm":
            if (inputWords.length == 1) {
                throw new WrongFormatException();
            }
            int companyConfirmNum = Integer.parseInt(inputWords[1]) - 1;
            ConfirmCommand confirmCommand = new ConfirmCommand(command, companyConfirmNum);
            return confirmCommand;
        case "unconfirm":
            if (inputWords.length == 1){
                throw new WrongFormatException();
            }
            int companyUnconfirmNum = Integer.parseInt(inputWords[1]) - 1;
            UnconfirmCommand unconfirmCommand = new UnconfirmCommand(command, companyUnconfirmNum);
            return unconfirmCommand;
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
