package seedu.badmaths;

//@@author ZiqiuZeng
public class Note {
    private String text; // the note information
    private int reviewCount; // how many times the note has been reviewed
    private boolean isDone; // whether the note is marked as done or not
    private NotePriority.Priority priority; // the priority of the note

    // constructor
    public Note(String text, NotePriority.Priority priority) {
        this.text = text;
        this.reviewCount = 0;
        this.isDone = false;
        this.priority = priority;
    }

    public Note() {
    }

    // getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void incrementReviewCount() {
        this.reviewCount++;
    }

    public boolean getIsDone() {
        return isDone;
    }
    public String getIsDoneIcon() {
        return (isDone ? "Y" : "N"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public NotePriority.Priority getPriority() {
        return priority;
    }

    public void setPriority(NotePriority.Priority priority) {
        this.priority = priority;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    @Override
    public String toString() {
        return "[" + getPriority() + "]" + "[" +getIsDoneIcon() + "]" + "[" + getReviewCount() + "]" + getText();
    }
}
