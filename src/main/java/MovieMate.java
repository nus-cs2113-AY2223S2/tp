import java.util.Scanner;
/**
 * Movie Mate class is the main class that starts running the program.
 *
 */
public class MovieMate {
    private WatchedList watchedList = new WatchedList();
    private ToWatchList toWatchList = new ToWatchList();
    public static void main(String[] args) {
        System.out.println("Hello from Movie Mate!");
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine() + ", welcome to movie mate");
        System.out.println("Please enter the command to proceed with MovieMate :))");
        String userInput = inputCommand();
        String[] commandTypeAndParams = Parser.parseCommand(userInput);
        String commandType = commandTypeAndParams[0];
        String commandArg = commandTypeAndParams[1];
        while(!commandType.equals("exit")) {
            switch (commandType) {
            case "watched":
                // add to watched list
                break;
            case "towatch":
                // add to to-watch list
                break;
            default:
                Ui.help();
            }
        }
    }
    /**
     * Scan in the user input and trim extra white space.
     * If there is no input, continue to scan the next line for input.
     * @return The string that the user entered
     */
    private static String inputCommand() {
        Scanner scan = new Scanner(System.in);
        String s;
        s = scan.nextLine();
        while (s.trim().isEmpty() || s.trim().charAt(0) == '#') {
            s = scan.nextLine();
        }
        return s;
    }
}
