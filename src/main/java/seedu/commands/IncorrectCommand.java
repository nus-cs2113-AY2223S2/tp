package seedu.commands;


public class IncorrectCommand extends Command {

    public IncorrectCommand() {
    }

    @Override
    public void execute() {
        System.out.println("Incorrect command given!");
    }
}
