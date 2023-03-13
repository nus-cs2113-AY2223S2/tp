package seedu.duke.command;


public class ListCompanyCommand extends Command{
    public ListCompanyCommand(String commandType){
        super(commandType);
    }

    @Override
    public void execute() {
        System.out.println("list company command");
    }
}
