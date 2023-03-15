package seedu.duke;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static DataReader dataReader = new DataReader();
    private static Storage storage = new Storage();
    private static UI ui = new UI();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;
        boolean isContinue = true;
        Parser.printGreeting();
        while (isContinue) {
            userInput = in.nextLine();
            ArrayList<University> universities = dataReader.getUniversities();
            ArrayList<Module> modules = storage.getModule();
            ArrayList<Module> allModules = dataReader.getModules();
            isContinue = ui.executeUserCommand(userInput, universities, modules, allModules, storage);
        }
    }
}
