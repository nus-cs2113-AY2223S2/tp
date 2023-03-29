/**
 * Takes in function and command
 * Identifies the function called and executes the appropriate function class
 *
 * @param command
 * @param toDo
 */

//@@author WilsonLee2000
package seedu.badMaths;

import seedu.badMaths.trigograph.TrigoGraph;
import seedu.badMaths.ui.Ui;
import seedu.badMaths.matrix.Calculator;

public class Command {
    private static final String filePath = "data/notes.txt";
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

    public void executeCommand(NotesList notes) {
        TrigoGraph trigoGraph = new TrigoGraph(toDo);
        Calculator calculator = new Calculator();
        Quadratic quadratic = new Quadratic(toDo);

        switch (command) {
        case "Bye":
            System.out.println("Goodbye!");
            break;
        case "Graph":
            trigoGraph.startGraphAnalysis();
            break;
        case "Store":
            notes.add(toDo);
            Ui.printAddNote(toDo, notes.getSize());
            Storage.saveFile(filePath, notes.getAll());
            break;
        case "List":
            if(!toDo.equals("Invalid todo")) {
                int index = Integer.parseInt(toDo) - 1;
                notes.review(index);
                Ui.printSpecificNote(index, notes.getAll());
                break;
            }
            Ui.printNotes(notes.getAll());
            break;
        case "Mark":
            int markIndex = Integer.parseInt(toDo) - 1;
            notes.markAsDone(markIndex);
            Ui.printMark(notes.getText(markIndex));
            break;
        case "Unmark":
            int UnmarkIndex = Integer.parseInt(toDo) - 1;
            notes.markAsUndone(UnmarkIndex);
            Ui.printUnmark(notes.getText(UnmarkIndex));
            break;
        case "Delete":
            int deleteIndex = Integer.parseInt(toDo) - 1;
            Ui.printDelete(notes.getText(deleteIndex), notes.getSize());
            notes.remove(deleteIndex);
            break;
        case "Low":
            int lowIndex = Integer.parseInt(toDo) - 1;
            notes.setPriority(lowIndex, NotePriority.Priority.LOW);
            Ui.printPriority(lowIndex, notes.getAll());
            break;
        case "Medium":
            int mediumIndex = Integer.parseInt(toDo) - 1;
            notes.setPriority(mediumIndex, NotePriority.Priority.MEDIUM);
            Ui.printPriority(mediumIndex, notes.getAll());
            break;
        case "High":
            int highIndex = Integer.parseInt(toDo) - 1;
            notes.setPriority(highIndex, NotePriority.Priority.HIGH);
            Ui.printPriority(highIndex, notes.getAll());
            break;
        case "FindInfo":
            String keyword = toDo;
            Ui.printFindNotes(notes.relevantInfo(keyword));
            break;
        case "FindPrior":
            String priorityStr = toDo;
            Ui.printFindNotes(notes.relevantPriority(priorityStr));
            break;
        case "FindMark":
            Ui.printFindNotes(notes.relevantMarked());
            break;
        case "FindUnmark":
            Ui.printFindNotes(notes.relevantUnmarked());
            break;
        case "Rank":
            switch (toDo) {
            case "Review Count":
                notes.rankByReviewCount();
                Ui.printNotesByReviewCount(notes.getAll());
                break;
            case "Priority":
                notes.rankByPriority();
                Ui.printNotesByPriority(notes.getAll());
                break;
            default:
                break;
            }
            break;
        case "Clear":
            notes.reset();
            Storage.clearFile(filePath);
            break;
        case "Help":
            HelpManual.readHelpManual();
            break;
        case "Matrix":
            calculator.run(toDo);
            break;
        case "Quadratic":
            quadratic.solveQuadratic();
            break;
        default:
            break;
        }
    }
}
