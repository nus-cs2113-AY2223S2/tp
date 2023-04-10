package seedu.moviemate.movie;

import seedu.moviemate.parser.Parser;
import seedu.moviemate.storage.MovieDatabase;
import seedu.moviemate.ui.Ui;

import java.util.ArrayList;

/**
 * A list of movies the user has watched.
 */
public class WatchedList extends MovieList {

    public WatchedList() {
        super();
    }

    public WatchedList(ArrayList<String[]> loaded) {
        super(loaded);
    }

    /**
     * Sets a review for a movie currently in the watched list
     * If review already exists for that movie, old review is overwritten with new one
     *
     * @param ui the Ui
     * @param watchedList list of watched movies
     */
    public void setReview(Ui ui, WatchedList watchedList) {
        // no movie, cannot add any review
        if (watchedList.empty()) {
            System.out.println("There are no movies in your watched list. Please add some movies first.");
            return;
        }

        ui.showListMessage(watchedList);

        Movie currentMovie;
        // get valid movie index
        String indexString = ui.inputCommand();
        int index = Parser.parseIndex(indexString, 1, movieList.size());
        while (true) {
            if (index < 0) {
                ui.printRequireValidIndex(1, movieList.size());
                indexString = ui.inputCommand();
                index = Parser.parseIndex(indexString, 1, movieList.size());
            } else if (index == 0) {
                ui.printExitInputIndex();
                return;
            } else {
                currentMovie = this.getMovie(index);
                break;
            }
        }

        System.out.println("This is the current movie that you want to review:");
        System.out.println(currentMovie.getTitle());
        MovieEntry reviewedMovie = new MovieEntry(currentMovie);

        // Do review here.
        reviewedMovie.setReview();

        this.movieList.set(index - 1, reviewedMovie);
    }

    /**
     * Deletes a review for a movie currently in the watched list
     * If review does not exist for that movie, nothing changes
     *
     * @param ui the Ui
     * @param watchedList list of watched movies
     */
    public void deleteReview(Ui ui, WatchedList watchedList) {
        // no movie, cannot delete any review
        if (watchedList.empty()) {
            System.out.println("There are no movies in your watched list. PLease add some movies first.");
            return;
        }
        ui.showListMessage(watchedList);

        Movie currentMovie;
        // get valid movie index
        String indexString = ui.inputCommand();
        int index = Parser.parseIndex(indexString, 1, movieList.size());
        while (true) {
            if (index < 0) {
                ui.printRequireValidIndex(1, movieList.size());
                indexString = ui.inputCommand();
                index = Parser.parseIndex(indexString, 1, movieList.size());
            } else if (index == 0) {
                ui.printExitInputIndex();
                return;
            } else {
                currentMovie = this.getMovie(index);
                break;
            }
        }

        if (currentMovie instanceof MovieEntry) {
            Movie deletedMovie = new Movie(currentMovie);
            this.movieList.set(index - 1, deletedMovie);
            System.out.println(String.format("Successfully deleted the review of movie %d.", index));
        } else {
            System.out.println("The movie has no review. No need to delete!");
        }

    }

    /**
     * Prints out review the user has previously written for a movie in the watched list
     *
     * @param ui the Ui
     * @param watchedList list of watched movies
     */
    public void viewReview(Ui ui, WatchedList watchedList) {
        // no movie, cannot view review
        if (watchedList.empty()) {
            System.out.println("There are no movies in your watched list. Please add some movies first.");
            return;
        }
        ui.showListMessage(watchedList);

        Movie currentMovie;
        // get valid movie index
        String indexString = ui.inputCommand();
        int index = Parser.parseIndex(indexString, 1, movieList.size());
        while (true) {
            if (index < 0) {
                ui.printRequireValidIndex(1, movieList.size());
                indexString = ui.inputCommand();
                index = Parser.parseIndex(indexString, 1, movieList.size());
            } else if (index == 0) {
                ui.printExitInputIndex();
                return;
            } else {
                currentMovie = this.getMovie(index);
                break;
            }
        }

        if (currentMovie instanceof MovieEntry) {
            MovieEntry reviewedMovie = (MovieEntry) currentMovie;
            System.out.println(reviewedMovie.getReview());
        } else {
            System.out.println("The movie has no review. You can add a review using 'addreview'.");
        }
    }

    @Override
    public void addToList(String inputTitle, MovieList watchedList, MovieList toWatchList, Ui ui) {
        ArrayList<Movie> relevantMovies = MovieDatabase.find(inputTitle);
        if (relevantMovies.size() == 0) {
            System.out.println("No relevant movie found, please try entering the watched " +
                    "command and movie name again!");
            return;
        }
        if (inputTitle.isBlank()) {
            System.out.println("<Movie Name> cannot be left blank, please try entering " +
                    "the watched command and movie name again!");
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

        //if movie being added to watched list is in to-watch list, it is deleted in to-watch list
        if (toWatchList.getIndex(movie) != -1) {
            toWatchList.remove(movie);
        }
        if (watchedList.getIndex(movie) == -1) {
            watchedList.add(movie);
            Ui.showAddMovieMessage(movie.toString());
        } else {
            System.out.println("That movie is already in your watched list.");
        }
    }

}
