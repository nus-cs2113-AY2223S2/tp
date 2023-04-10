package utils.exceptions;

public class InkaException extends Exception{
    protected String uiMessage = "";
    InkaException(String uiMessage) {
        this.uiMessage = uiMessage;
    }

    public String getUiMessage() {
        return uiMessage;
    }

}
