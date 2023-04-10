package seedu.moviemate.parser;

import seedu.moviemate.command.AddToWatchListCommand;
import seedu.moviemate.command.AddWatchedListCommand;
import seedu.moviemate.command.RandomMovieCommand;
import seedu.moviemate.command.DeleteReviewCommand;
import seedu.moviemate.command.Command;
import seedu.moviemate.command.HelpCommand;
import seedu.moviemate.command.SeeDetailCommand;
import seedu.moviemate.command.RemoveListCommand;
import seedu.moviemate.command.ViewReviewCommand;
import seedu.moviemate.command.WatchListCommand;
import seedu.moviemate.command.ExitCommand;
import seedu.moviemate.command.ListCommand;
import seedu.moviemate.command.FilterCommand;
import seedu.moviemate.command.AddReviewCommand;
import seedu.moviemate.ui.Ui;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses a user input command and returns the corresponding Command object.
     * The user input command is expected to be a string containing a command type and its arguments.
     * The method splits the input command into its components and then switches on the command type to return
     * the appropriate Command object. If the command type is not recognized, it returns a HelpCommand object.
     *
     * @param userInputCommand the string containing the user input command
     * @param ui the Ui object to use for displaying messages to the user
     * @return the Command object corresponding to the user input command
     */
    public static Command parseCommand(String userInputCommand, Ui ui) {
        final String[] split = userInputCommand.trim().split("\\s+", 2);
        final String[] commandTypeAndParams = split.length == 2 ? split : new String[]{split[0], ""};

        String commandType = commandTypeAndParams[0];
        String commandArg = commandTypeAndParams[1];
        assert userInputCommand != null : "User input command cannot be null";
        switch (commandType) {
        case "watched":
            return new AddWatchedListCommand(commandArg);
        case "remove":
            return new RemoveListCommand(commandArg);
        case "towatch":
            return new AddToWatchListCommand(commandArg);
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "watchlist":
            return new WatchListCommand();
        case "seedetail":
            return new SeeDetailCommand(commandArg);
        case "addreview":
            return new AddReviewCommand();
        case "deletereview":
            return new DeleteReviewCommand();
        case "viewreview":
            return new ViewReviewCommand();
        case "filter":
            return new FilterCommand(commandArg);
        case "random":
            return new RandomMovieCommand(commandArg);
        case "exit": // fallthrough
        case "bye":
            return new ExitCommand();
        default:
            return new HelpCommand(); // or return UnknownCommand()
        }
    }

    /**
     * Parses a string representing an index and returns its integer value if it falls within the given range [l, r].
     * If the string cannot be parsed into an integer or is out of the range [l, r], the method returns -1.
     * Otherwise, the method returns the parsed integer index.
     *
     * @param indexString the string representing the index to be parsed
     * @param l the lower bound of the index range (inclusive)
     * @param r the upper bound of the index range (inclusive)
     * @return the integer value of the parsed index.
     */
    public static int parseIndex(String indexString, int l, int r) {
        int index = 0;
        assert l <= r : "Lower bound must be less than or equal to upper bound";

        //cannot parse string to index
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            System.out.println("Movie id should be number");
            return -1;
        }

        //index out of range
        if (index < l - 1 || index > r) {
            System.out.println("Movie id out of range");
            return -1;
        }
        return index;
    }

}
