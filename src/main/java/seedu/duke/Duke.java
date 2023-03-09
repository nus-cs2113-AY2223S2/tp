package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandParser;
import seedu.duke.task.TaskList;

import java.util.Scanner;

public class Duke {
    private static Ui ui;
    private static boolean isInUse = true;

    public static void main(String[] args) {
        ui = new Ui();
        //parser = new Parser();
        ui.printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        TaskList taskList = new TaskList();
        while (isInUse) {
            String userInput = in.nextLine();
            Command parsedCommand = CommandParser.parseCommand(userInput);
            parsedCommand.execute(taskList, ui);
            if (parsedCommand.isExit()) {
                in.close();
            }
            isInUse = !parsedCommand.isExit();
        }
    }
}

