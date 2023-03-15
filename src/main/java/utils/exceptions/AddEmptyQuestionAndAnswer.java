package utils.exceptions;

public class AddEmptyQuestionAndAnswer extends InkaException {
    public AddEmptyQuestionAndAnswer() {
        super("Please ensure that you supply a valid question and answer to Inka!");
    }
}
