package seedu.moviemate;

import seedu.moviemate.command.Command;
import seedu.moviemate.command.ExitCommand;
import seedu.moviemate.movie.ToWatchList;
import seedu.moviemate.movie.WatchedList;
import seedu.moviemate.parser.Parser;
import seedu.moviemate.storage.MovieDatabase;
import seedu.moviemate.storage.Storage;
import seedu.moviemate.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Movie Mate class is the main class that starts running the program.
 */
public class MovieMate {
    private static Ui ui;
    private static Parser parser;
    private static Logger logger;
    private static Storage storage;
    private static WatchedList watchedList;
    private static ToWatchList toWatchList;
    private static MovieDatabase movieDatabase;

    private static String watchedListPath;
    private static String toWatchListPath;
    private String userName;



    public MovieMate(String watchedListPath, String toWatchListPath) {
        ui = new Ui();
        parser = new Parser();
        logger = Logger.getLogger("MovieMate");
        storage = new Storage();

        try {
            movieDatabase = new MovieDatabase(storage.loadDatabase());
        } catch (IOException e) {
            logger.log(Level.WARNING, "processing error");
            System.out.println("Critical error: You might be missing a database file.");
            System.exit(1);
        }

        watchedList = new WatchedList(storage.load(watchedListPath));
        toWatchList = new ToWatchList(storage.load(toWatchListPath));
    }


    private void start() {
        this.userName = storage.loadName();
        if (this.userName.equals("")) {
            this.userName = ui.showWelcomeMessage();
        } else {
            this.userName = ui.showWelcomeMessage(this.userName);
        }
    }

    private void exit() {
        ui.showExitMessage();
        storage.writeToFile("", this.userName);
        storage.writeToFile(watchedListPath, watchedList.getFileWriteFormat());
        storage.writeToFile(toWatchListPath, toWatchList.getFileWriteFormat());
        System.exit(0);
    }

    public void run() {
        this.start();
        ui.printLine();
        Command command;
        do {
            ui.printEnterCommandPrompt();
            String userInput = ui.inputCommand();
            command = parser.parseCommand(userInput, ui);
            command.execute(watchedList, toWatchList, ui, storage);
            ui.printLine();
        } while (!ExitCommand.isExit(command));
        exit();
    }

    public static void main(String[] args) {
        watchedListPath = "data/moviemate_watchlist.txt";
        toWatchListPath = "data/moviemate_towatch.txt";

        new MovieMate(watchedListPath, toWatchListPath).run();
    }
}
