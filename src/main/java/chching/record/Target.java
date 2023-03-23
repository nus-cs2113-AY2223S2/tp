package chching.record;

import java.util.ArrayList;

/**
 * Models a class for a target
 */
public class Target {

    protected double value;

    /**
     * Constructor to instantiate target objects
     *
     * @param userTarget input target
     */
    public Target(double userTarget) {
        this.value = userTarget;
    }

    public Double getValue() {

        return value;
    }
}


