package dinerdirector;

import commands.Command;
import common.Messages;
import manager.Meeting;
import ui.TextUi;
import utils.Parser;

public class DinerDirector {
    public static int indexOfMeetings = 0;
    private static final int MAX_NUM_MEETINGS = 100;
    public static Meeting[] meetings = new Meeting[MAX_NUM_MEETINGS];
    private TextUi ui;

    //Solution below adopted from:
    //https://github.com/nus-cs2113-AY2223S2/personbook/blob/main/src/main/java/seedu/personbook/Main.java
    //Implemented the main, run, start method in the similar way.
    public static void main(String[] args) {
        new DinerDirector().run();
    }

    private void run() {
        start();
        runCommandLoopUntilExit();
        exit();
    }

    /**
     * Start of program.
     * Initializing of Ui, creating of storage/loading of file will be done and checked here.
     */
    private void start() {
        this.ui = new TextUi();
        ui.printBanner();
    }

    private void runCommandLoopUntilExit() {
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.getUserInput();
            Command command = new Parser().parseCommand(userInput);
            command.execute(ui);
            isExit = command.isExit();
        }
        ui.printMessage(Messages.MESSAGE_COMMAND_EXIT);
    }

    /**
     * Exits the program.
     */
    private void exit() {
        System.exit(0);
    }


}
