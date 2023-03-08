/**
 * Takes in function and command
 * Identifies the function called and executes the appropriate function class
 *
 * @param command
 * @param toDo
 */

package seedu.badMaths;

public class Command {

    protected String command;
    protected String toDo;
    protected boolean isRunning = true;

    public Command(String command, String toDo) {
        this.command = command;
        this.toDo = toDo;
    }

    public void executeCommand() {
        while (isRunning) {
            switch (command) {
            case "Bye":
                System.out.println("Goodbye!");
                isRunning = false;
                break;
            case "Graph":
                TrigoGraph trigoGraph = new TrigoGraph(command);
                trigoGraph.startGraphAnalysis();
                break;
            default:
                break;
            }
        }
    }
}
