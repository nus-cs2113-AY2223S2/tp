import java.io.IOException;
import java.util.Scanner;
/**
 * Movie Mate class is the main class that starts running the program.
 *
 */
public class MovieMate {
    private static Storage storage = new Storage();
    private static String filePath = "data/movies.csv";
    private static String newMoviesDB = "data/movies_trimmed.csv";
    private static String watchedListPath = "data/moviemate_data.txt";
    private static WatchedList watchedList = new WatchedList(storage.load(watchedListPath));
    private static ToWatchList toWatchList = new ToWatchList();
    private static MovieDatabase movieDatabase;

    static {
        try {
            movieDatabase = new MovieDatabase(ReadCSVFile.readEntireCSV(newMoviesDB));
        } catch (IOException e) {
            System.out.println("Critical error with database file.");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Ui.showWelcomeMessage();

        // Initialise Scanner
        Scanner scan = new Scanner(System.in);

        while(true) {
            // Get user input and parse it.
            String userInput = inputCommand(scan);
            String[] commandTypeAndParams = Parser.parseCommand(userInput);
            String commandType = commandTypeAndParams[0];
            String commandArg = commandTypeAndParams[1];
            Movie movie;
            String[] movieInfo;

            switch (commandType) {
            case "watched":
                // add to watched list
                watchedList.add(commandArg);
                break;
            case "towatch":
                // add to to-watch list
                toWatchList.add(commandArg);
                break;
            case "help":
                Ui.help();
                Ui.printLine();
                break;
            case "list":
                // list the watched list
                Integer watchId = 1;
                for (Movie watched: watchedList.movieList) {
                    System.out.print(watchId);
                    System.out.print(". ");
                    System.out.println(watched.getTitle());
                    watchId += 1;
                }
                Ui.printLine();
                break;
            case "watchlist":
                // list the to-watch list
                Integer toWatchId = 1;
                for (Movie towatch: toWatchList.movieList) {
                    System.out.print(toWatchId);
                    System.out.print(". ");
                    System.out.println(towatch.getTitle());
                    toWatchId += 1;
                }
                Ui.printLine();
                break;
            case "seedetail":
                // find relevant movie info
                break;
            case "exit":
                // fallthrough
            case "bye":
                exit();
                break;
            default:
                Ui.help();
                break;
            }
        }

    }

    private static void exit() {
        Ui.showExitMessage();
        Ui.printLine();
        storage.writeToFile(watchedListPath, watchedList.getFileWriteFormat());
        System.exit(0);
    }

    /**
     * Scan in the user input and trim extra white space.
     * If there is no input, continue to scan the next line for input.
     * @return The string that the user entered
     */
    private static String inputCommand(Scanner scan) {
        String s = "";
        s = scan.nextLine();
        while (s.trim().isEmpty() || s.trim().charAt(0) == '#') {
            s = scan.nextLine();
        }
        return s;
    }
}
