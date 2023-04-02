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

    public boolean isInvalidTodo(String todo) {
        return todo.equals("Invalid todo");
    }

    public boolean isInvalidIndex(int index, NotesList notes) {
        return (index < 0 && index > notes.getSize());
    }

    public void executeCommand(NotesList notes) {
        TrigoGraph trigoGraph = new TrigoGraph(toDo);
        Calculator calculator = new Calculator();
        Quadratic quadratic = new Quadratic(toDo);
        try {
            //@@author WilsonLee2000
            assert (command.equals("Bye") || command.equals("Graph") || command.equals("Store") ||
                    command.equals("List") || command.equals("Delete") || command.equals("Mark") ||
                    command.equals("Unmark") || command.equals("Low") || command.equals("Medium") ||
                    command.equals("High") || command.equals("FindInfo") || command.equals("FindPrior") ||
                    command.equals("FindMark") || command.equals("FindUnmark") || command.equals("Rank") ||
                    command.equals("Clear") || command.equals("Help") || command.equals("Matrix") ||
                    command.equals("Quadratic")) : "input has incorrect format required";
            switch (command) {
            //@@author WilsonLee2000
            case "Bye":
                if(!isInvalidTodo(toDo)) {
                    throw new IllegalTodoException();
                }
                System.out.println("Goodbye!");
                break;
            case "Graph":
                trigoGraph.startGraphAnalysis();
                break;
            //@@author WilsonLee2000
            case "Store":
                if (isInvalidTodo(toDo)) {
                    throw new IllegalTodoException();
                }
                notes.add(toDo);
                Ui.printAddNote(toDo, notes.getSize());
                Storage.saveFile(filePath, notes.getAll());
                break;
            //@@author WilsonLee2000
            case "List":
                if (isInvalidTodo(toDo)) {
                    Ui.printNotes(notes.getAll());
                    break;
                }
                int index = Integer.parseInt(toDo) - 1;
                if (isInvalidIndex(index, notes)) {
                    throw new IllegalIndexException();
                }
                notes.review(index);
                Ui.printSpecificNote(index, notes.getAll());
                break;
            //@@author WilsonLee2000
            case "Delete":
                int deleteIndex = Integer.parseInt(toDo) - 1;
                if (isInvalidIndex(deleteIndex, notes)) {
                    throw new IllegalIndexException();
                }
                Ui.printDelete(notes.getText(deleteIndex), notes.getSize());
                notes.remove(deleteIndex);
                break;
            //@@author ZiqiuZeng
            case "Mark":
                int markIndex = Integer.parseInt(toDo) - 1;
                if (isInvalidIndex(markIndex, notes)) {
                    throw new IllegalIndexException();
                }
                notes.markAsDone(markIndex);
                Ui.printMark(notes.getText(markIndex));
                break;
            //@@author ZiqiuZeng
            case "Unmark":
                int unmarkIndex = Integer.parseInt(toDo) - 1;
                if (isInvalidIndex(unmarkIndex, notes)) {
                    throw new IllegalIndexException();
                }
                notes.markAsUndone(unmarkIndex);
                Ui.printUnmark(notes.getText(unmarkIndex));
                break;
            //@@author ZiqiuZeng
            case "Low":
                int lowIndex = Integer.parseInt(toDo) - 1;
                if (isInvalidIndex(lowIndex, notes)) {
                    throw new IllegalIndexException();
                }
                notes.setPriority(lowIndex, NotePriority.Priority.LOW);
                Ui.printPriority(lowIndex, notes.getAll());
                break;
            //@@author ZiqiuZeng
            case "Medium":
                int mediumIndex = Integer.parseInt(toDo) - 1;
                if (isInvalidIndex(mediumIndex, notes)) {
                    throw new IllegalIndexException();
                }
                notes.setPriority(mediumIndex, NotePriority.Priority.MEDIUM);
                Ui.printPriority(mediumIndex, notes.getAll());
                break;
            //@@author ZiqiuZeng
            case "High":
                int highIndex = Integer.parseInt(toDo) - 1;
                if (isInvalidIndex(highIndex, notes)) {
                    throw new IllegalIndexException();
                }
                notes.setPriority(highIndex, NotePriority.Priority.HIGH);
                Ui.printPriority(highIndex, notes.getAll());
                break;
            //@@author ZiqiuZeng
            case "FindInfo":
                if (isInvalidTodo(toDo)) {
                    throw new IllegalTodoException();
                }
                String keyword = toDo;
                Ui.printFindNotes(notes.relevantInfo(keyword));
                break;
            //@@author ZiqiuZeng
            case "FindPrior":
                if (isInvalidTodo(toDo)) {
                    throw new IllegalTodoException();
                }
                String priorityStr = toDo;
                Ui.printFindNotes(notes.relevantPriority(priorityStr));
                break;
            //@@author ZiqiuZeng
            case "FindMark":
                Ui.printFindNotes(notes.relevantMarked());
                break;
            //@@author ZiqiuZeng
            case "FindUnmark":
                Ui.printFindNotes(notes.relevantUnmarked());
                break;
            //@@author ZiqiuZeng
            case "Rank":
                if (isInvalidTodo(toDo) || !(toDo.equals("Review Count") || toDo.equals("Priority"))) {
                    throw new IllegalArgumentException();
                }
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
            //@@author ZiqiuZeng
            case "Clear":
            //@@author WilsonLee2000
                if(!isInvalidTodo(toDo)) {
                    throw new IllegalTodoException();
                }
            //@@author ZiqiuZeng
                notes.reset();
                Storage.clearFile(filePath);
                break;
            //@@author Khooyourun
            case "Help":
                HelpManual.readHelpManual();
                break;
            //@@author 0nandon
            case "Matrix":
                calculator.run(toDo);
                break;
            //@@author Khooyourun
            case "Quadratic":
                quadratic.solveQuadratic();
                break;
            default:
                break;
            }
        } catch (IllegalIndexException e) {
            System.out.println("Oops! This note does not exist. Please try again.");
        } catch (IllegalTodoException e) {
            Ui.printIncorrectFormatEntered();
        }

    }
}

