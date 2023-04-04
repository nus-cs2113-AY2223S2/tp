package seedu.rainyDay;

import seedu.rainyDay.command.CommandResult;
import seedu.rainyDay.data.AllData;
import seedu.rainyDay.data.MonthlyExpenditures;
import seedu.rainyDay.data.UserData;
import seedu.rainyDay.exceptions.RainyDayException;
import seedu.rainyDay.modules.Storage;
import seedu.rainyDay.modules.Ui;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.modules.Parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RainyDay {
    public static String filePath = "./data/rainyDay.json";
    public static AllData allData;
    public static UserData userData;
    public static MonthlyExpenditures monthlyExpenditures;

    private static Logger logger = Logger.getLogger(RainyDay.class.getName());

    private final Ui ui;

    private RainyDay(String filePath) {
        ui = new Ui();
        try {
            ui.printLogo();
            userData = Storage.loadFromFile(filePath);
            monthlyExpenditures = new MonthlyExpenditures(new HashMap<>());
            monthlyExpenditures.loadAllExpenditures(userData.getFinancialReport());
            allData = new AllData(userData,monthlyExpenditures);
            ui.greetUser(userData.getReportOwner());
            assert userData != null : "Error loading from json file";
            logger.log(Level.INFO, "File loaded successfully.");
        } catch (Exception e) {
            logger.log(Level.INFO, "No valid save file detected. Starting with empty financial data.");
            ui.noFileExist();
            String username = ui.readUserName();
            assert username != null : "Inputted username should not be null";
            FinancialReport financialReport = new FinancialReport(new ArrayList<>());
            userData = new UserData(financialReport);
            HashMap<Integer, Double> expenditures = new HashMap<>();
            monthlyExpenditures = new MonthlyExpenditures(expenditures);
            allData = new AllData(userData,monthlyExpenditures);
            financialReport.setReportOwner(username);
        }
    }

    private void run() {
        setUpDate();
        runCommand();
    }

    private void setUpDate() {
        System.out.println(userData.checkUserBudgetLimit(LocalDate.now()));
        Storage.writeToFile(RainyDay.userData, RainyDay.filePath);
    }

    private void runCommand() {
        Command specificCommand;
        while (true) {
            try {
                String userInput = ui.readUserCommand();
                specificCommand = new Parser().parseUserInput(userInput);
                assert specificCommand != null : "Parser returned null";
                executeCommand(specificCommand);
                if(specificCommand.isExit()) {
                    break;
                }
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }

    private void executeCommand(Command command) throws RainyDayException {
        command.setData(allData);
        CommandResult result = command.execute();
        if (result != null) {
            System.out.println(result.output);
        }
        Storage.writeToFile(userData, filePath);
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
