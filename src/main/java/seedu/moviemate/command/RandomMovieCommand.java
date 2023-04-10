package seedu.moviemate.command;

import seedu.moviemate.movie.Movie;
import seedu.moviemate.movie.ToWatchList;
import seedu.moviemate.movie.WatchedList;
import seedu.moviemate.storage.MovieDatabase;
import seedu.moviemate.storage.Storage;
import seedu.moviemate.ui.Ui;

import java.util.ArrayList;

public class RandomMovieCommand implements Command {
    private String inputGenre;

    public RandomMovieCommand (String inputGenre) {
        this.inputGenre = inputGenre;
    }

    @Override
    public void execute(WatchedList watchedList, ToWatchList toWatchList, Ui ui, Storage storage) {
        ArrayList<Movie> relevantMovies = MovieDatabase.randomMovieOfGenres(inputGenre);
        ui.showMovies(relevantMovies);
    }
}
