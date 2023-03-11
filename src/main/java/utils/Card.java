package utils;

//to be made into an abstract class containing a few types of Cards later, for now just a single Card will do
public class Card {
    private int uuid ; //to be made into a hash later
    private String question;
    private String answer;
    @Override
    public String toString() {
        return this.question + " " + this.answer;
    }
}
