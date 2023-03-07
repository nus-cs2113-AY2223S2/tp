package seedu.duke;


public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        DataReader dr = new DataReader();
        System.out.println(dr.getUniversities());
        System.out.println(dr.getModules());
    }
}
