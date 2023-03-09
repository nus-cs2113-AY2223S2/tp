package seedu.duke;

import java.util.Scanner;

public class Duke {
    private DukeUI ui;
    public Duke() {
        this.ui = new DukeUI();
        ui.printGreeting();
    }
    public void run() {
        boolean isExit = false;
        while(!isExit) {
            //todo: add command
            isExit = true;
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
