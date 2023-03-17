package utils;

import model.Card;
import model.CardList;


import utils.command.AddCardCommand;
import utils.command.Command;
import utils.command.DeleteCommand;
import utils.command.ExceptionCommand;
import utils.command.ExportCommand;
import utils.command.ListCommand;
import utils.command.TerminateCommand;
import utils.exceptions.AddEmptyAnswer;
import utils.exceptions.AddEmptyQuestion;
import utils.exceptions.AddEmptyQuestionAndAnswer;
import utils.exceptions.AddGoneWrong;
import utils.exceptions.DeleteMissingNumber;
import utils.exceptions.DeleteRangeInvalid;

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

    public Command parseCommand(String userCommand, CardList cardList)
            throws DeleteMissingNumber, DeleteRangeInvalid, AddGoneWrong, AddEmptyQuestion, AddEmptyAnswer,
            AddEmptyQuestionAndAnswer {
        String[] userCommandSplit = userCommand.split("-", 3);
        assert userCommandSplit.length >=1 : "User Command must be specified";
        if (userCommandSplit[0].startsWith("list")) {
            return new ListCommand();
        } else if (userCommandSplit[0].startsWith("add") || userCommandSplit[0].startsWith(" add")) {
            if (userCommandSplit.length < 3) {
                throw new AddGoneWrong();
            } else if (userCommandSplit[1].isBlank() && userCommandSplit[2].isBlank()) {
                assert userCommandSplit.length < 3 : "Questions and answers should be specified " ;
                throw new AddEmptyQuestionAndAnswer();
            } else if (userCommandSplit[1].isBlank()) {
                throw new AddEmptyQuestion();
            } else if (userCommandSplit[2].isBlank()) {
                throw new AddEmptyAnswer();
            }
            String question = userCommandSplit[1];
            String answer = userCommandSplit[2];
            Card card = new Card(question, answer);
            return new AddCardCommand(card); // main command return
        } else if (userCommandSplit[0].startsWith("delete") || userCommandSplit[0].startsWith("delete ")) {

            if (userCommandSplit.length == 1) {
                throw new DeleteMissingNumber();
            } else if (Integer.parseInt(userCommandSplit[1]) < 1
                    || Integer.parseInt(userCommandSplit[1]) > cardList.size()) {

                throw new DeleteRangeInvalid();
            }
            int deleteIndex = Integer.parseInt(userCommandSplit[1]);
            assert deleteIndex >= 0 : "deleteIndex should be a number";
            return new DeleteCommand(deleteIndex);
        } else if (userCommandSplit[0].startsWith("export") || userCommandSplit[0].startsWith("export ")) {
            return new ExportCommand();
        } else if (userCommandSplit[0].startsWith("bye")) {
            this.setIsExecuting(false);
            return new TerminateCommand();
        } else {
            return new ExceptionCommand();
        }
    }
}
