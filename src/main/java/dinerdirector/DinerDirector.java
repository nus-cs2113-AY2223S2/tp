package dinerdirector;

import commands.Command;
import common.Messages;
import manager.DeadlineManager;
import ui.TextUi;
import utils.Parser;

import java.util.ArrayList;

public class DinerDirector {
    private TextUi ui;
    private DeadlineManager deadlineList;

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
        deadlineList = new DeadlineManager(new ArrayList<>());
    }

    private void runCommandLoopUntilExit() {
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.getUserInput();
            Command command = new Parser().parseCommand(userInput);
            assert command instanceof Command : "command should be a command class";
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
