package seedu.duke;

public class University {
    private int univId;
    private String univName;
    private String univAbbName;

    public University(int univId, String univName, String univAbbName) {
        this.univId = univId;
        this.univName = univName;
        this.univAbbName = univAbbName;
    }

    @Override
    public String toString() {
        return univId + "," + univName + "," + univAbbName;
    }
}
