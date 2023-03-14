package seedu.duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.DateFormat;
import seedu.workout.Workout;
import seedu.duke.command.Parser;

import java.util.ArrayList;
public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private void run() {
        boolean isRunning = true;
        while (isRunning) {
            isRunning = Parser.isByeEntered();
        }
        Parser.sayBye();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();


        public class Duke {
            private ArrayList<Workout> workouts;

            public Duke() {
                this.workouts = new ArrayList<Workout>();
            }


            public static void main(String[] args) {
                Duke duke = new Duke();
                duke.run();

            }