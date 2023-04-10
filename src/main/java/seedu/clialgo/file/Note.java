package seedu.clialgo.file;

/**
 * The <code>Note</code> object represents each note file that the user can add into CLIAlgo.
 */
public class Note extends CS2040CFile {
    /**
     * Constructor for <code>Note</code> object.
     *
     * @param name Name of the note file.
     * @param path Path to the note file.
     * @param topic Topic that note file should be tagged.
     */
    public Note(String name, String path, String topic) {
        super(name, path, topic);
    }

    public Note(String name, String path, String topic, int importance) {
        super(name, path, topic, importance);
    }

    /** Prints the name of the note file. */
    @Override
    public void printFileName() {
        System.out.println(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the location path of the note file.
     *
     * @return The location path of the note file.
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * Sets the location path of the note file.
     *
     * @param path The new location path of the note file.
     */
    @Override
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the tag of the note file.
     *
     * @return The tag of the note file.
     */
    @Override
    public String getTopic() {
        return this.topic;
    }

    /**
     * Sets the tag of the note file.
     *
     * @param topic The new tag for the note file.
     */
    @Override
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public int getImportance() {
        return this.importance;
    }

    @Override
    public void setImportance(int importance) {
        this.importance = importance;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Note checkedNote = (Note) object;
        boolean isSameName = this.getName().equals(checkedNote.getName());
        boolean isSamePath = this.getPath().equals(checkedNote.getPath());
        boolean isSameTopic = this.getTopic().equals(checkedNote.getTopic());
        boolean isSameImportance = this.getImportance() == checkedNote.getImportance();
        return isSameName && isSamePath && isSameTopic && isSameImportance;
    }
}
