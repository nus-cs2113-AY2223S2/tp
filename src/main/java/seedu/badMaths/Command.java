/**
 * Takes in function and command
 * Identifies the function called and executes the appropriate function class
 *
 * @param function
 * @param command
 */

package seedu.badMaths;

public class Command {

    protected String function;
    protected String command;

    public Command (String function, String command) {
        this.function = function;
        this.command = command;
    }

    public void executeCommand() {
            switch (function) {
            case "bye":
                System.out.println("Goodbye!");
                break;
            case "Graph":
                TrigoGraph trigoGraph = new TrigoGraph(command);
                trigoGraph.splitAmplitude();
                break;
            default:
                break;
            }
    }
}
