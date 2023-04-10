package seedu.moviemate.command;

import seedu.moviemate.storage.Storage;
import seedu.moviemate.movie.ToWatchList;
import seedu.moviemate.ui.Ui;
import seedu.moviemate.movie.WatchedList;

public class AddToWatchListCommand implements Command {
    private String inputTitle;

    public AddToWatchListCommand(String inputTitle) {
        this.inputTitle = inputTitle;
    }

    @Override
    public void execute(WatchedList watchedList, ToWatchList toWatchList, Ui ui, Storage storage) {
        toWatchList.addToList(inputTitle, watchedList, toWatchList, ui);
    }
}
