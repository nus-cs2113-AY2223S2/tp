package seedu.duke.data.ipptcalculator;

//@@author ghzr0
public class UserScore {
    /**
     * The following are the constructor classes for the functions involved in obtaining the scores for the 3 exercises
     **/

    private int situpScore;
    private int pushupScore;
    private int runScore;

    public int getRunScore () {
        return runScore;
    }

    public void setRunScore (int runScore) {
        this.runScore = runScore;
    }

    public int getSitupScore () {
        return situpScore;
    }

    public void setSitupScore (int situpScore) {
        this.situpScore = situpScore;
    }

    public int getPushupScore () {
        return pushupScore;
    }

    public void setPushupScore (int pushupScore) {
        this.pushupScore = pushupScore;
    }

    public int getTotalScore () {
        return this.pushupScore + this.runScore + this.situpScore;
    }

}
