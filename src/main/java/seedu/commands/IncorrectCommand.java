package seedu.commands;


public class IncorrectCommand extends Command {

    public IncorrectCommand() {
    }

    @Override
    public void execute() {
        System.out.println("Please enter the command again!");
    }
}
