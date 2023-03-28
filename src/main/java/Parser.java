/**
 * Parses user input.
 */
public class Parser {

    public static Command parseCommand(String userInputCommand, Ui ui) {
        final String[] split = userInputCommand.trim().split("\\s+", 2);
        final String[] commandTypeAndParams = split.length == 2 ? split : new String[]{split[0], ""};

        String commandType = commandTypeAndParams[0];
        String commandArg = commandTypeAndParams[1];

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
        case "exit": // fallthrough
        case "bye":
            return new ExitCommand();
        default:
            return new HelpCommand(); // or return UnknownCommand()
        }
    }


    public static int parseIndex(String indexString, int l, int r) {
        int index = 0;

        //cannot parse string to index
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            return -1;
        }

        //index out of range
        if (index < l || index > r) {
            return -1;
        }
        return index;
    }

}
