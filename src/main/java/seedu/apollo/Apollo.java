package seedu.apollo;

import seedu.apollo.command.Command;
import seedu.apollo.module.Module;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.ArrayList;

/**
 * Main class for running Apollo.
 */
public class Apollo {

    public static final String FILE_PATH = "save.txt";
    private static Storage storage;
    private static TaskList tasks;

    private static ModuleList moduleList;
    private static Ui ui;

    private static ArrayList<Module> moduleData = new ArrayList<Module>();
    private static ArrayList<Module> modules = new ArrayList<Module>();

    /**
     * Initialises Ui, Storage, and TaskList.
     *
     * @param filePath Location of the local save file.
     */
    public Apollo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(ui));
            moduleList = new ModuleList(modules);
            storage.update(tasks);
            moduleData = storage.loadModuleList();
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
            Command c = Parser.getCommand(fullCommand, ui, tasks.getSize(), moduleData);
            if (c != null) {
                c.execute(tasks, ui, storage, moduleList);
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
