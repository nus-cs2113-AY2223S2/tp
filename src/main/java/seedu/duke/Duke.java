package seedu.duke;


public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private Inventory inventory;


    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        inventory = new Inventory();
    }

    public void run() {
        while (true) {
            parser.mainParser(inventory);
        }
    }

    public static void main(String[] args) {
        assert false : "dummy assertion set to fail";
        new Duke("data/saved.txt").run();
    }
}
