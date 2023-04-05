/**
 * Takes in function and command
 * Identifies the function called and executes the appropriate function class
 *
 * @param command
 * @param toDo
 */

package seedu.badmaths;
import seedu.badmaths.commands.*;
import seedu.badmaths.note.NotesList;
import seedu.badmaths.storage.NotesFileCleaner;
import seedu.badmaths.trigograph.TrigoGraph;
import seedu.badmaths.ui.Ui;
import seedu.badmaths.matrix.Calculator;
import seedu.badmaths.Quadratic.Quadratic;
import java.util.ArrayList;


public class Command {
    private static final String filePath = "data/notes.txt";
    protected String command;
    protected String toDo;
    ArrayList<String> historyCommand = new ArrayList<>();


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
        return (index < 0 || index >= notes.getSize());
    }

    public boolean isAnInt(String todo) {
        try {
            Integer.parseInt(todo);
        } catch (NumberFormatException numberException) {
            Ui.printInvalidNumberEntered();
            return false;
        }
        return true;
    }



    public void executeCommand(NotesList notes, ArrayList<String> historyCommand) {
        TrigoGraph trigoGraph = new TrigoGraph(toDo);
        Calculator calculator = new Calculator();
        Quadratic quadratic = new Quadratic(toDo);
        CommandHistory commandHist = new CommandHistory(historyCommand);
        NotesMarker notesMarker = new NotesMarker(notes, filePath);
        PrioritySetter prioritySetter = new PrioritySetter(notes, filePath);
        NotesFinder notesFinder = new NotesFinder(notes, filePath);
        NotesPriorityFinder notesPriorityFinder = new NotesPriorityFinder(notes, filePath);
        NotesRanker notesRanker = new NotesRanker(notes, filePath);


        try {
            //@@author WilsonLee2000
            assert (command.equals("Bye") || command.equals("Graph") || command.equals("Store") ||
                    command.equals("List") || command.equals("Delete") || command.equals("Mark") ||
                    command.equals("Unmark") || command.equals("Low") || command.equals("Medium") ||
                    command.equals("High") || command.equals("FindInfo") || command.equals("FindPrior") ||
                    command.equals("FindMark") || command.equals("FindUnmark") || command.equals("Rank") ||
                    command.equals("Clear") || command.equals("Help") || command.equals("Matrix") ||
                    command.equals("Quadratic") || command.equals("History")) : "input has incorrect format required";
            //@@author Khooyourun
            switch (command) {
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
                Store store = new Store(notes, toDo);
                store.storeNotes();
                break;
            //@@author WilsonLee2000
            case "List":
                List lists = new List(notes,toDo);
                lists.listNotes();
                break;
            //@@author WilsonLee2000
            case "Delete":
                Delete deletes = new Delete(notes, toDo);
                deletes.deleteNotes();
                break;
            case "History":
                // print out all commands typed in the list
                commandHist.displayHistory();
                break;
            //@@author ZiqiuZeng
            case "Mark":
                notesMarker.mark(toDo);
                break;
            //@@author ZiqiuZeng
            case "Unmark":
                notesMarker.unmark(toDo);
                break;
            //@@author ZiqiuZeng
            case "Low":
            case "Medium":
            case "High":
                prioritySetter.setPriority(command,toDo);
                break;
            //@@author ZiqiuZeng
            case "FindInfo":
                notesFinder.find(toDo);
                break;
            //@@author ZiqiuZeng
            case "FindPrior":
                notesPriorityFinder.find(toDo);
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
                notesRanker.rank(toDo);
                break;
            /*
             * The command "Clear" will continue to execute for as long as it is being entered,
             * without any other command or input interrupting it.
             */
            case "Clear":
                notes.reset();
                NotesFileCleaner.clearFile(filePath);
                break;
            /*
             * The command "Help" will continue to execute for as long as it is being entered,
             * without any other command or input interrupting it.
             */
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

