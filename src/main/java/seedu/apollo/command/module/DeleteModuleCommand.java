package seedu.apollo.command.module;
import seedu.apollo.storage.Storage;
import seedu.apollo.command.Command;
import seedu.apollo.exception.module.ModuleNotFoundException;
import seedu.apollo.module.Module;
import seedu.apollo.module.ModuleList;
import seedu.apollo.ui.Ui;
import seedu.apollo.task.TaskList;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

/**
     * For {@code delmod} command.
     * Delete Module Command class that finds the module using moduleCode and removes it from the ModuleList
     */
public class DeleteModuleCommand extends Command {
    private static Logger logger = Logger.getLogger("DeleteModuleCommand");
    protected String moduleCode;
    public DeleteModuleCommand(String moduleCode){
        this.moduleCode = moduleCode;
        DeleteModuleCommand.setUpLogger();
    }
    public static void setUpLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        ConsoleHandler logConsole = new ConsoleHandler();
        logConsole.setLevel(Level.SEVERE);
        logger.addHandler(logConsole);
        try {

            if (!new File("apollo.log").exists()) {
                new File("apollo.log").createNewFile();
            }

            FileHandler logFile = new FileHandler("apollo.log", true);
            logFile.setLevel(Level.FINE);
            logger.addHandler(logFile);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList){
        assert moduleList != null : "ModuleList should not be null!";
        assert taskList != null : "TaskList should not be null!";
        assert storage != null : "Storage should not be null!";
        try {
            Module toDelete = moduleList.findModule(moduleCode);
            if (toDelete == null) {
                throw new ModuleNotFoundException();
            }
            moduleList.remove(toDelete);
            //update storage later
            ui.printModuleDeleteMessage(moduleCode);
            storage.updateModule(moduleList);
        } catch (ModuleNotFoundException e) {
            ui.printUnsuccessfulModuleDelete(moduleCode);
        } catch (IOException e) {
            ui.printErrorForIO();
        }
    }

}
