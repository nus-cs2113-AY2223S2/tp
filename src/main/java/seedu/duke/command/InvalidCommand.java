package seedu.duke.command;

public class InvalidCommand extends Command {
    @Override
    public void execute() {
        ui.printInvalidInputMessage();
        System.out.println(ui.getLine());
    }
}
