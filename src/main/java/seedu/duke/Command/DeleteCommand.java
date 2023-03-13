package seedu.duke.Command;


public class DeleteCommand extends Command{
    protected int taskNum;
    public DeleteCommand(String commandType, int taskNum){
        super(commandType);
        this.taskNum = taskNum;
    }

    @Override
    public void execute() {
        System.out.println("delete command");
    }
}
