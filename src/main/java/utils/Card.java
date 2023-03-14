package utils;

//to be made into an abstract class containing a few types of Cards later, for now just a single Card will do
public class Card {
    private int uuid; //to be made into a hash later
    private String question;
    private String answer;

    // Temporary constructor for Card, to be revised later to also consider uuid and tag etc
    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public int getUuid() {
        return uuid;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }



    @Override
    public String toString() {
        return this.question + " " + this.answer;

    } // return the card


}
