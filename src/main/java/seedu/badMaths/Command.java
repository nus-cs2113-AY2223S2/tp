/**
 * Takes in function and command
 * Identifies the function called and executes the appropriate function class
 *
 * @param command
 * @param toDo
 */

package seedu.badMaths;

import seedu.badMaths.temptrigograph.TrigoGraph;
import seedu.badMaths.tempui.Ui;

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
        Notes notes = new Notes(toDo);
        TrigoGraph trigoGraph = new TrigoGraph(toDo);

        switch (command) {
        case "Bye":
            System.out.println("Goodbye!");
            break;
        case "Graph":
            trigoGraph.startGraphAnalysis();
            break;
        case "Notes":
            notes.setToDo(toDo);
            notes.handleCache(command);
            break;
        case "List":
            notes.handleCache(command);
            break;
        case "Help":
            HelpManual.readHelpManual();
            break;
        default:
            break;
        }
    }
}
