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
    public String getDifficultyString() {
        return (difficulty);
    }
    public String parseDifficultyForSaving() {
        if (this.difficulty.equals(EASY.getDifficultyString())) {
            return "E";
        } else if (this.difficulty.equals(MEDIUM.getDifficultyString())) {
            return "M";
        } else {
            assert this.difficulty.equals(HARD.getDifficultyString());
            return "H";
        }
    }

}
