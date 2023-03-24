package seedu.duke.achievements;

public class Achievement {
    private static final String BLANK = " ";
    private static final String COLON = ":";
    private static final String nextLine = "\n";
    private static final String COMPLETED = "ACHIEVED! Congratulations :)";
    private static final String UNCOMPLETED = "Not Achieved :(";
    private String name;
    private String requirement;
    private boolean completed;
    private AchievementDifficulty difficulty;


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
                       boolean completed, AchievementDifficulty difficulty) {
        this.name = name;
        this.requirement = requirement;
        this.completed = completed;
        this.difficulty = difficulty;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String getDifficulty() {
        return this.difficulty.toString();
    }

    @Override
    public String toString() {
        String completed;
        if (this.isCompleted()) {
            completed = COMPLETED;
        } else {
            completed = UNCOMPLETED;
        }

        return (name + COLON + BLANK + requirement +
                nextLine + completed +
                nextLine + requirement
            );
    }

    public void complete() {
        this.completed = true;
    }

}
