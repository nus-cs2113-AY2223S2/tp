package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import utils.exceptions.InvalidUUIDException;

public class CardSelectorTest {

    private static final String INVALID_UUID_STR = "UUID needs to be in 03658854-e5d4-468f-8c41-74917e5d4515 format";

    private CardSelector cardSelector;

    @Test
    void cardSelectorInvalidUUID() {
        try {
            this.cardSelector = new CardSelector("weird uuid string");
        } catch (InvalidUUIDException e) {
            assertEquals(INVALID_UUID_STR, e.getUiMessage());
        }
    }
}
