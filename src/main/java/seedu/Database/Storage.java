package seedu.Database;

public abstract class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.load();
    }

    public abstract void load(); 

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
