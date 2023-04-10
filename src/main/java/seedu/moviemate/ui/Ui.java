package seedu.moviemate.ui;

import seedu.moviemate.movie.Movie;
import seedu.moviemate.movie.MovieList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {

    /**
     * Scan in the user input and trim extra white space.
     * If there is no input, continue to scan the next line for input.
     *
     * @return The string that the user entered
     */
    public String inputCommand() {
        Scanner scan = new Scanner(System.in);
        String s = "";
        s = scan.nextLine();

        while (s.trim().isEmpty() || s.trim().charAt(0) == '#') {
            if (scan.hasNextLine()) {
                s = scan.nextLine();
            }
        }
        return s;
    }

    /**
     * Show exit message to the user
     */
    public void showExitMessage() {
        System.out.println("Thanks for using Movie Mate!");
        System.out.println("Hope to see you again soon :))");
    }

    public static void printLine() {
        System.out.println("-------------------------------------------------------------------------");
    }

    /**
     * Show welcome message to the user
     */
    public String showWelcomeMessage() {
        System.out.println("Hello from Movie Mate!");
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();

        System.out.println("Hello " + name + ", welcome to movie mate");
        System.out.println("Please enter the command to proceed with MovieMate :))");
        return name;
    }

    public String showWelcomeMessage(String name) {
        System.out.println("Hello from Movie Mate!");
        System.out.printf("Welcome back, %s!\n", name);
        System.out.println("Please enter the command to proceed with MovieMate :))");
        return name;
    }

    /**
     * Show help message to the user
     * TODO change tbc into user guide
     */
    public static void printHelp() {
        System.out.println("First time using Movie Mate?");

        System.out.println("Here are some commands available in MovieMate:");
        System.out.println("Format: watched <movie name>");
        System.out.println("Usage: add a watched movie to your watched list.");
        printLine();
        System.out.println("Format: list");
        System.out.println("Usage: list out all movie in your watched list.");
        printLine();
        System.out.println("Format: random <genre>");
        System.out.println("Usage: get some random movies that fits in the genre entered");
        printLine();
        System.out.println("Format: seedetail movie");
        System.out.println("The system the will then prompt you to enter the movie name " +
                "and proceed with other instruction.");
        System.out.println("Usage: see detail of a movie");
        printLine();

        System.out.println("To know more about MovieMate's command. Please check the user guide.");
        System.out.println("Here is the link to the user guide: ");
        System.out.println("https://ay2223s2-cs2113-w12-4.github.io/tp/UserGuide.html");
        System.out.println("Hope it helps, and have a nice day!");
    }

    /**
     * Shows successfully added movie to the watched/ to-watch list
     *
     * @param movie The String containing movie title, year, runtime, and genre
     */
    public static void showAddMovieMessage(String movie) {
        System.out.println("You have successfully added the movie into your list!");
        System.out.println("Here are the movie details:");
        System.out.println(movie);
        System.out.println("Feel free to continue with other features!");
    }

    /**
     * Show the movie list, watched or to-watch, to the user.
     *
     * @param movieList The movie list to show
     */
    public static void showListMessage(MovieList movieList) {
        int watchId = 1;
        for (Movie watched : movieList.movieList) {
            System.out.print(watchId);
            System.out.print(". ");
            System.out.println(watched.getTitle());
            watchId += 1;
        }
        printLine();
        if (watchId == 1) {
            System.out.println("Currently, your list is empty.");
        } else {
            System.out.println("These are the movies in your list.");
        }
    }

    /**
     * Show successfully deleted movie from list to the user
     */
    public static void showDeleteMessage() {
        System.out.println("The movie has been deleted for you!");
    }

    /**
     * This method shows the movies in the ArrayList to the user.
     *
     * @param movies The ArrayList that contains the movies.
     */
    public static void showMovies(ArrayList<Movie> movies) {
        int index = 1; // 1-based index for displaying to users
        if (movies.size() != 0) {
            System.out.println("Below are the random movies that fall in the genre entered :))");
            for (Movie movie : movies) {
                System.out.println(index + ". " + movie.toString());
                index += 1;
            }
            System.out.println("Feel free to add it to your movie list!");
        } else {
            System.out.println("There is no relevant movies found, please try again!");
        }
    }

    public void printRemoveMovieListEmpty() {
        System.out.println("This list is empty. Nothing to remove!");
    }

    public void printSeedetailEmpty() {
        System.out.println("There are no movies in your list. Nothing to show!");
    }

    public void printSeedetailHelp() {
        System.out.println("Please enter the movie index that you would like to see the detail of!");
    }

    public void printSeedetailByNameHelp() {
        System.out.println("Please enter the movie name that you would like to see the detail of!");
    }

    public void printSeedetailFormatHelp() {
        System.out.println("Please follow the format: seedetail [watched/towatch/movie]");
    }
    
    /**
     * Show feedback message to the user after showing the movie detail
     */
    public static void printSeedetailSuccess() {
        System.out.println("The movie details are shown above!");
        System.out.println("Please feel free to continue with other features :))");
    }
    public static void printSeedetailFail() {
        System.out.println("Something went wrong");
        System.out.println("Please try again...");
    }

    public void printMovieDetail(String movieDetail) {
        System.out.println(movieDetail);
    }

    public void printRequireValidIndex(int l, int r) {
        System.out.println(String.format("Please enter a valid index from %d to %d", l, r));
        System.out.println("If you would like to exit the command, simply enter 0.");
    }
    public void printExitInputIndex() {
        System.out.println("Exit input acknowledged. Cancelling last command...");
    }
    public void printPromptIndexForAdd() {
        System.out.println("Please enter the id of the movie you're looking for\n" +
                "The program will then proceed with adding the movie you chose, thanks!");
        printLine();
    }
    public void printPromptIndexForDetail() {
        System.out.println("Please enter the id of the movie you're looking for\n" +
                "The program will then show the detail of the movie you chose, thanks!");
        printLine();
    }
    public void printEnterCommandPrompt() {
        System.out.println("Please enter the next command:");
    }
}
