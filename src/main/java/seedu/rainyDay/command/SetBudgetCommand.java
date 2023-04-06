package seedu.rainyDay.command;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author BenjaminPoh

/**
 * Represents a command that sets the User's budget for the month
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
            FileHandler fileHandler = new FileHandler("./logs/SetBudgetCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log SetBudgetCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * Executes the command and prints the monthly budget goal set, or if the goal is removed.
     */
    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "Starting SetBudgetCommand.execute()");
        savedData.setBudgetGoal(goal);
        String output;
        if (goal > 0) {
            output = String.format("Monthly Budget Goal set to $%,.2f!", goal);
        } else {
            output = "Monthly Budget Goal removed!";
        }
        logger.log(Level.INFO, "Completed SetBudgetCommand.execute()");
        return new CommandResult(output);
    }
}
