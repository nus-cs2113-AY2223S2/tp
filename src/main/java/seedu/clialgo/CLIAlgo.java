package seedu.clialgo;

import seedu.clialgo.command.Command;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.storage.FileManager;

//@@author heejet
/**
 * A <code>CLIAlgo</code> object is used to run the entire CLIAlgo application.
 * It contains a <code>Ui</code> object, a <code>TopicManager</code> object, a <code>FileManager</code>, a
 * <code>Parser</code> and a <code>Buffer</code> object to execute various commands from the user.
 */
public class CLIAlgo {
    /** The path of the folder used to store information in CLIAlgo. */
    private static final String DATA_PATH = "./data";

    /** An object to handle the user interaction. */
    private final Ui ui;

    /** An object to handle the CS2040CFiles added by the user. */
    private final TopicManager topicManager;

    /** An object to handle saving and loading of data stored within CLIAlgo. */
    private final FileManager fileManager;

    /** An object to make sense of the commands keyed in by the user */
    private final Parser parser;

    /** An object to store files that the user wants to export */
    private final Buffer buffer;

    public CLIAlgo() {
        ui = new Ui();
        topicManager = new TopicManager();
        fileManager = new FileManager(DATA_PATH, topicManager.getTopicNames());
        parser = new Parser();
        buffer = Buffer.getInstance();
        ui.printWelcomeMessage();
    }

    private void initialize() {
        fileManager.initialize();
        topicManager.initialize(fileManager.decodeAll());
    }

    /** Continuously reads in the user input until the exit command is executed */
    private void run() {
        while (true) {
            String fullCommand = ui.getUserInput();
            Command command = parser.parse(fullCommand, topicManager);
            command.execute(topicManager, ui, fileManager, buffer);
        }
    }

    public static void main(String[] args) {
        CLIAlgo cliAlgo = new CLIAlgo();
        cliAlgo.initialize();
        cliAlgo.run();
    }
}
