/**
 * Takes in function and command
 * Identifies the function called and executes the appropriate function class
 *
 * @param command
 * @param toDo
 */

package seedu.badMaths;

import seedu.badMaths.trigograph.TrigoGraph;
import seedu.badMaths.ui.Ui;
import seedu.badMaths.matrix.Calculator;

public class Command {

    protected String command;
    protected String toDo;
    Notes notes = new Notes(toDo);

    public Command(String command, String toDo) {
        this.command = command;
        this.toDo = toDo;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        try {
            this.command = command;
        } catch (IllegalArgumentException e) {
            Ui.printIncorrectFormatEntered();
        }
    }

    public void setToDo(String toDo){
        this.toDo = toDo;
    }

    public void executeCommand() {
        TrigoGraph trigoGraph = new TrigoGraph(toDo);
        Calculator calculator = new Calculator();

        switch (command) {
        case "Bye":
            System.out.println("Goodbye!");
            break;
        case "Graph":
            trigoGraph.startGraphAnalysis();
            break;
        case "Store":
            notes.setToDo(toDo);
            notes.handleCache(command);
            break;
        case "List":
            notes.setToDo(toDo); // 1
            notes.handleCache(command); // List
            break;
        case "Delete":
            notes.setToDo(toDo); // 2
            notes.handleCache(command); // Delete
            break;
        case "Help":
            HelpManual.readHelpManual();
            break;
        case "Matrix":
            calculator.run(toDo);
            break;
        default:
            break;
        }
    }
}
