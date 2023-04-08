package model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.exceptions.InvalidUUIDException;

public class LogicTest {

    /**
     * Card that is added through CardUUID and Tag should only appear once
     */
    @Disabled
    @Test
    public void logic_noDuplicateCard() throws InvalidUUIDException {
        Card card = Card.createCardWithUUID("QUESTION", "ANSWER", "00000000-0000-0000-0000-000000000000");
        Tag tag = new Tag("testTag", card.getUuid());
        Deck deck = new Deck("testDeck");

        deck.addCard(card.getUuid());
        deck.addTag(tag.getUUID());

        assert deck.cardIsInDeck(card.getUuid());
        assert deck.getCardsSet().size() == 1;
    }

    /**
     * Card that is added through CardUUID and Tag should remain in Deck if only either is removed
     */
    @Disabled
    @Test
    public void logic_noDoubleDelete() {
    }
}
