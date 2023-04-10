package seedu.duke.utils.parsers;

import seedu.duke.commands.HelpCommand;

public class HelpParser extends Parser{
    public HelpParser(){
        super();
    }
    @Override
    public void run(){
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.run();
    }
}
