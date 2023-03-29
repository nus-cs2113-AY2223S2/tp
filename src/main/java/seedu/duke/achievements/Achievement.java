package seedu.duke.achievements;

import seedu.duke.achievements.types.AchievementBodyPart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Achievement {
    private static final String BLANK = " ";
    private static final String COLON = ":";
    private static final String nextLine = "\n";
    private static final String COMPLETED = "ACHIEVED! Congratulations :)";
    private static final String UNCOMPLETED = "Not Achieved :(";
    private static final String COUNT_PREFIX = "Current Count: ";
    private String name;
    private String requirement;
    private boolean completed;
    private final AchievementDifficulty difficulty;
    private String AchievementType;
    public int countCurrent;
    public int countToComplete;


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
                       String AchievementType, int countCurrent, int countComplete) {
        this.name = name;
        this.requirement = requirement;
        this.completed = completed;
        this.difficulty = difficulty;
        this.AchievementType = AchievementType;
        this.countCurrent = countCurrent;
        this.countToComplete = countComplete;
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
                nextLine + difficulty.toString() +
                nextLine + completed +
                nextLine + COUNT_PREFIX +
                countCurrent + nextLine
        );
    }

    public String getName() {
        return this.name;
    }
    public String getRequirement() {
        return this.requirement;
    }

    public boolean isCompleted() {
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
        return  name + COLON +
                requirement + COLON +
                completedNum + COLON +
                difficulty.parseDifficultyForSaving() + COLON +
                AchievementType.toString() + COLON +
                countCurrent + COLON +
                countToComplete + COLON +
                System.lineSeparator();
    }



}
