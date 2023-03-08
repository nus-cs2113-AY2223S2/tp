package seedu.duke;

import seedu.duke.command.Parser;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;

public class Duke {
    private static Ui ui;
    private static Parser parser;
    private static boolean isInUse = true;

    public static void main(String[] args) {
        ui = new Ui();
        parser = new Parser();
        ui.printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (isInUse) {
            String userInput = in.nextLine();
            String[] parsedCommand = parser.parseCommand(userInput);
            performUserRequest(parsedCommand, in, tasks);
        }
    }
    private static void performUserRequest(String[] parsedCommand, Scanner in, ArrayList<Task> tasks) {
        switch (parsedCommand[0]) {
        case "add":
            tasks.add(new Task(parsedCommand[1]));
            ui.printAddTaskNotification(parsedCommand[1]);
            break;
        case "mark":
            tasks.get(Integer.parseInt(parsedCommand[1]) - 1).setDone(true);
            ui.printMarkTaskNotification(tasks.get(Integer.parseInt(parsedCommand[1]) - 1)
                    .getDescription());
            break;
        case "unmark":
            tasks.get(Integer.parseInt(parsedCommand[1]) - 1).setDone(false);
            ui.printUnmarkTaskNotification(tasks.get(Integer.parseInt(parsedCommand[1]) - 1)
                    .getDescription());
            break;
        case "list":
            ui.listTasks(tasks);
            break;
        case "bye":
            in.close();
            ui.printGoodbyeMessage();
            isInUse = false;
            break;
        default:
            ui.printErrorMessage();
        }
    }
}

