package seedu.brokeMan.exception;

public class CategoryNotCorrectException extends BrokeManException{
    public String getMessage() {return "Category should be one of the enum tags: \n"+
            "    SHOPPING, GROCERIES, TRANSPORTATION, ENTERTAINMENT, TRAVEL,\n" +
            "    SALARY, INVESTMENT,\n" +
            "    OTHERS"; }
}
