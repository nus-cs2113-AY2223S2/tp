package seedu.duke.budget;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirplaneTicketTest {

    @Test
    public void initialiseAirplaneTicket_integerType_success() {
        int testPrice = 20;
        AirplaneTicket airplaneTicket = new AirplaneTicket(testPrice);
        assertEquals(testPrice, airplaneTicket.getPrice());
    }

    @Test
    public void changeAirplaneTicketPrice_integerType_success() {
        int testPrice = 1000;
        int changePrice = 20;
        AirplaneTicket airplaneTicket = new AirplaneTicket(testPrice);
        airplaneTicket.setPrice(changePrice);
        assertEquals(changePrice, airplaneTicket.getPrice());
    }

}