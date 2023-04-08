package bagpacker.iohandlertest;
import bagpacker.commands.AddCommand;
import bagpacker.commands.ByeCommand;
import bagpacker.commands.DeleteCommand;
import bagpacker.commands.HelpCommand;
import bagpacker.commands.IncorrectCommand;
import bagpacker.commands.ListCommand;
import bagpacker.commands.PackCommand;
import bagpacker.commands.UnpackCommand;
import bagpacker.exception.EmptyInputException;
import bagpacker.iohandler.Parser;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * ParserTest to test Parser Class
 */
public class ParserTest {
    @Test
    public void fullInputTest() {
        String userInput = "  add this item  ";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream);
        Parser.parse();
        assertEquals(userInput.trim(), Parser.getFullInput());
    }

    @Test
    public void addCommandTest() {
        String userInput = "  add 500 /of this item  ";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream);
        assertEquals(AddCommand.class, Parser.parse().getClass());
    }

    @Test
    public void emptyInputTest() {
        String userInput = "   ";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream);
        Exception exception = assertThrows(EmptyInputException.class, Parser::readLine);
        assertEquals(EmptyInputException.class, exception.getClass());
    }

    @Test
    public void deleteCommandTest() {
        String userInput1 = "  add 3 /of this item  ";
        String userInput2 = "  delete 1 ";
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        PackingList dummyPackingList = new PackingList();
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        assertEquals(DeleteCommand.class, Parser.parse().getClass());
    }

    @Test
    public void packCommandTest() {
        String userInput1 = "  add 3 /of this item  ";
        String userInput2 = "  pack 1 /of 1";
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        PackingList dummyPackingList = new PackingList();
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        assertEquals(PackCommand.class, Parser.parse().getClass());
    }

    @Test
    public void unpackCommandTest() {
        String userInput1 = "  add 3 /of this item  ";
        String userInput2 = "  pack 2 /of 1";
        String userInput3 = "  Unpack 1 /of 1";
        PackingList dummyPackingList = new PackingList();

        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);

        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);

        inStream = new ByteArrayInputStream(userInput3.getBytes());
        System.setIn(inStream);
        assertEquals(UnpackCommand.class, Parser.parse().getClass());
    }

    @Test
    public void listCommandTest() {
        String userInput1 = "  add 1 /of this item  ";
        String userInput2 = "  lIST ";
        PackingList dummyPackingList = new PackingList();

        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);

        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);

        assertEquals(ListCommand.class, Parser.parse().getClass());
    }

    @Test
    public void helpCommandTest() {
        String userInput1 = "  HelP  ";

        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);

        assertEquals(HelpCommand.class, Parser.parse().getClass());
    }

    @Test
    public void byeCommandTest() {
        String userInput1 = "  byE  ";

        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);

        assertEquals(ByeCommand.class, Parser.parse().getClass());
    }

    @Test
    public void invalidCommandTest() {
        String userInput1 = " thisIsNOTaVALID command  ";

        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);

        assertEquals(IncorrectCommand.class, Parser.parse().getClass());
    }
}
