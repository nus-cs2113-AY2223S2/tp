//package seedu.duke;
//
//import org.junit.jupiter.api.Test;
//import seedu.duke.objects.Inventory;
//import seedu.duke.objects.AlertList;
//import seedu.duke.utils.SessionManager;
//import seedu.duke.utils.parser.Parser;
//import seedu.duke.utils.parser.ParserHandler;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//class ParserTest {
//    @Test
//    void parseAdd() {
//        Inventory inventory = new Inventory();
//        AlertList alertList = new AlertList();
//        SessionManager session = SessionManager.getInstance();
//        ParserHandler parserHandler = new ParserHandler(inventory, session, alertList);
//        assertDoesNotThrow(() -> parser.parseAdd("n/orange upc/1231 qty/5 p/5", inventory));
//        assertNotEquals(0, inventory.getItemInventory().size());
//    }
//}
