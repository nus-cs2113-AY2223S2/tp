package seedu.badMaths.ui;

import seedu.badMaths.Note;
import seedu.badMaths.NotePriority;

import java.util.ArrayList;

public class Ui {
    public static void printIncorrectFormatEntered() {
        System.out.println("Please enter the format as required.");
    }

    public static void printNegativeAmplitudeEntered() {
        System.out.println("Amplitude cannot be negative!");
    }

    public static void printAmplitude(double amplitude) {
        System.out.println("This is the amplitude: " + amplitude);
    }

    public static void printPhase(double phase) {
        System.out.println("This is the phase: " + phase);
    }

    public static void printVerticalShift(double verticalShift) {
        System.out.println("This is the vertical shift: " + verticalShift);
    }

    public static void printFrequency(Double freq) {
        System.out.println("This is the freq (Hz): " + freq);
    }


    //@@author ZiqiuZeng
    public static void printMark(String text) {
        System.out.println("You have marked this note as done:");
        System.out.println(text);
    }

    //@@author ZiqiuZeng
    public static void printUnmark(String text) {
        System.out.println("You have unmarked this note:");
        System.out.println(text);
    }

    //@@author WilsonLee2000
    public static void printAddNote(String text, int size) {
        System.out.println("You have added this note:");
        System.out.println(text);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    //@@author WilsonLee2000
    public static void printDelete(String text, int size) {
        System.out.println("You have removed this note:");
        System.out.println(text);
        System.out.println("Now you have " + (size - 1) + " tasks in the list.");
    }

    //@@author WilsonLee2000
    public static void printNotes(ArrayList<Note> notes) {
        if (notes.size() == 0) {
            System.out.println("You have no note yet. :(");
        } else {
            System.out.println("Here are the notes you have stored:");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i).toString());
            }
            System.out.println("Now you have " + notes.size() + " tasks in the list.");
        }
    }

    //@@author WilsonLee2000
    public static void printSpecificNote(int index, ArrayList<Note> notes) {
        if (index >= 0 && index < notes.size()) {
            Note note = notes.get(index);
            System.out.println("Here is the note you are looking for: ");
            System.out.println((index + 1) + ": " + note.toString());
        } else {
            System.out.println("Invalid note index");
        }
    }

    //@@author ZiqiuZeng
    public static void printFindNotes(ArrayList<Note> notes) {
        if (notes.size() == 0) {
            System.out.println("Sorry, no relevant results were found for this query. Please try other keywords.");
        } else {
            System.out.println("Here are the notes you are searching for:");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i).toString());
            }
        }
    }

    //@@author ZiqiuZeng
    public static void printPriority(int index, ArrayList<Note> notes) {
        NotePriority.Priority priority = notes.get(index).getPriority();
        String priorityStr = priority.name();
        System.out.println("You have changed its priority to" + priorityStr);
        System.out.println((index + 1) + ": " + notes.get(index).toString());
    }

    //@@author ZiqiuZeng
    public static void printNotesByReviewCount(ArrayList<Note> notes) {
        // Sort notes by review count in descending order
        notes.sort((note1, note2) -> Integer.compare(note2.getReviewCount(), note1.getReviewCount()));

        // Print out notes by review count
        System.out.println("Notes sorted by review count:");
        for (Note note : notes) {
            System.out.println(note.getText() + " (review count: " + note.getReviewCount() + ")");
        }
    }

    //@@author ZiqiuZeng
    public static void printNotesByPriority(ArrayList<Note> notes) {
        ArrayList<Note> highPriorityNotes = new ArrayList<>();
        ArrayList<Note> mediumPriorityNotes = new ArrayList<>();
        ArrayList<Note> lowPriorityNotes = new ArrayList<>();

        for (Note note : notes) {
            if (note.getPriority() == NotePriority.Priority.HIGH) {
                highPriorityNotes.add(note);
            } else if (note.getPriority() == NotePriority.Priority.MEDIUM) {
                mediumPriorityNotes.add(note);
            } else {
                lowPriorityNotes.add(note);
            }
        }

        System.out.println("High priority notes:");
        for (Note note : highPriorityNotes) {
            System.out.println(note.toString());
        }

        System.out.println("Medium priority notes:");
        for (Note note : mediumPriorityNotes) {
            System.out.println(note.toString());
        }

        System.out.println("Low priority notes:");
        for (Note note : lowPriorityNotes) {
            System.out.println(note.toString());
        }
    }
}
