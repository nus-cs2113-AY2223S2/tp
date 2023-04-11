package seedu.badmaths.note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//@@author ZiqiuZeng
public class NotesList {

    //@@author ZiqiuZeng
    protected ArrayList<Note> notes;

    //@@author ZiqiuZeng
    public NotesList(ArrayList<Note> notes) {
        this.notes = notes;
    }

    //@@author ZiqiuZeng
    public void reset() {
        notes.clear();
        notes = new ArrayList<>();
    }

    //@@author ZiqiuZeng
    public void add(String text) {
        NotePriority.Priority priority = NotePriority.Priority.LOW;
        Note note = new Note(text, priority);
        notes.add(note);
    }

    //@@author ZiqiuZeng
    public void remove(int index) {
        notes.remove(index);
    }

    //@@author ZiqiuZeng
    public void markAsDone(int index) {
        Note note = notes.get(index);
        note.markAsDone();
    }

    //@@author ZiqiuZeng
    public void markAsUndone(int index) {
        Note note = notes.get(index);
        note.markAsNotDone();
    }

    //@@author ZiqiuZeng
    public void setPriority(int index, NotePriority.Priority priority) {
        Note note = notes.get(index);
        note.setPriority(priority);
    }

    //@@author ZiqiuZeng
    public String getText(int index) {
        return notes.get(index).toString();
    }

    //@@author ZiqiuZeng
    public void review(int index) {
        Note note = notes.get(index);
        note.incrementReviewCount();
    }

    //@@author ZiqiuZeng
    public ArrayList<Note> getAll() {
        return notes;
    }

    //@@author ZiqiuZeng
    public int getSize() {
        return notes.size();
    }

    //@@author ZiqiuZeng
    public ArrayList<Note> relevantInfo(String keyword) {
        ArrayList<Note> relevantNotes = new ArrayList<>();
        for (Note note : notes) {
            if (note.getText().contains(keyword)) {
                relevantNotes.add(note);
            }
        }
        return relevantNotes;
    }

    //@@author ZiqiuZeng
    public ArrayList<Note> relevantPriority(String priorityStr) {
        NotePriority.Priority priority = NotePriority.Priority.valueOf(priorityStr);
        ArrayList<Note> relevantNotes = new ArrayList<>();
        for (Note note : notes) {
            if (note.getPriority() == priority) {
                relevantNotes.add(note);
            }
        }
        return relevantNotes;
    }

    //@@author ZiqiuZeng
    public ArrayList<Note> relevantMarked() {
        ArrayList<Note> markedNotes = new ArrayList<>();
        for (Note note : notes) {
            if (note.getIsDone()) {
                markedNotes.add(note);
            }
        }
        return markedNotes;
    }

    //@@author ZiqiuZeng
    public ArrayList<Note> relevantUnmarked() {
        ArrayList<Note> unmarkedNotes = new ArrayList<>();
        for (Note note : notes) {
            if (!note.getIsDone()) {
                unmarkedNotes.add(note);
            }
        }
        return unmarkedNotes;
    }

    //@@author ZiqiuZeng
    public void rankByReviewCount() {
        notes.sort((note1, note2) -> Integer.compare(note2.getReviewCount(), note1.getReviewCount()));
    }

    //@@author ZiqiuZeng
    public void rankByPriority() {
        Collections.sort(notes, new Comparator<Note>() {
            @Override
            public int compare(Note note1, Note note2) {
                NotePriority.Priority priority1 = note1.getPriority();
                NotePriority.Priority priority2 = note2.getPriority();
                return priority2.compareTo(priority1);
            }
        });
    }

}
