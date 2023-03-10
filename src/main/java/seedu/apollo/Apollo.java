package seedu.apollo;

import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;

import java.io.IOException;
import java.rmi.UnexpectedException;

/**
 * Main class for running Apollo.
 */
public class Apollo {

    public static final String FILE_PATH = "save.txt";
    private static Storage storage;
    private static TaskList taskList;

    private static ModuleList moduleList;
    private static Ui ui;

    private static ModuleList moduleData = new ModuleList();

    /**
     * Initialises Ui, Storage, and TaskList.
     *
     * @param filePath Location of the local save file.
     */
    public Apollo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.loadTaskList(ui);
            storage.update(taskList);
            moduleData = storage.loadModuleList();
            moduleList = new ModuleList();
            ui.printWelcomeMessage();
        } catch (IOException e) {
            ui.printErrorForIO();
        }
    }

    /**
     * Reads, executes, and prints outputs of user commands continually.
     * Stops after ExitCommand is called.
     *
     * @throws UnexpectedException If command cannot be executed for an unexpected reason.
     */
    public void run() throws IOException {
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command c = Parser.getCommand(fullCommand, ui, taskList.size(), moduleData);
            if (c != null) {
                c.execute(taskList, ui, storage, moduleList);
                isExit = c.isExit;
            }
            ui.showLine();
        }
    }

    /**
     * Initialises and runs Apollo.
     */
    public static void main(String[] args) {
        try {
            new Apollo(FILE_PATH).run();
        } catch (UnexpectedException exception) {
            ui.printUnexpectedException(exception);
        } catch (IOException e) {
            ui.printErrorForIO();
        }
        System.exit(0);
    }

}
