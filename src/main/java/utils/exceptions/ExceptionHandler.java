package utils.exceptions;

import utils.cardlist.CardList;
import utils.command.Command;
import utils.command.ExceptionCommand;
import utils.parser.Parser;
import utils.userinterface.UserInterface;

public class ExceptionHandler {

    public Command mainExceptionHandler(Parser parser, String userCommand, UserInterface userInterface,
            CardList cardList) {
        Command command = new ExceptionCommand();
        try {
            command = parser.parseCommand(userCommand, cardList);
        } catch (DeleteMissingNumber e) {
            userInterface.printDeleteError();
        } catch (NumberFormatException e) {
            //print the error message here
            userInterface.printDeleteDateFormatError();
        } catch (DeleteRangeInvalid e) {
            //print the error message here
            userInterface.printDeleteDateRangeError();
        } catch (AddGoneWrong e) {
            //print the error here
            userInterface.addError();
        } catch (AddEmptyQuestion e) {
            //print something
            userInterface.addMissingQuestionPrompt();
        } catch (AddEmptyAnswer e) {
            //print something
            userInterface.addMissingAnswerPrompt();
        } catch (AddEmptyQuestionAndAnswer e) {
            //print something here
            userInterface.addMissingQuestionAndAnswerPrompt();
        }
        return command;
    }
}
