package seedu.apollo.command.module;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.storage.Storage;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.ui.Ui;
import seedu.apollo.task.TaskList;
import seedu.apollo.utils.LoggerInterface;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ListModuleCommand extends Command { //implements LoggerInterface {
    private static Logger logger = Logger.getLogger("ListModuleCommand");

    public ListModuleCommand() {
        super(logger);
//        setUpLogger();
    }

//    /**
//     * Sets up logger for ListModuleCommand class.
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


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) {
        assert (moduleList != null) : "ListModuleCommand: ModuleList should not be null!";
        ui.printModuleList(moduleList);
    }
}
