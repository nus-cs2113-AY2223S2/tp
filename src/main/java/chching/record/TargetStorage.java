package chching.record;

/**
 * Models a class to store target
 */

public class TargetStorage {

    protected Target[] targetsList = new Target[1];

    /**
     * Constructor to instantiate TargetStorage objects
     *
     * @param targetsList input targetsList
     */
    public TargetStorage(Target[] targetsList) {
        this.targetsList = targetsList;
    }

    /**
     * Default Constructor to instantiate TargetStorage objects
     */
    public TargetStorage() {
        targetsList = new Target[1];
    }

    public void addTarget(Target target) {
        targetsList[0] = target;
    }

    public Target getTarget() {
        return targetsList[0];
    }

    public void clearTargetList() {
        targetsList[0] = null;
    }
}

