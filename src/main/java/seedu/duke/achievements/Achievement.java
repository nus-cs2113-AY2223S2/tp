package seedu.duke.achievements;


public abstract class Achievement {
    private static final String BLANK = " ";
    private static final String COLON = ":";
    private static final String NEXT_LINE = "\n";
    private static final String COMPLETED = "ACHIEVED! Congratulations :)";
    private static final String UNCOMPLETED = "Not Achieved :(";
    private static final String COUNT_PREFIX = "Current Count: ";
    public int countCurrent;
    public int countToComplete;
    private String name;


    private String requirement;
    private boolean completed;
    private final AchievementDifficulty difficulty;
    private String achievementType;


    //@@ChubbsBunns

    /**
     * This is a constructor class for achievements
     *
     * @param name        Name of the achievement
     * @param requirement This is a description of what is
     *                    required to accomplish this achievement
     * @param completed   Whether this achievement is achieved or not
     * @param difficulty  A string passed that decides whether
     */
    public Achievement(String name, String requirement,
                       boolean completed, AchievementDifficulty difficulty,
                       String achievementType, int countCurrent, int countComplete) {
        this.name = name;
        this.requirement = requirement;
        this.completed = completed;
        this.difficulty = difficulty;
        this.achievementType = achievementType;
        this.countCurrent = countCurrent;
        this.countToComplete = countComplete;
    }

    @Override
    public String toString() {
        String completed;
        if (this.getCompleted()) {
            completed = COMPLETED;
        } else {
            completed = UNCOMPLETED;
        }

        return (name + COLON + BLANK + requirement +
                NEXT_LINE + difficulty.toString() +
                NEXT_LINE + completed +
                NEXT_LINE + COUNT_PREFIX +
                countCurrent + NEXT_LINE
            );
    }

    public String getName() {
        return this.name;
    }

    public String getRequirement() {
        return this.requirement;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public String getDifficulty() {
        return this.difficulty.toString();
    }

    public void complete() {
        this.completed = true;
    }

    public String parseDataForSaving() {
        String completedNum;
        if (completed == true) {
            completedNum = "1";
        } else {
            completedNum = "0";
        }
        return name + COLON +
                requirement + COLON +
                completedNum + COLON +
                difficulty.parseDifficultyForSaving() + COLON +
                achievementType.toString() + COLON +
                countCurrent + COLON +
                countToComplete + COLON +
                System.lineSeparator();
    }


}
