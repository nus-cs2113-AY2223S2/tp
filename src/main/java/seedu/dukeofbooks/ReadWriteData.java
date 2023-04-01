package seedu.dukeofbooks;

import seedu.dukeofbooks.controller.InventoryController;
import seedu.dukeofbooks.controller.SearchController;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.book.Isbn;
import seedu.dukeofbooks.data.book.Title;
import seedu.dukeofbooks.data.book.Topic;
import seedu.dukeofbooks.data.exception.IllegalOperationException;
import seedu.dukeofbooks.data.exception.IllegalValueException;
import seedu.dukeofbooks.data.inventory.Inventory;
import seedu.dukeofbooks.data.inventory.InventoryDetails;
import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.data.person.PersonName;
import seedu.dukeofbooks.data.person.Phone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ReadWriteData {

    public static final String FILEPATH = "database/books.txt";

    public static final Integer ISBNSUBSTRING = 6;

    public static final Integer TITLESUBSTRING = 7;

    public static final Integer PERSONSUBSTRING = 8;

    public static final Integer TOPICSUBSTRING = 7;

    public static void readData(Inventory inventory) throws FileNotFoundException {
        String originalString;
        Isbn isbn;
        Title title;
        PersonName authorName;
        Phone authorPhone;
        Person author;
        Topic topic;
        Book book;

        File f = new File(FILEPATH);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            originalString = s.nextLine();
            while (!originalString.equals("NEXTBOOK")) {
                try {
                    InventoryController.setData(inventory);
                    SearchController.setData(inventory);
                    isbn = new Isbn(originalString.substring(ISBNSUBSTRING));
                    originalString = s.nextLine();
                    title = new Title(originalString.substring(TITLESUBSTRING));
                    originalString = s.nextLine();
                    authorName = new PersonName(originalString.substring(PERSONSUBSTRING));
                    authorPhone = new Phone(Phone.DEFAULT_PHONE_NUMBER);
                    author = new Person(authorName, authorPhone);
                    originalString = s.nextLine();
                    topic = new Topic(originalString.substring(TOPICSUBSTRING));
                    book = new Book(isbn, title, topic, author);
                    InventoryController.addBook(book);
                    originalString = s.nextLine();
                } catch (IllegalValueException | IllegalOperationException ive) {
                    ive.printStackTrace();
                }
            }
        }
    }

    public static void writeData(Inventory inventory) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        HashMap<Book, InventoryDetails> inventoryListing = inventory.getInventoryMap();

        inventoryListing.forEach((key, value) -> {
            try {
                fw.write(key.toString()+'\n'+"NEXTBOOK"+'\n');
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        fw.close();
    }
}
