import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    /**
     * Show exit message to the user
     */
    public static void showExitMessage() {
        System.out.println("Thanks for using Movie Mate!");
        System.out.println("Hope to see you again soon :))");
    }
    public static void printLine() {
        System.out.println("-------------------------------------------------------------------------");
    }

    /**
     * Show welcome message to the user
     */
    public static void showWelcomeMessage() {
        System.out.println("Hello from Movie Mate!");
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine() + ", welcome to movie mate");
        System.out.println("Please enter the command to proceed with MovieMate :))");
    }

    /**
     * Show help message to the user
     * TODO change tbc into user guide
     */
    public static void help() {
        System.out.println("First time using Movie Mate?");
        System.out.println("Here is the link to the user guide: ");
        System.out.println("tbc");
        System.out.println("Hope it helps, and have a nice day!");
    }

    /**
     * Shows successfully added movie to the watched/ to-watch list
     * @param movie The String containing movie title, year, runtime, and genre
     */
    public static void showAddMovieMessage(String movie) {
        System.out.println("You have successfully added the movie into your list!");
        System.out.println("Here is the movie detail");
        System.out.println(movie);
        System.out.println("Feel free to continue with other features");
    }
}
