package seedu.duke;


public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
    }

    public void run() {
        while (true) {
            parser.mainParser();
        }
    }

    public static void main(String[] args) {
        new Duke("data/saved.txt").run();
    }
}
