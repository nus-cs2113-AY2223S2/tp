package seedu.duke;

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
    private static void performUserRequest(String[] parsedCommand, Scanner in, ArrayList<Task> tasks) throws NumberFormatException {
        switch (parsedCommand[0]) {
            case "add":
                if (!parsedCommand[2].isEmpty()) {
                    tasks.add(new Task(parsedCommand[1], parsedCommand[2]));
                    ui.printAddTaskNotification(parsedCommand[1], parsedCommand[2]);
                    break;
                } else {
                    break;
                }
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
            case "editdeadline":
                tasks.get(Integer.parseInt(parsedCommand[1]) - 1).editDeadline(parsedCommand[2]);
                ui.printEditDeadlineNotification(tasks.get(Integer.parseInt(parsedCommand[1]) - 1)
                        .getDescription(), parsedCommand[2]);
                break;
            case "bye":
                in.close();
                ui.printGoodbyeMessage();
                isInUse = false;
                break;
            case "param error":
                ui.printParametersError();
                break;
            default:
                ui.printErrorMessage();
        }
    }
}

