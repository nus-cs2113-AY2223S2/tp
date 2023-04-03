package seedu.duke.exceptions;


import seedu.duke.utils.Ui;

public class HistoryErrorException extends Exception{
    public void incorrectParameters(){
        System.out.println("Wrong/Incomplete Format! Please use the format: history [UPC]" );
        Ui.printLine();
    }
}
