package seedu.rainyDay;

import seedu.rainyDay.exceptions.RainyDayException;
import seedu.rainyDay.modules.Storage;
import seedu.rainyDay.modules.UI;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RainyDay {
    public static String filePath = "rainyDay.txt";
    public static FinancialReport financialReport = new FinancialReport(new ArrayList<>());
    private static Logger logger = Logger.getLogger("RainyDayLog.log");

    private RainyDay(String filePath) {
        try {
            financialReport = new FinancialReport(Storage.loadFromFile(filePath));
            logger.log(Level.INFO, "File loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.INFO, "No valid save file detected. Starting with empty financial data.");
            UI.noFileExist();
        }
    }

    private void run() {
        Scanner input = new Scanner(System.in);
        UI.printLogo();
        UI.greetUser(input.nextLine());

        while (true) {
            try {
                String userInput = UI.getUserInput(input);
                if (userInput.equalsIgnoreCase(Command.COMMAND_EXIT)) {
                    break;
                }
                Command specificCommand = Parser.parseUserInput(userInput);
                assert specificCommand != null : "Parser returned null";
                specificCommand.execute();
            } catch (RainyDayException e) {
                logger.log(Level.INFO, "RainyDayException caught");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        UI.sayFarewellToUser();
    }

    public static void main(String[] args) {
        logger.log(Level.INFO, "Starting RainyDay");
        new RainyDay(filePath).run();
        logger.log(Level.INFO, "Quitting RainyDay");
    }
}
