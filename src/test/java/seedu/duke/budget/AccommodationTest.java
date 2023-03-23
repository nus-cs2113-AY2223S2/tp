package seedu.duke.budget;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccommodationTest {

    @Test
    public void initialiseAccommodation_integerType_success() {
        int testPrice = 20;
        Accommodation accommodation = new Accommodation(testPrice);
        assertEquals(testPrice, accommodation.getPrice());
    }

    @Test
    public void changeAccommodationPrice_integerType_success() {
        int testPrice = 1000;
        int changePrice = 20;
        Accommodation accommodation = new Accommodation(testPrice);
        accommodation.setPrice(changePrice);
        assertEquals(changePrice, accommodation.getPrice());
    }
}