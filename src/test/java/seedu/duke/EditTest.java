//package seedu.duke;
//
//import org.junit.jupiter.api.Test;
//import seedu.duke.exceptions.EditErrorException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//class EditTest {
//
//    Inventory inventory = new Inventory();
//    Parser testParser = new Parser();
//
//    @Test
//    public void sampleTest() {
//        testParser.parseAdd("n/orange upc/123 qty/5 p/5");
//        testParser.parseEdit("upc/123 n/apple");
//        String[] data = {"upc/123"};
//        Item updatedItem = null;
//        try {
//            updatedItem = Inventory.retrieveItemFromHashMap(data);
//        } catch (EditErrorException eee) {
//            fail();
//        }
//        assertEquals("apple", updatedItem.getName(), "Name not changed to apple.");
//    }
//}
