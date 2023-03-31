package seedu.duke.exceptions;


public class HistoryErrorException extends Exception{
    public void incorrectParameters(){
        System.out.println("Wrong/Incomplete Format! Please use the format: history [UPC]" );
    }
}
