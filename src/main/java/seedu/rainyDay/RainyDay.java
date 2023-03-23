package seedu.rainyDay;

import seedu.rainyDay.command.CommandResult;
import seedu.rainyDay.modules.Storage;
import seedu.rainyDay.modules.Ui;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.modules.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RainyDay {
    public static String filePath = "./data/rainyDay.ser";
    public static FinancialReport financialReport = new FinancialReport(new ArrayList<>());
    private static Logger logger = Logger.getLogger(RainyDay.class.getName());
    private final Ui ui;

    private RainyDay(String filePath) {
        ui = new Ui();
        try {
            financialReport = Storage.loadFromFile(filePath);
            logger.log(Level.INFO, "File loaded successfully.");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            logger.log(Level.INFO, "No valid save file detected. Starting with empty financial data.");
            ui.noFileExist();
            String username = ui.readUserName();
            assert username != null : "Inputted username should not be null";
            financialReport.setReportOwner(username);
        }
    }

    private void run() {
        showStartingMessage();
        runCommand();
        ui.sayFarewellToUser(financialReport.getReportOwner());
    }

    private void showStartingMessage() {
        ui.printLogo();
        ui.greetUser(financialReport.getReportOwner());
    }

    private void runCommand() {
        Command specificCommand;
        String userInput = ui.readUserCommand();
        while (!userInput.equalsIgnoreCase(Command.COMMAND_EXIT)) {
            try {
                specificCommand = new Parser().parseUserInput(userInput);
                assert specificCommand != null : "Parser returned null";
                executeCommand(specificCommand);
                userInput = ui.readUserCommand();
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }

    private void executeCommand(Command command) {
        command.setData(financialReport);
        CommandResult result = command.execute();
        result.printResult();
    }

    private static void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("RainyDay.log");
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    public static void main(String[] args) {
        setupLogger();
        logger.log(Level.INFO, "Starting RainyDay");
        new RainyDay(filePath).run();
        logger.log(Level.INFO, "Quitting RainyDay");
    }
}
