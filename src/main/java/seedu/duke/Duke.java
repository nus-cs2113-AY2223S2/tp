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
//        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        // data = new DataReader();
        Scanner in = new Scanner(System.in);
        in.nextLine();
//        System.out.println("What is your name?");
//        System.out.println("Hello " + in.nextLine());
        String userInput;
        boolean isContinue = true;
        while (isContinue) {
            userInput = in.nextLine();
            ArrayList<University> universities = dataReader.getUniversities();
            ArrayList<Module> modules = storage.getModule();
            ArrayList<Module> allModules = dataReader.getModules();
            isContinue = ui.executeUserCommand(userInput, universities, modules, allModules, storage);
        }
    }
}
