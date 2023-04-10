package seedu.moviemate.command;

import seedu.moviemate.movie.MovieList;
import seedu.moviemate.movie.ToWatchList;
import seedu.moviemate.movie.WatchedList;
import seedu.moviemate.parser.Parser;
import seedu.moviemate.storage.Storage;
import seedu.moviemate.ui.Ui;

public class SeeDetailCommand implements Command {

    private String movieListType;

    //@@author chao2048
    public SeeDetailCommand(String movieListType) {
        this.movieListType = movieListType;
    }

    /**
     * Used for see detail by list.
     * The function will display the list and prompt the user to enter the index of a movie.
     * It will then show the details of that movie to the user
     *
     * @param movieList relevant movie list
     * @param ui the Ui
     */
    public void seeMovieDetail(MovieList movieList, Ui ui) {
        // list watched/to-watch list for the user to choose
        if (movieList.empty()) {
            ui.printSeedetailEmpty();
            return;
        }
        ui.showListMessage(movieList);
        ui.printSeedetailHelp();

        while (true) {
            String inputIndex = ui.inputCommand();
            int seeDetailWatchedIndex = Parser.parseIndex(inputIndex, 1, movieList.movieList.size());
            if (seeDetailWatchedIndex < 0) {
                ui.printRequireValidIndex(1, movieList.movieList.size());
                continue;
            }
            if (seeDetailWatchedIndex == 0) {
                ui.printExitInputIndex();
                break;
            }
            // happy path
            ui.printMovieDetail(movieList.getMovieDetail(seeDetailWatchedIndex));
            ui.printSeedetailSuccess();
            break;
        }
    }

    public void seeMovieDetailByName(Ui ui) {
        ui.printSeedetailByNameHelp();
        String movieName = ui.inputCommand();
        MovieList.findMovieDetail(movieName, ui);
    }
    //@@author chao2048

    public void execute(WatchedList watchedList, ToWatchList toWatchList, Ui ui, Storage storage) {
        switch (movieListType) {
        case "watched":
            assert movieListType.equals("watched");
            seeMovieDetail(watchedList, ui);
            break;
        case "towatch":
            assert movieListType.equals("towatch");
            seeMovieDetail(toWatchList, ui);
            break;
        case "movie":
            seeMovieDetailByName(ui);
            break;
        default:
            ui.printSeedetailFormatHelp();
            break;
        }
    }
}
