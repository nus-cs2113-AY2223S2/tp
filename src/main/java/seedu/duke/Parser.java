package seedu.duke;


import java.util.logging.Level;

import static java.lang.System.exit;
import static seedu.duke.Duke.LOGGER;

public class Parser {
    public Parser() {

    }
    public void checkManPageInput(String userInput) {
        assert userInput.split(" ").length == 2: "Correct input should be man /*command*";
        String userCommand = userInput.split("/")[1].trim();

        switch (userCommand) {
        case "add":
            Manpage.printManPageAdd();
            break;
        case "list":
            Manpage.printManPageList();
            break;
        case "find":
            Manpage.printManPageFind();
            break;
        case "delete":
            Manpage.printManPageDelete();
            break;
        case "exit":
            Manpage.printManPageExit();
            break;
        default:
            break;
        }
    }

    public void checkUserInput(String userInput, ModuleList listOfModules) {
        String[] userCommands = userInput.split("/");
        switch (userCommands[0].trim()) {
        case "man":
            assert userCommands.length >= 1;
            if (userCommands.length == 2) {
                checkManPageInput(userInput);
            } else {
                Manpage.printManPage();
            }
            break;
        case "add":
            try {
                checkAddInput(listOfModules, userCommands);
            } catch (DukeException e) {
                LOGGER.log(Level.WARNING, "addModule Check failed: " + e.getDescription());
                Print.printErrorMessage(e);
            }
            break;
        case "find":
            assert userCommands.length == 2;
            listOfModules.findModule(userCommands[1].trim());
            break;
        case "delete":
            assert userCommands.length == 2;
            listOfModules.deleteModule(userCommands[1].trim());
            break;
        case "list":
            assert userCommands.length == 1;
            listOfModules.viewModuleList();
            break;
        case "bye":
            assert userCommands.length == 1;
            Print.printFarewellMessage();
            LOGGER.log(Level.INFO, "Exiting Modganiser.");
            exit(0);
            break;
        default:
            break;
        }
    }

    private void checkAddInput(ModuleList listOfModules, String[] userCommands) throws DukeException {
        //check for correct number of fields
        if (userCommands.length < 4) {
            DukeException inputException = new DukeException("Missing fields");
            throw inputException;
        } else if (userCommands.length > 4) {
            DukeException inputException = new DukeException("Too many fields");
            throw inputException;
        }
        //check for correct field in MC
        try {
            int moduleCredits = Integer.parseInt(userCommands[2].trim());
            if (moduleCredits < 0 || moduleCredits > 8) {
                DukeException inputException = new DukeException("Make sure Modular Credits is a number from 0 to 8");
                throw inputException;
            }
        } catch (NumberFormatException e) {
            DukeException inputException = new DukeException("Make sure Modular Credits is a number from 0 to 8");
            throw inputException;
        }
        //check for correct field in type of module
        String typeOfModule = userCommands[3].trim();
        boolean isCorrectModuleType = typeOfModule.equals("CORE") || typeOfModule.equals("UE")
                || typeOfModule.equals("GE");
        if (!isCorrectModuleType) {
            DukeException inputException = new DukeException("Incorrect Module Type, "
                    + "Accepted Module Types are: (CORE,UE,GE)");
            throw inputException;
        }
        assert userCommands.length == 4;
        listOfModules.addModule(userCommands[1].trim(),
                userCommands[2].trim(), userCommands[3].trim());
    }

}
