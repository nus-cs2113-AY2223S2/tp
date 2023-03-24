package seedu.duke.achievements;

import seedu.duke.ui.ErrorMessages;

public enum AchievementDifficulty {
    EASY("*"),
    MEDIUM ("**"),
    HARD ("***");

    public final String difficulty;
    AchievementDifficulty(String difficulty){
        this.difficulty = difficulty;
    }

    public String toString() { return difficulty; }
}
