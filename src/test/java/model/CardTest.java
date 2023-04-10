package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import utils.exceptions.InvalidUUIDException;

public class CardTest {

    private static final String INVALID_UUID = "UUID needs to be in 03658854-e5d4-468f-8c41-74917e5d4515 format";

    private Card card1;

    @Test
    void cardCreationInvalidUUID() {
        try {
            this.card1 = Card.createCardWithUUID("test question", "test answer", "invalid uuid string");
        } catch (InvalidUUIDException e) {
            assertEquals(INVALID_UUID, e.getUiMessage());
        }
    }
}
