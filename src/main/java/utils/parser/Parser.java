package utils.parser;

import utils.Card;
import utils.cardlist.CardList;
import utils.command.*;
import utils.exceptions.*;
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

    public Command parseCommand(String userCommand, CardList cardList) throws DeleteMissingNumber, DeleteRangeInvalid , AddGoneWrong, AddEmptyQuestion, AddEmptyAnswer, AddEmptyQuestionAndAnswer{
        String[] userCommandSplit = userCommand.split("-", 3);
        if (userCommandSplit[0].startsWith("list")) {
            return new ListCommand();
        } else if (userCommandSplit[0].startsWith("add") || userCommandSplit[0].startsWith(" add")) {
            if(userCommandSplit.length<3) {
                throw new AddGoneWrong();
            } else if (userCommandSplit[1].isBlank() && userCommandSplit[2].isBlank()) {
                throw new AddEmptyQuestionAndAnswer();
            }else if(userCommandSplit[1].isBlank()) {
                throw new AddEmptyQuestion();
            } else if(userCommandSplit[2].isBlank()) {
                throw new AddEmptyAnswer();
            }
            String question = userCommandSplit[1];
            String answer = userCommandSplit[2];
            Card card = new Card(question, answer);
            return new AddCommand(card); // main command return
        } else if (userCommandSplit[0].startsWith("delete") || userCommandSplit[0].startsWith("delete ")) {
            if (userCommandSplit.length == 1) {
                throw new DeleteMissingNumber();
            } else if (Integer.parseInt(userCommandSplit[1]) < 1 || Integer.parseInt(userCommandSplit[1]) > cardList.size()) {
                throw new DeleteRangeInvalid();
            }
            int deleteIndex = Integer.parseInt(userCommandSplit[1]);
            return new DeleteCommand(deleteIndex);
        }else if (userCommandSplit[0].startsWith("export") || userCommandSplit[0].startsWith("export ")){
            return new ExportCommand();
        } else if (userCommandSplit[0].startsWith("bye")) {
            this.setIsExecuting(false);
            return new TerminateCommand();
        } else {
            return new ExceptionCommand();
        }
    }
}

