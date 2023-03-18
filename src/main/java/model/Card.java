package model;

import java.util.ArrayList;
import java.util.UUID;

//to be made into an abstract class containing a few types of Cards later, for now just a single Card will do
public class Card {
    private CardUUID uuid; //to be made into a hash later
    private String question;
    private String answer;
    private ArrayList<TagUUID> tags = new ArrayList<>();

    // Temporary constructor for Card, to be revised later to also consider uuid and tag etc
    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.uuid = new CardUUID(UUID.randomUUID());
    }

    public CardUUID getUuid() {
        return this.uuid;
    }

    public ArrayList<TagUUID> getTagsUUID() {
        return this.tags;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void addTag(TagUUID tagUUID) {
        tags.add(tagUUID);
    }

    public void removeTag(TagUUID tagUUID) {
        tags.remove(tagUUID);
    }

    @Override
    public String toString() {
        return "Qn: " + this.question + "\nAns: " + this.answer + "\nUUID:  " + this.uuid;
    } // return the card
}
