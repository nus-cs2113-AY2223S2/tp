package seedu.duke.budget;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntertainmentTest {

    @Test
    public void initialiseEntertainment_integerType_success() {
        int testPrice = 20;
        Entertainment entertainment = new Entertainment(testPrice);
        assertEquals(testPrice, entertainment.getPrice());
    }

    @Test
    public void changeEntertainmentPrice_integerType_success() {
        int testPrice = 1000;
        int changePrice = 20;
        Entertainment entertainment = new Entertainment(testPrice);
        entertainment.setPrice(changePrice);
        assertEquals(changePrice, entertainment.getPrice());
    }

}