package seedu.apollo.command.task;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.storage.Storage;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;
import seedu.apollo.utils.LoggerInterface;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * List Command class that shows user a list of all the Tasks they have input.
 */
public class ListCommand extends Command {//implements LoggerInterface {
    private static Logger logger = Logger.getLogger("ListCommand");

    public ListCommand() {
        super(logger);
//        setUpLogger();
    }
//    /**
//     * Sets up logger for ListCommand class.
//     *
//     * @throws IOException If logger file cannot be created.
//     */
//    @Override
//    public void setUpLogger() {
//        LogManager.getLogManager().reset();
//        logger.setLevel(Level.ALL);
//        ConsoleHandler logConsole = new ConsoleHandler();
//        logConsole.setLevel(Level.SEVERE);
//        logger.addHandler(logConsole);
//        try {
//
//            if (!new File("apollo.log").exists()) {
//                new File("apollo.log").createNewFile();
//            }
//
//            FileHandler logFile = new FileHandler("apollo.log", true);
//            logFile.setLevel(Level.FINE);
//            logger.addHandler(logFile);
//
//        } catch (IOException e) {
//            logger.log(Level.SEVERE, "File logger not working.", e);
//        }
//
//    }

    /**
     * Prints out all Tasks from the TaskList.
     *
     * @param taskList The existing TaskList.
     * @param ui       Prints shortlisted Tasks to user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) {
        taskList.sortTaskByDay(taskList);
        ui.printList(taskList);
    }

}
