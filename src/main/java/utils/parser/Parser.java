package utils.parser;

import utils.Card;
import utils.command.*;
import utils.userinterface.UserInterface;

public class Parser {
    private boolean isExecuting;

    public Parser() {
        this.isExecuting = true;
    }

    public boolean getIsExecuting() {
        return isExecuting;
    }

    public void setIsExecuting(boolean bool) {
        this.isExecuting = bool;
    }

    public Command parseCommand(String userCommand) {
        String[] userCommandSplit = userCommand.split("-", 3);
        if (userCommandSplit[0].startsWith("list")) {
            return new ListCommand();
        } else if (userCommandSplit[0].startsWith("add")) {
            String question = userCommandSplit[1];
            String answer = userCommandSplit[2];
            Card card = new Card(question, answer);
            return new AddCommand(card);
        } else if (userCommandSplit[0].startsWith("delete")) {
            int deleteIndex = Integer.parseInt(userCommandSplit[1]);
            return new DeleteCommand(deleteIndex);
        } else if (userCommandSplit[0].startsWith("bye")) {
            this.setIsExecuting(false);
            return new TerminateCommand();
        } else {
            return new ExceptionCommand();
        }
    }
}

