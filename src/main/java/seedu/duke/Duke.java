package seedu.duke;

import seedu.duke.command.Command;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static DataReader dataReader = new DataReader();
    private static Storage storage = new Storage();
    private static UI ui = new UI();

    private static Parser parser = new Parser();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;
        boolean isContinue = true;
        ui.printGreetingMessage();
        while (isContinue) {
            userInput = in.nextLine();
            ArrayList<University> universities = dataReader.getUniversities();
            ArrayList<Module> modules = storage.getModules();
            ArrayList<Module> allModules = dataReader.getModules();
            Command command = parser.handleUserCommand(userInput, universities, modules, allModules, storage);
            command.execute();
            isContinue = !command.getIsExit();
        }
    }
}
