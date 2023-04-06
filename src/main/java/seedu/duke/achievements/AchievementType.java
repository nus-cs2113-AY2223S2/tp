package seedu.duke.achievements;

//@@author ChubbsBunns

/**
 * Provides enum variables for storing the types of achievements
 */
public enum AchievementType {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard"),
    CORE("core"),
    LEG("leg"),
    UPPER("upper body"),
    GYM("gym"),
    STATIC("static");

    public final String type;

    AchievementType (String type) {
        this.type = type;
    }

    public String toString () {
        return type;
    }

}
