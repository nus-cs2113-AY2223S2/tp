package seedu.duke;

import seedu.duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duck {

    /** Runs the Duke bot */
    private static void runDuck() {
        Ui.greetingMessage();

        ArrayList<Task> tasks = new ArrayList<>();
        Storage.tryLoad(tasks);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        Parser.processCommand(tasks, line, in);

        Ui.exitMessage();
    }

    public static void main(String[] args) {
        runDuck();
    }
}
