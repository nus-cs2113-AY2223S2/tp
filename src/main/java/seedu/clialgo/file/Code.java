package seedu.clialgo.file;

public class Code extends CS2040CFile {
    /**
     * Constructor for <code>Code</code> object.
     *
     * @param name Name of the code file.
     * @param path Path to the code file.
     * @param topic Topic that code file should be tagged.
     */
    public Code(String name, String path, String topic, int importance) {
        super(name, path, topic, importance);
    }

    public Code(String name, String path, String topic) {
        super(name, path, topic);
    }

    /** Prints the name of the code file. */
    @Override
    public void printFileName() {
        System.out.println(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the location path of the code file.
     *
     * @return The location path of the code file.
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the location path of the code file.
     *
     * @param path The new location path of the code file.
     */
    @Override
    public void setPath(String path) {
        this.path = path;
    }


    /**
     * Gets the tag of the code file.
     *
     * @return The tag of the code file.
     */
    public String getTopic() {
        return this.topic;
    }

    /**
     * Sets the tag of the code file.
     *
     * @param topic The new tag for the code file.
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getImportance() {
        return this.importance;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Code checkedCode = (Code) object;
        boolean isSameName = this.getName().equals(checkedCode.getName());
        boolean isSamePath = this.getPath().equals(checkedCode.getPath());
        boolean isSameTopic = this.getTopic().equals(checkedCode.getTopic());
        boolean isSameImportance = this.getImportance() == checkedCode.getImportance();
        return isSameName && isSamePath && isSameTopic && isSameImportance;
    }
}
