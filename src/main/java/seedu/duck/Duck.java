package seedu.duck;

import seedu.duck.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duck {
    /** Runs the Duck bot */
    private static void runDuck() throws IOException {
        Ui.greetingMessage();

        ArrayList<Task> tasks = new ArrayList<>();
        Storage.tryLoad(tasks);
        Ui.displayUpcomingDeadline(tasks);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        Parser.processCommand(tasks, line, in);

        Ui.exitMessage();
    }

    public static void main(String[] args) throws IOException {
        runDuck();
    }
}
