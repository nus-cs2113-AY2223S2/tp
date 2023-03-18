package seedu.duke.command;

public class ExceptionHandleCommand extends Command {
    private Exception e;
    public ExceptionHandleCommand(Exception e) {
        this.e = e;
    }

    public void execute() {
        System.out.println(e.getMessage());
    }
}
