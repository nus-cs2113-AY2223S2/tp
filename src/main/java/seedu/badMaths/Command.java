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

    public Command(String command, String toDo) {
        this.command = command;
        this.toDo = toDo;
    }

    public String getCommand() {
        return command;
    }

    public void executeCommand() {
        switch (command) {
        case "Bye":
            System.out.println("Goodbye!");
            break;
        case "Graph":
            TrigoGraph trigoGraph = new TrigoGraph(toDo);
            trigoGraph.startGraphAnalysis();
            break;
        default:
            break;
        }
    }
}
