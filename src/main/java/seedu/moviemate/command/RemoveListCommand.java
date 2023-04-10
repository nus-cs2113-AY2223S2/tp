package seedu.moviemate.command;

import seedu.moviemate.movie.ToWatchList;
import seedu.moviemate.movie.WatchedList;
import seedu.moviemate.parser.Parser;
import seedu.moviemate.storage.Storage;
import seedu.moviemate.ui.Ui;
import seedu.moviemate.movie.MovieList;

public class RemoveListCommand implements Command {

    private String removeListType;

    public RemoveListCommand(String removeListType) {
        this.removeListType = removeListType;
    }

    /**
     * Removes a movie from the given movie list based on user input.
     * The method prompts the user for the movie index to be removed
     * and ensures that the input is a valid index within the range.
     * If the input is invalid, the user is prompted again until a valid input is received.
     * If the user inputs 0, the method exits.
     *
     * @param movieList the MovieList from which a movie will be removed
     * @param ui the Ui object responsible for printing output and receiving user input
     */
    public void removeMovieList(MovieList movieList, Ui ui){
        if (movieList.empty()) {
            ui.printRemoveMovieListEmpty();
            return;
        }
        Ui.showListMessage(movieList);
        System.out.println("Please enter the id of the movie you would like to remove");
        while (true) {
            String input = ui.inputCommand();
            int removeIndex = Parser.parseIndex(input, 1, movieList.movieList.size());
            if (removeIndex < 0) {
                ui.printRequireValidIndex(1, movieList.movieList.size());
                continue;
            }
            if (removeIndex == 0) {
                ui.printExitInputIndex();
                break;
            }
            // happy path
            movieList.remove(removeIndex);
            break;

        }
    }


    @Override
    public void execute(WatchedList watchedList, ToWatchList toWatchList, Ui ui, Storage storage) {
        switch (removeListType) {
        case "watched":
            removeMovieList(watchedList, ui);
            break;
        case "towatch":
            removeMovieList(toWatchList, ui);
            break;
        default:
            System.out.println("Please follow the format: remove [watched/towatch]");
            break;
        }
    }


}
