package seedu.duke;

public class View {
    protected String description;
    protected boolean isDone;

    public View(String description) {
        this.description = description;
        this.isDone = false;
    }
}
