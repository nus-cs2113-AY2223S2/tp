package seedu.duke.achievements;

public enum AchievementDifficulty {
    EASY("*"),
    MEDIUM ("**"),
    HARD ("***");

    public final String difficulty;
    AchievementDifficulty(String difficulty){
        this.difficulty = difficulty;
    }

    public String toString() {
        return difficulty;
    }

}
