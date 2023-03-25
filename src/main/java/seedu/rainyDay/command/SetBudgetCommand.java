package seedu.rainyDay.command;

import seedu.rainyDay.RainyDay;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author lil1n
/**
 * Represents a command that delete statement from the financial report
 */
public class SetBudgetCommand extends Command {

    private static final Logger logger = Logger.getLogger(SetBudgetCommand.class.getName());

    private final double goal;

    public SetBudgetCommand(double goal) {
        this.goal = goal;
    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("SetBudgetCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log SetBudgetCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * Executes the command and print the relevant output message
     */
    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "Starting SetBudgetCommand.execute()");
        RainyDay.userData.setBudgetGoal(goal);
        String output = String.format("Monthly Budget Goal set to $%,.2f!", goal);
        logger.log(Level.INFO, "Completed SetBudgetCommand.execute()");
        return new CommandResult(output);
    }
}
