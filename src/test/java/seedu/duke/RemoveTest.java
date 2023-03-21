//package seedu.duke;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class RemoveTest {
//
//    @Test
//    void removeItemAtIndex() {
//        Item item1 = new Item("appleA", "123", "000", "0.0");
//        Item item2 = new Item("apples", "012345678", "5000", "12.0");
//        Item item3 = new Item("oranges", "876543210", "3000", "0.32");
//        Inventory.addItem(item1);
//        Inventory.addItem(item2);
//        Inventory.addItem(item3);
//
//        Inventory.removeItemAtIndex(2, "Y");
//
//        assertEquals(2, Inventory.getItemList().size());
//    }
//
//    @Test
//    void removeByUpc() {
//        Item item1 = new Item("appleA", "123", "100", "0.0");
//        Item item2 = new Item("apples", "012345678", "5000", "12.0");
//        Item item3 = new Item("oranges", "876543210", "3000", "0.32");
//        Inventory.addItem(item1);
//        Inventory.addItem(item2);
//        Inventory.addItem(item3);
//
//        Inventory.removeByUpc(item3, "876543210", "y");
//        assertEquals(2, Inventory.getItemList().size());
//        Inventory.removeByUpc(item2, "012345678", "reply");
//        assertEquals(2, Inventory.getItemList().size());
//        Inventory.removeByUpc(item2, "012345678", "Y");
//        assertEquals(1, Inventory.getItemList().size());
//    }
//}
