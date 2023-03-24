package seedu.duke.achievements;

import seedu.duke.ui.ErrorMessages;
import seedu.duke.exceptions.DukeError;
public abstract class Achievement {
    private final static String BLANK = " ";
    private final static String COLON = ":";
    private final static String nextLine = "\n";
    private final static String COMPLETED = "ACHIEVED! Congratulations :)" ;
    private final static String UNCOMPLETED = "Not Achieved :(";
    private String name;
    private String requirement;
    private boolean completed;
    private AchievementDifficulty difficulty;


    //@@ChubbsBunns

    /**
     * This is a constructor class for achievements
     * @param name Name of the achievement
     * @param requirement This is a description of what is
     *                    required to accomplish this achievement
     * @param completed Whether this achievement is achieved or not
     * @param input_difficulty A string passed that decides whether
     */
    public Achievement (String name, String requirement,
                        boolean completed, String input_difficulty) {
        this.name = name;
        this.requirement = requirement;
        this.completed = completed;
        if ( input_difficulty.toLowerCase().equals("easy") ) {
            this.difficulty = AchievementDifficulty.EASY;
        }
        else if (input_difficulty.toLowerCase().equals("medium")) {
            this.difficulty = AchievementDifficulty.MEDIUM;
        }
        else {
            assert input_difficulty.toLowerCase().equals("hard") : "hard";
            this.difficulty = AchievementDifficulty.HARD;
        }
    }

    public String getName(){
        return this.name;
    }

    public boolean isCompleted(){
        return this.completed;
    }

    public String getDifficulty() { return this.difficulty.toString();}

    @Override
    public String toString() {
        String completed;
        if (this.isCompleted()) {
            completed = COMPLETED;
        }
        else {
            completed = UNCOMPLETED;
        }

        return (name + COLON + BLANK + requirement +
                nextLine + completed +
                nextLine + requirement
         );
    }

}
