package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.AddCommand;
import seedu.duke.command.ConfirmCommand;
import seedu.duke.command.UnconfirmCommand;
import seedu.duke.command.ChooseVenueCommand;
import seedu.duke.command.FindCompanyCommand;
import seedu.duke.command.FindIndustryCommand;
import seedu.duke.command.ListCompanyCommand;
import seedu.duke.command.ListUnconfirmedCommand;
import seedu.duke.command.ListVenueCommand;
import seedu.duke.command.LoadSampleCompanyCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.PurgeCommand;

import seedu.duke.exception.IntegerSizeExceededException;
import seedu.duke.ui.Ui;
import seedu.duke.exception.WrongFormatException;

import java.math.BigInteger;

public interface Parser {

    /**
     * Handles the various command type and parse accordingly
     *
     * @param input the information that requires parsing
     * @return command type that would represent what to execute
     *
     * @throws WrongFormatException if error occurs when the input is in the wrong format
     * @throws NumberFormatException if error occurred due to user not providing a number where expected
     * @throws NullPointerException if error occurred due to null pointers
     * @throws IndexOutOfBoundsException if error occurred due to an index being out of bounds
     */
    static Command parse(String input) throws WrongFormatException,
            NumberFormatException, NullPointerException, IndexOutOfBoundsException, IntegerSizeExceededException {
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
            if (inputWords.length == 1) {
                throw new WrongFormatException();
            }
            input = input.replaceFirst("add", "").trim();
            int indexOfName = input.indexOf("n/");
            int indexOfIndustry = input.indexOf("i/");
            int indexOfContactNumber = input.indexOf("c/");
            int indexOfContactEmail = input.indexOf("e/");
            String companyName = input.substring(indexOfName+2, indexOfIndustry).trim();
            String industry = input.substring(indexOfIndustry+2, indexOfContactNumber).trim();
            int contactNumber = Integer.parseInt(input.substring(indexOfContactNumber+2, indexOfContactEmail).trim());
            String contactEmail = input.substring(indexOfContactEmail+2).trim();
            AddCommand addCommand = new AddCommand(command, industry, companyName, contactNumber, contactEmail);
            return addCommand;
        case "delete":
            if (inputWords.length == 1) {
                throw new WrongFormatException();
            }
            BigInteger currValue = new BigInteger(inputWords[1]);
            checkInputLimit(currValue);
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
            BigInteger currConfirmNum = new BigInteger(inputWords[1]);
            checkInputLimit(currConfirmNum);
            int companyConfirmNum = Integer.parseInt(inputWords[1]) - 1;
            ConfirmCommand confirmCommand = new ConfirmCommand(command, companyConfirmNum);
            return confirmCommand;
        case "unconfirm":
            if (inputWords.length == 1){
                throw new WrongFormatException();
            }
            BigInteger currUnconfirmNum = new BigInteger(inputWords[1]);
            checkInputLimit(currUnconfirmNum);
            int companyUnconfirmNum = Integer.parseInt(inputWords[1]) - 1;
            UnconfirmCommand unconfirmCommand = new UnconfirmCommand(command, companyUnconfirmNum);
            return unconfirmCommand;
        case "find":
            if(inputWords[1].equals("industry")){
                String targetIndustry = input.replace("find", "").trim();
                targetIndustry = targetIndustry.replace("industry", "").trim();
                if (targetIndustry.equals("")) {
                    throw new WrongFormatException();
                }
                return new FindIndustryCommand("find industry", targetIndustry.toUpperCase());
            } else if(inputWords[1].equals("company")){
                String targetCompany = input.replace("find", "").trim();
                targetCompany = targetCompany.replace("company", "").trim();
                if (targetCompany.equals("")) {
                    throw new WrongFormatException();
                }
                return new FindCompanyCommand("find company", targetCompany);
            } else {
                throw new WrongFormatException();
            }
        case "help":
            ui.showGuide();
            break;
        case "exit":
            ui.showExitMessage();
            System.exit(0);
            break;
        default:
            throw new WrongFormatException();
        }
        Command defaultCommand = new Command(command);
        return defaultCommand;
    }

    private static void checkInputLimit(BigInteger currValue) throws IntegerSizeExceededException {
        BigInteger intMax = BigInteger.valueOf(Integer.MAX_VALUE);
        if (intMax.compareTo(currValue) == -1) {
            throw new IntegerSizeExceededException();
        }
    }
}
