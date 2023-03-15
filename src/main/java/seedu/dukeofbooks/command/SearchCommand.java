package seedu.dukeofbooks.command;

public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    private String title;
    
    public SearchCommand(String title) {
        this.title = title;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("book has been found");
    }
}
