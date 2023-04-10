package seedu.moviemate.movie;

import seedu.moviemate.parser.Parser;
import seedu.moviemate.storage.MovieDatabase;
import seedu.moviemate.ui.Ui;

import java.util.ArrayList;

/**
 * A list of movies the user plans to watch.
 */
public class ToWatchList extends MovieList{

    public ToWatchList() {
        super();
    }

    public ToWatchList(ArrayList<String[]> load) {
        super(load);
    }

    @Override
    public void addToList(String inputTitle, MovieList watchedList, MovieList toWatchList, Ui ui) {
        ArrayList<Movie> relevantMovies = MovieDatabase.find(inputTitle);
        if (relevantMovies.size() == 0) {
            System.out.println("No relevant movie found, please try entering the towatch " +
                    "command and movie name again!");
            return;
        }
        if (inputTitle.isBlank()) {
            System.out.println("<Movie Name> cannot be left blank, please try entering " +
                    "the towatch command and movie name again!");
            return;
        }
        int id = 1;
        for (Movie relevantMovie : relevantMovies) {
            System.out.println(id + ". " + relevantMovie.toString());
            id += 1;
        }
        ui.printPromptIndexForAdd();

        String input = ui.inputCommand();
        Movie movie;
        while (true) {
            int addIndex = Parser.parseIndex(input, 1, relevantMovies.size());
            if (addIndex < 0) {
                ui.printRequireValidIndex(1, relevantMovies.size());
                input = ui.inputCommand();
                continue;
            }
            if (addIndex == 0) {
                ui.printExitInputIndex();
                return;
            }
            // happy path
            movie = relevantMovies.get(addIndex - 1);
            break;
        }

        // if movie being added to to-watch list is in watched list, user is prompted if they want
        // to remove it from watched list
        if (watchedList.getIndex(movie) != -1) {
            System.out.println("You have already watched this movie!\n" +
                    "Should we delete it from your watched list? [Y/N]");
            while (true) {
                input = ui.inputCommand();
                if (input.equalsIgnoreCase("N")) {
                    return;
                }
                if (input.equalsIgnoreCase("Y")) {
                    // any existing review for the movie is first deleted before the movie itself
                    // is deleted from the list
                    int movieIndex = watchedList.getIndex(movie);
                    Movie deletedMovie = new Movie(movie);
                    watchedList.movieList.set(movieIndex - 1, deletedMovie);
                    watchedList.remove(deletedMovie);
                    break;
                } else {
                    System.out.println("Invalid format. Please enter either 'Y' or 'N'.");
                }
            }
        }
        if (toWatchList.getIndex(movie) == -1) {
            toWatchList.add(movie);
            Ui.showAddMovieMessage(movie.toString());
        } else {
            System.out.println("That movie is already in your to-watch list.");
        }

    }

}
