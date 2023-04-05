package seedu.duke.parsers;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.parsers.AddParser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for AddParser
 * Conducts the following test cases:
 * 1. Add item with all parameters
 * 2. Add item with missing parameter(s)
 * 3. Add item with invalid parameter(s)
 */
class AddParserTest {

    @Test
    void addItemWithAllParameters() {
        String commandInfo = "n/Test Item 1 upc/12345678910 qty/56 p/2.1";
        Inventory inventory = new Inventory();
        AddParser addParser = new AddParser(commandInfo, inventory);
        addParser.run();
        assertEquals(1, inventory.getItemInventory().size());
    }

    @Test
    void addItemWithOneMissingParameter() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String commandInfo = "n/Test Item 1 qty/56 p/2.1";
        Inventory inventory = new Inventory();
        AddParser addParser = new AddParser(commandInfo, inventory);
        addParser.run();
        assertEquals(0, inventory.getItemInventory().size());
        String expectedOutput = "Tip: Ensure that your UPC, quantity and price are all positive numbers and within " +
                "valid range";;
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void addItemWithMultipleMissingParameters() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String commandInfo = "n/Test Item 1 qty/56";
        Inventory inventory = new Inventory();
        AddParser addParser = new AddParser(commandInfo, inventory);
        addParser.run();
        assertEquals(0, inventory.getItemInventory().size());
        String expectedOutput = "Tip: Ensure that your UPC, quantity and price are all positive numbers and within " +
                "valid range";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void addItemWithEmptyParameter() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String commandInfo = "";
        Inventory inventory = new Inventory();
        AddParser addParser = new AddParser(commandInfo, inventory);
        addParser.run();
        assertEquals(0, inventory.getItemInventory().size());
        String expectedOutput = "Tip: Ensure that your UPC, quantity and price are all positive numbers and within " +
                "valid range";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void addItemWithOutOfRangeQty() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String commandInfo = "n/Test Item 1 upc/12345678910 qty/56000000000000 p/2.1";
        Inventory inventory = new Inventory();
        AddParser addParser = new AddParser(commandInfo, inventory);
        addParser.run();
        assertEquals(0, inventory.getItemInventory().size());
        String expectedOutput = "The number you have entered exceeds the maximum limit of 99,999,999.";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void addItemWithOutOfRangePrice() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String commandInfo = "n/Test Item 1 upc/12345678910 qty/5 p/123456789123456.52";
        Inventory inventory = new Inventory();
        AddParser addParser = new AddParser(commandInfo, inventory);
        addParser.run();
        assertEquals(0, inventory.getItemInventory().size());
        String expectedOutput = "The number you have entered exceeds the maximum limit of 99,999,999.";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void addItemWithEmptyValues() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String commandInfo = "n/Test Item 1 upc/ qty/ p/";
        Inventory inventory = new Inventory();
        AddParser addParser = new AddParser(commandInfo, inventory);
        addParser.run();
        assertEquals(0, inventory.getItemInventory().size());
        String expectedOutput = "Tip: Ensure that your UPC, quantity and price are all positive numbers and within " +
                "valid range";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void addItemWithWrongUPCType() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String commandInfo = "n/Test Item 1 upc/abcdefg qty/1 p/2.5";
        Inventory inventory = new Inventory();
        AddParser addParser = new AddParser(commandInfo, inventory);
        addParser.run();
        assertEquals(0, inventory.getItemInventory().size());
        String expectedOutput = "Tip: Ensure that your UPC, quantity and price are all positive numbers and within " +
                "valid range";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void addItemWithWrongQtyType() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String commandInfo = "n/Test Item 1 upc/12345678910 qty/abc p/2.5";
        Inventory inventory = new Inventory();
        AddParser addParser = new AddParser(commandInfo, inventory);
        addParser.run();
        assertEquals(0, inventory.getItemInventory().size());
        String expectedOutput = "Tip: Ensure that your UPC, quantity and price are all positive numbers and within " +
                "valid range";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    void addItemWithWrongPriceType() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String commandInfo = "n/Test Item 1 upc/12345678910 qty/2 p/ttthhhasasad";
        Inventory inventory = new Inventory();
        AddParser addParser = new AddParser(commandInfo, inventory);
        addParser.run();
        assertEquals(0, inventory.getItemInventory().size());
        String expectedOutput = "Tip: Ensure that your UPC, quantity and price are all positive numbers and within " +
                "valid range";
        assertTrue(outContent.toString().contains(expectedOutput));
    }
}
