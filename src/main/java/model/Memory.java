package model;

import java.util.ArrayList;

public class Memory {

    private TagList tagList;

    private DeckList deckList;

    private CardList cardList;

    public Memory() {

        this.tagList = new TagList();
        this.deckList = new DeckList();
        this.cardList = new CardList();


    }

    public CardList getCardList() {
        return cardList;
    }

    public DeckList getDeckList() {
        return deckList;
    }

    public TagList getTagList() {
        return tagList;
    }

    public void setCardList(CardList cardList) {
        this.cardList = cardList;
    }

    public void setDeckList(DeckList deckList) {
        this.deckList = deckList;
    }

    public void setTagList(TagList tagList) {
        this.tagList = tagList;
    }
}
