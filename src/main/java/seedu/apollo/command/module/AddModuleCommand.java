package seedu.apollo.command.module;

import seedu.apollo.exception.module.DuplicateModuleException;
import seedu.apollo.storage.Storage;
import seedu.apollo.ui.Ui;
import seedu.apollo.command.Command;
import seedu.apollo.exception.module.InvalidModule;
import seedu.apollo.module.Module;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class AddModuleCommand extends Command {
    private static Logger logger = Logger.getLogger("AddModuleCommand");
    private Module module;


    /**
     * Constructor for AddModuleCommand.
     *
     * @param moduleCode The module code of the module to be added.
     * @param allModules The list of all modules.
     * @throws InvalidModule If the module code is invalid.
     */
    public AddModuleCommand(String moduleCode, ModuleList allModules) throws InvalidModule {

        AddModuleCommand.setUpLogger();

        assert (moduleCode != null) : "AddModuleCommand: Module code should not be null!";
        assert (allModules != null) : "AddModuleCommand: Module list should not be null!";
        Module toAdd = allModules.findModule(moduleCode);

        if (toAdd == null) {

            throw new InvalidModule();
        }

        module = toAdd;

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

    public boolean isAdded(ModuleList moduleList, Module module) {
        return moduleList.contains(module);

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList) {
        try {
            if ((module != null) && (!isAdded(moduleList, module))) {
                assert module != null;
                moduleList.add(module);
                moduleList.sortModules();
                ui.printAddModuleMessage(module);

            } else if (isAdded(moduleList, module)) {
                throw new DuplicateModuleException();
            }
            storage.updateModule(moduleList);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Exception", e);
            ui.printErrorForIO();

        } catch (DuplicateModuleException e) {
            ui.printDuplicateModule();
        }
    }
}
