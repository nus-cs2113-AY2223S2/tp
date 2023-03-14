package seedu.clialgo;

/**
 * The <code>Note</code> object represents each note file that the user can add into CLIAlgo.
 */
public class Note {
    private String name;
    private String path;
    private String topic;

    /**
     * Constructor for <code>Note</code> object.
     *
     * @param name Name of the note file.
     * @param path Path to the note file.
     * @param topic Topic that note file should be tagged.
     */
    public Note(String name, String path, String topic) {
        this.name = name;
        this.path = path;
        this.topic = topic;
    }

    /**
     * Prints the name of the note file.
     */
    public void printNoteName() {
        System.out.println(name);
    }

    public String getName() {
        return this.name;
    }

    /**
     * Gets the location path of the note file.
     *
     * @return The location path of the note file.
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the location path of the note file.
     *
     * @param path The new location path of the note file.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the tag of the note file.
     *
     * @return The tag of the note file.
     */
    public String getTopic() {
        return this.topic;
    }

    /**
     * Sets the tag of the note file.
     *
     * @param topic The new tag for the note file.
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }
}
