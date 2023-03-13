package seedu.duke;

// import java.util.Scanner;
//import seedu.duke.ui.Ui;
//import seedu.duke.EventList;
//import seedu.duke.parser.Parser;


public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private final EventList eventTracker;
    private final Ui ui;
    private final Parser parser;
    public Duke (){
        ui = new Ui();
        eventTracker = new EventList();
        parser = new Parser();
    }
    public void run(){
        ui.showWelcome();
        ui.getUserCommand(eventTracker);
    }

    public static void main(String[] args) {
        Duke nusPlanner = new Duke();
        nusPlanner.run();
    }
}
