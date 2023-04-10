package seedu.rainyDay.command;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ExitCommand extends Command {
    private static final Logger logger = Logger.getLogger(ExitCommand.class.getName());

    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("./logs/ExitCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log ExitCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting ExitCommand.execute()");
        String output = "We hope that you enjoyed using rainyDay, goodbye " + savedData.getReportOwner();
        setExit();

        return new CommandResult(output);
    }
}
