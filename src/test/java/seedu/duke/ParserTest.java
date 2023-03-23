package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ParserTest {
    private Parser parser;
    private EventList eventList;

    @BeforeEach
    void setup() throws NPExceptions {
        parser = new Parser();
        eventList = new EventList();
        eventList.addEvent("test0", "20:00", "2023/03/14");
    }

    @Test
    void testParseAddCommand() {
        parser.parseCommand("add -e test -st 20:00 -sd 2023/03/14", eventList);
        assert (eventList.getSize() == 2
                && (eventList.getDetails(1).equals("[E] test (2023/03/14 20:00 not recurring)")));
    }

    @Test
    void testParserDeleteCOmmand() {
        parser.parseCommand("delete -s 1", eventList);
        assert (eventList.getSize() == 0);
    }
    // @Test
    // void testEditCommand(){
    // parser.parseCommand("edit -i 0 -st 20:00 -sd 2023/03/14", eventList);
    // assert(eventList.getSize()==0);
    // }
}
