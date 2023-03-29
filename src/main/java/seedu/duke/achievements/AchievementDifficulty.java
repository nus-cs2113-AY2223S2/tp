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
        return ("Difficulty: " + difficulty);
    }

    public String parseDifficultyForSaving() {
        if (this.difficulty.equals(EASY)) {
            return "E";
        } else if (this.difficulty.equals(MEDIUM)) {
            return "M";
        } else {
            assert this.difficulty.equals(HARD);
            return "H";
        }
    }

}
