package seedu.duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.DateFormat;
import seedu.workout.Workout;

public class Duke {
    private ArrayList<Workout> workouts;

    public Duke() {
        this.workouts = new ArrayList<Workout>();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.print("Enter a command: ");
            command = scanner.nextLine();

            if (command.startsWith("delete ")) {
                String dateString = command.substring(7);
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                    Date date = dateFormat.parse(dateString);

                    // Search for the workout with the matching date and remove it
                    Workout workoutToDelete = null;
                    for (Workout workout : this.workouts) {
                        if (workout.getDate().equals(date)) {
                            workoutToDelete = workout;
                            break;
                        }
                    }
                    if (workoutToDelete != null) {
                        this.workouts.remove(workoutToDelete);
                        System.out.println("Workout deleted successfully.");
                    } else {
                        System.out.println("No workout found with the specified date.");
                    }

                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in the format dd/mm/yy.");
                }
            }

        } while (!command.equals("bye"));
        scanner.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
