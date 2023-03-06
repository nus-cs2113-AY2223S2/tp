public class Flashcard {
    public static final String QUESTION_START_INDICATOR = "/q";
    public static final String ANSWER_START_INDICATOR = "/a";
    private String questionText;
    private String answerText;

    public Flashcard(String userInput) {
        int positionOfStartOfQuestion = userInput.indexOf(QUESTION_START_INDICATOR);
        int positionOfStartOfAnswer = userInput.indexOf(ANSWER_START_INDICATOR);
        this.questionText = userInput.substring(positionOfStartOfQuestion +
                        QUESTION_START_INDICATOR.length(), positionOfStartOfAnswer).trim();
        this.answerText = userInput.substring(positionOfStartOfAnswer +
                ANSWER_START_INDICATOR.length()).trim();
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswerText() {
        return answerText;
    }
}
