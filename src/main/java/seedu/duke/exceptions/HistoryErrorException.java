package seedu.duke.exceptions;


import seedu.duke.utils.Ui;

public class HistoryErrorException extends Exception{
    public void incorrectParameters(){
        System.out.println("Wrong/Incomplete Entry For History! Please refer to UG for more information\nSample" +
                " Format: \"history [UPC]\"" );
        Ui.printLine();
    }
}
