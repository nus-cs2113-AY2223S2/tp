package seedu.pettracker.ui;

import java.util.Scanner;

/**
 * Class to handle all output from commands and get user input from Standard Input.
 */
public class Ui {
    private static final String DIVIDER = "\n_________________________________________________________________________"
            + "_______________________________\n";
    private static final String FILE_IO_ERROR = "ERROR: Error with File IO";
    private static final String NON_INTEGER_ERROR = "ERROR: Input should be an integer";
    private static final String NON_POSITIVE_INTEGER_ERROR = "ERROR: Integer provided should be above 0";
    private static final String INVALID_STAT_ERROR = "ERROR: The only valid stats are type, age, or weight.";
    private static final String PET_NOT_FOUND_ERROR = "ERROR: Pet not Found";
    private static final String EMPTY_PET_NAME_ERROR = "ERROR: Pet Name is empty";
    private static final String DUPLICATE_PET_ERROR = "ERROR: Pet already exists";
    private static final String NON_INTEGER_FILE_ERROR = "ERROR: Pet save file has non-integer values for age" +
            "/weight. File loading aborted.";
    private static final String NON_POSITIVE_INTEGER_FILE_ERROR = "ERROR: Pet save file has non-positive " +
            "values for age/weight. File loading aborted.";
    private static final String INVALID_STAT_FILE_ERROR = "ERROR: Pet save file has invalid stats. File " +
            "loading aborted.";
    private static final String PET_NOT_FOUND_FILE_ERROR = "ERROR: Pet save file has stat that belongs to a " +
            "pet that does not exist. File loading aborted.";
    private static final String EMPTY_PET_NAME_FILE_ERROR = "ERROR: Pet save file has empty pet name. File loading " +
            "aborted.";
    private static final String DUPLICATE_PET_FILE_ERROR = "ERROR: Pet save file has duplicate pet names. File " +
            "loading aborted.";
    private static final String INVALID_SEPARATOR_PET_FILE_ERROR = "ERROR: Pet save file has invalid separator/" +
            "invalid number of separator. File loading aborted.";
    private static final String INVALID_SEPARATOR_TASK_FILE_ERROR = "ERROR: Task save file has invalid " +
            "separator/invalid number of separator. File loading aborted.";
    private static final String INVALID_DATE_FILE_ERROR = "ERROR: Task save file has invalid date format. " +
            "File loading aborted.";
    private static final String EMPTY_TASK_NAME_FILE_ERROR = "ERROR: Task save file has empty task name. File " +
            "loading aborted.";
    private static final String INVALID_MARK_TASK_SYMBOL_FILE_ERROR = "ERROR: Task save file has invalid mark task " +
            "symbol. File loading aborted.";

    private Scanner in;

    /**
     * Constructor to initialize Scanner object.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Message that welcomes the user when program loads.
     */
    public void showWelcomeMessage() {
        System.out.println("Welcome to the Pet Health Tracker!");
    }

    /**
     * Message that prints when the program exits and closes the Scanner.
     */
    public void showEndingMessage() {
        in.close();
        System.out.println("Goodbye! See you soon.");
    }

    /**
     * Message that prints when a new pet is added.
     *
     * @param petName String containing the name of the added pet.
     */
    public void addPetCommandMessage(String petName) {
        System.out.println("Successfully added new pet: " + petName);
    }

    /**
     * Message that prints when a pet's stats are edited.
     *
     * @param petName String containing the name of the added pet.
     */
    public void editStatCommandMessage(String petName, String stat, String statValue) {
        System.out.println("Successfully updated " + petName + "'s " + stat + " to " + statValue);
    }

    /**
     * Message that prints when a new stat is added for a pet.
     *
     * @param petName   String containing the name of the corresponding pet.
     * @param statName  String indicating the name of the added stat.
     * @param statValue String indicating the value for that stat.
     */
    public void addStatCommandMessage(String petName, String statName, String statValue) {
        System.out.println("Updated " + statName + " to " + statValue + " for " + petName);
    }

    /**
     * Message that prints when a pet is removed.
     *
     * @param petName String representing the name of the removed pet.
     */
    public void removePetCommandMessage(String petName) {
        System.out.println("Successfully removed pet: " + petName);
    }

    /**
     * Message that prints when a stat is removed from a pet.
     *
     * @param petName  String containing name of the corresponding pet.
     * @param statName String containing name of the stat that was removed.
     */
    public void removeStatCommandMessage(String petName, String statName) {
        System.out.println("Successfully removed " + statName + " from " + petName);
    }


    /**
     * Gets input from the user from Standard Input.
     *
     * @return String containing the input from the user.
     */
    public String getUserInput() {
        System.out.println();
        return in.nextLine();
    }

    /**
     * Message that prints when a task is added to the task list.
     */
    public void addTodoCommandMessage() {
        System.out.println("Added new task to list");
    }

    /**
     * Message that prints when a task is removed from the task list.
     *
     * @param taskNumber Integer representing the task number of the removed task.
     */
    public void removeTaskCommandMessage(int taskNumber) {
        System.out.println("Successfully removed task " + taskNumber);
    }

    public void editTaskCommandMessage(int taskNumber, String newDescription) {
        System.out.println("Updated task " + taskNumber + " to " + newDescription + ".");
    }

    /**
     * Message that prints before listing all tasks.
     */
    public void listTasksCommandMessage() {
        System.out.println("Here are your tasks:");
    }

    /**
     * Message that prints before listing a schedule.
     */
    public void scheduleCommandMessage() {
        System.out.println("Here is your schedule: ");
    }

    /**
     * Message that prints when a task is marked as done.
     */
    public void markTaskCommandMessage() {
        System.out.println("Task marked as done");
    }

    /**
     * Message that prints when a task is marked as not done.
     */
    public void unmarkTaskCommandMessage() {
        System.out.println("Task marked as not done");
    }

    /**
     * Message that prints when the storage experiences IO error.
     */
    public void printFileIOErrorMessage() {
        System.out.println(FILE_IO_ERROR);
    }

    public void printNonIntegerMessage() {
        System.out.println(NON_INTEGER_ERROR);
    }

    public void printIntegerNotPositiveMessage() {
        System.out.println(NON_POSITIVE_INTEGER_ERROR);
    }

    public void printInvalidStatMessage() {
        System.out.println(INVALID_STAT_ERROR);
    }

    public void petNotFoundMessage() {
        System.out.println(PET_NOT_FOUND_ERROR);
    }

    public void petNameEmptyMessage() {
        System.out.println(EMPTY_PET_NAME_ERROR);
    }

    public void duplicatePetMessage() {
        System.out.println(DUPLICATE_PET_ERROR);
    }

    /**
     * Print out help for all commands
     */
    public void showHelpMessage() {
        System.out.println("Here is Pet Tracker's Help Page");
        System.out.println("Words in uppercase is used to show the user's input\n");
        System.out.println(DIVIDER);
        showPetHelp();
        showTaskHelp();
        showExitCommandHelp();
        System.out.println("Hope this helps!");
    }

    /**
     * Print out help for pet related commands
     */
    private void showPetHelp() {
        showAddPetCommandHelp();
        showRemovePetCommandHelp();
        showPetListCommandHelp();
        showAddStatCommandHelp();
        showRemoveStatCommandHelp();
        showEditStatCommandHelp();
    }

    /**
     * Print out help for task related commands
     */
    private void showTaskHelp() {
        showAddTaskCommandHelp();
        showRemoveTaskCommandHelp();
        showTaskListCommandHelp();
        showEditTaskCommandHelp();
        showScheduleCommandHelp();
        showMarkTaskCommandHelp();
        showUnMarkTaskCommandHelp();
    }

    /**
     * Print out help for add-pet command
     */
    private void showAddPetCommandHelp() {
        System.out.println("Command: add-pet NAME");
        System.out.println("Description: Adds a pet to the Pet List");
        System.out.println("Example: add-pet Bob");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for remove-pet command
     */
    private void showRemovePetCommandHelp() {
        System.out.println("Command: remove-pet NAME");
        System.out.println("Description: Removes a pet from the Pet List");
        System.out.println("Example: remove-pet Bob");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for list command
     */
    private void showPetListCommandHelp() {
        System.out.println("Command: list");
        System.out.println("Description: View the current Pet List and total number of pets");
        System.out.println("Example: list");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for add-stat command
     */
    private void showAddStatCommandHelp() {
        System.out.println("Command: add-stat NAME STAT VALUE");
        System.out.println("Description: Adds a pet to the Pet List");
        System.out.println("Example: add-stat Bob Weight 30");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for remove-stat command
     */
    private void showRemoveStatCommandHelp() {
        System.out.println("Command: remove-stat NAME STAT");
        System.out.println("Description: Removes a stat from a pet in the Pet List");
        System.out.println("Example: remove-stat Bob Weight");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for edit-stat command
     */
    private void showEditStatCommandHelp() {
        System.out.println("Command: edit-stat NAME STAT");
        System.out.println("Description: Edit a stat from a pet in the Pet List");
        System.out.println("Example: edit-stat Bob Weight 20");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for add-task command
     */
    private void showAddTaskCommandHelp() {
        System.out.println("Command: add-task NAME or add-task Name /by DATE");
        System.out.println("Description: Adds a task to the Task List");
        System.out.println("Example: add-task Buy food /by 2021-03-01");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for remove-task command
     */
    private void showRemoveTaskCommandHelp() {
        System.out.println("Command: remove-task NUMBER");
        System.out.println("Description: Removes a task from the Task List");
        System.out.println("Example: remove-task 1");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for list-tasks command
     */
    private void showTaskListCommandHelp() {
        System.out.println("Command: list-tasks");
        System.out.println("Description: View the current Task List and total number of Tasks");
        System.out.println("Example: list-tasks");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for edit-task command
     */
    private void showEditTaskCommandHelp() {
        System.out.println("Command: edit-task NUMBER VALUE or edit-task NUMBER VALUE /by DATE");
        System.out.println("Description: Edit the task description or date");
        System.out.println("Example: edit-task 2 feed the cat");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for schedule command
     */
    private void showScheduleCommandHelp() {
        System.out.println("Command: schedule");
        System.out.print("Description: View the current list of tasks with an associated deadline, ");
        System.out.println("in order of deadline");
        System.out.println("Example: schedule");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for mark-task command
     */
    private void showMarkTaskCommandHelp() {
        System.out.println("Command: mark-task NUMBER");
        System.out.println("Description: Marks a task as done in the Task List");
        System.out.println("Example: mark-task 2");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for unmark-task command
     */
    private void showUnMarkTaskCommandHelp() {
        System.out.println("Command: unmark-task NUMBER");
        System.out.println("Description: Marks a task as not done in the Task List");
        System.out.println("Example: unmark-task 2");
        System.out.println(DIVIDER);
    }

    /**
     * Print out help for exit command
     */
    private void showExitCommandHelp() {
        System.out.println("Command: exit");
        System.out.println("Description: Exits the program");
        System.out.println("Example: exit");
        System.out.println(DIVIDER);
    }

    public void taskNumberOutOfBoundsMessage() {
        System.out.println("Task number does not exist.");
    }

    public void invalidTaskNumber() {
        System.out.println("Invalid task number.");
    }
}
