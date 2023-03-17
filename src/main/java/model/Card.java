package model;

import java.util.ArrayList;
import java.util.UUID;

//to be made into an abstract class containing a few types of Cards later, for now just a single Card will do
public class Card {
    private UUID uuid; //to be made into a hash later
    private String question;
    private String answer;
    private ArrayList<UUID> tags = new ArrayList<>();

    // Temporary constructor for Card, to be revised later to also consider uuid and tag etc
    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.uuid =  UUID.randomUUID();
        tags.add(UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454"));
    }

    public UUID getUuid() {
        return this.uuid ;
    }

    public ArrayList<UUID> getTagsUUID () {
        return this.tags;
    }
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void addTag(UUID tagUUID) {
        tags.add(tagUUID);
    }
    public void removeTag(UUID tagUUID) {
        tags.remove(tagUUID);
    }


    @Override
    public String toString() {
        return "Qn: " + this.question + "\nAns: " + this.answer + "\nUUID:  " + this.uuid ;
    } // return the card
}
