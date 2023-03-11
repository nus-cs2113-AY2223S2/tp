package utils.parser;

import utils.userinterface.UserInterface;

public class Parser {
    private boolean isExecuting;

    public Parser() {
        this.isExecuting = true;
    }

    public boolean getIsExecuting() {
        return isExecuting;
    }

    public void parseCommand() {
        UserInterface ui = new UserInterface();
        String command = ui.getCommand();
        if (command.startsWith("card")) {

        }
    }

    public void parseCard(String line) {

    }


}


