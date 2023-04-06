package seedu.duke;

import seedu.duke.budget.BudgetPlanner;
import seedu.duke.command.Command;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static DataReader dataReader = new DataReader();
<<<<<<< HEAD
    private static Storage storage = new Storage();
    private static DeadlineStorage deadlineStorage = DeadlineStorage.getInstance();
=======
    private static Storage storage = Storage.getInstance();
    private static DeadlineStorage deadlineStorage = new DeadlineStorage();
>>>>>>> 9f1d7a74d2951cee3ae7ae647c9804e5ff39c038

    private static BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
    private static UI ui = new UI();

    private static Parser parser = new Parser();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;
        boolean isContinue = true;
        ArrayList<University> universities = dataReader.getUniversities();
        ArrayList<Module> allModules = dataReader.getModules();
        ArrayList<Module> modules = storage.getModules();
        ArrayList<Deadline> deadlines = deadlineStorage.getDeadlines();
        ui.printGreetingMessage();
        deadlineStorage.compareDeadlines(deadlines);
        while (isContinue) {
            userInput = in.nextLine();
            Command command = parser.parseUserCommand(userInput, universities, modules, allModules, storage,
                    deadlineStorage, budgetPlanner, deadlines);
            command.execute();
            isContinue = !command.getIsExit();
        }
    }
}
