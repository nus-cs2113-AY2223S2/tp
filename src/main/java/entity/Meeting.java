package entity;

public class Meeting {
    private String time;
    private String issue;

    public Meeting(String issue, String time) {
        this.time = time;
        this.issue = issue;
    }

    public String getTime() {
        return this.time;
    }

    public String getIssue() {
        return this.issue;
    }

    public String savableString() {
        return this.issue + "~|~" + this.time;
    }
}
