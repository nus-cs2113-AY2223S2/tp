package functionalities.commands;

import exception.SniffException;
import functionalities.SniffTasks;

/**
 * Command to execute find feature
 * Supports 4 types of find operations
 * Find by appointment uID, animal type, type of appointment and date of appointment
 */
public class FindCommand extends Command {

    protected String category;
    protected String details;

    public FindCommand(String category, String details) {
        this.category = category;
        this.details = details;
    }

    /**
     * Checks type of find command entered and executes the respective find command located in SniffTasks class
     *
     * @param tasks The SniffTasks class
     * @throws SniffException Throws exception if invalid find command type is entered
     */
    @Override
    public void executeCommand(SniffTasks tasks) throws SniffException {
        if (category.equals("appointment")) {
            tasks.findAppointment(details);
        } else if (category.equals("animal")) {
            tasks.findAnimal(details);
        } else if (category.equals("type")) {
            tasks.findType(details);
        } else if (category.equals("date")) {
            tasks.findDate(details);
        } else {
            throw new SniffException("Unsupported find command!");
        }
    }

}
