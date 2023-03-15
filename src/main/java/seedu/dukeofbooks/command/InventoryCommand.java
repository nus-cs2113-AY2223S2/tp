package seedu.dukeofbooks.command;

public class InventoryCommand extends Command {
    public static final String COMMAND_WORD = "librarian";
    public static final String ADD_WORD = "add";
    public static final String DELETE_WORD = "delete";

    private String action;
    public InventoryCommand(String action) {
        this.action = action;
    }
    @Override
    public CommandResult execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
