package seedu.clialgo;

import seedu.clialgo.command.Command;
import seedu.clialgo.storage.FileManager;

public class CLIAlgo {
    private static final String DATA_PATH = ".\\data";
    private final Ui ui;
    private final TopicManager topicManager;
    private final FileManager fileManager;
    private final Parser parser;

    public CLIAlgo() {
        ui = new Ui();
        topicManager = new TopicManager();
        fileManager = new FileManager(DATA_PATH, topicManager.getTopicNames());
        parser = new Parser();
        ui.printWelcomeMessage();
    }

    /** Continuously reads in the user input until the exit command is executed */
    private void run() {
        fileManager.initialize();
        topicManager.initialize(fileManager.decodeAll());
        while (true) {
            String fullCommand = ui.getUserInput();
            Command command = parser.parse(fullCommand, topicManager);
            command.execute(topicManager, ui, fileManager);
        }
    }

    public static void main(String[] args) {
        CLIAlgo cliAlgo = new CLIAlgo();
        cliAlgo.run();
    }
}
