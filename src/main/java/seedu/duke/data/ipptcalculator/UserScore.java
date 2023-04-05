package seedu.duke.data.ipptcalculator;

public class UserScore {

    private int situpScore;
    private int pushupScore;
    //private int totalScore;
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
