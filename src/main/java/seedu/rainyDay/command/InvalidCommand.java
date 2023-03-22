package seedu.rainyDay.command;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author lil1n
/**
 * Represents an invalid command
 */
public class InvalidCommand extends Command {
    private static final Logger logger = Logger.getLogger(InvalidCommand.class.getName());
    private final String output;

    public InvalidCommand(String output) {
        this.output = output;
    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("InvalidCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log InvalidCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * Executes the command and print the relevant output message
     */
    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting InvalidCommand.execute()... exiting InvalidCommand.execute()");
        CommandResult result = new CommandResult(output);
        result.printResult();
        return result;
    }
}
