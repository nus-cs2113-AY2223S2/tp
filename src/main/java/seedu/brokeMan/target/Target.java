package seedu.brokeMan.target;

public class Target {

    private double target;

    public Target(double target) {
        this.target = target;

    }
    public void viewMoneyLeft() {
        System.out.println("You have " + target + " left.");
    }
    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
        System.out.println("You have successfully set " + target + " as your target.");
    }
}
