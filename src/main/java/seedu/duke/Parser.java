package seedu.duke.command;
import seedu.duke.DukeException;
import seedu.duke.Workout;
import java.util.ArrayList;
import java.util.Scanner;


public class Parser {
    private static final String UNRECOGNISED_INPUT = "     OOPS!!! I'm sorry, but I don't know what that means :-(";
    private ArrayList<Workout> workouts;
    /**
     * Read the input command of the usersE
     * @return return the string of workout
     */
    public static String readCommand(){
        String workOut;
        Scanner in = new Scanner(System.in);
        workOut = in.nextLine();
        return workOut;
    }
    public static void sayBye(){
        System.out.println("See you next time!");
    }
    /**
     * check the input and see if the Bye is entered
     * @return return false if bye is not entered
     */
    public static boolean isByeEntered(){
        String checkInput = readCommand();
        try {
            if(checkInput.isEmpty()) {
                throw new RuntimeException("You didn't enter anything, please type in the command to your workouts");
            }
            return checkInput.equalsIgnoreCase("bye");
        }catch (Exception e){
            System.out.println(e.getMessage());
            readCommand();
        }
        return false;
    }
    public static void inputInstruction(String input, ArrayList<Workout> workoutInput) throws DukeException {
        String[] command = input.split("\\s+");
        switch (command[0]) {
            case "list":
                ListCommand.printList(input, workoutInput);
                break;
            default:
                throw new DukeException(UNRECOGNISED_INPUT);
        }
    }
}