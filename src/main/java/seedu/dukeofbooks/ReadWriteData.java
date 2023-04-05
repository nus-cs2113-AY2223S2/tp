package seedu.dukeofbooks;

import seedu.dukeofbooks.controller.InventoryController;
import seedu.dukeofbooks.controller.ListController;
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
import seedu.dukeofbooks.data.user.User;
import seedu.dukeofbooks.data.user.UserRecords;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadWriteData {

    public static final String BOOK_FILEPATH = "database/books.txt";

    public static final String USER_FILEPATH = "database/users.txt";

    public static final Integer ISBN_SUBSTRING = 6;

    public static final Integer TITLE_SUBSTRING = 7;

    public static final Integer PERSON_SUBSTRING = 8;

    public static final Integer TOPIC_SUBSTRING = 7;

    private static final int USERNAME_POSITION = 0;
    private static final int PASSWORD_HASH_POSITION = 1;
    private static final int NAME_POSITION = 2;

    public static void readBookData(Inventory inventory) throws FileNotFoundException {
        String originalString;
        Isbn isbn;
        Title title;
        PersonName authorName;
        Phone authorPhone;
        Person author;
        Topic topic;
        Book book;

        File f = new File(BOOK_FILEPATH);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            originalString = s.nextLine();
            while (!originalString.equals("NEXTBOOK")) {
                try {
                    InventoryController.setData(inventory);
                    SearchController.setData(inventory);
                    ListController.setData(inventory);
                    isbn = new Isbn(originalString.substring(ISBN_SUBSTRING));
                    originalString = s.nextLine();
                    title = new Title(originalString.substring(TITLE_SUBSTRING));
                    originalString = s.nextLine();
                    authorName = new PersonName(originalString.substring(PERSON_SUBSTRING));
                    authorPhone = new Phone(Phone.DEFAULT_PHONE_NUMBER);
                    author = new Person(authorName, authorPhone);
                    originalString = s.nextLine();
                    topic = new Topic(originalString.substring(TOPIC_SUBSTRING));
                    book = new Book(isbn, title, topic, author);
                    InventoryController.addBook(book);
                    originalString = s.nextLine();
                } catch (IllegalValueException | IllegalOperationException ive) {
                    ive.printStackTrace();
                }
            }
        }
    }

    public static void readUserData(UserRecords userRecords) throws FileNotFoundException {
        File f = new File(USER_FILEPATH);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] args = line.split(",");
            String username = args[USERNAME_POSITION];
            int passwordHash = Integer.parseInt(args[PASSWORD_HASH_POSITION]);
            String name = unquoteString(args[NAME_POSITION]);
            assert userRecords.addUser(username, passwordHash, name) : "Cannot insert data: " + line;
        }
    }

    public static void writeBookData(Inventory inventory) throws IOException {
        FileWriter fw = new FileWriter(BOOK_FILEPATH);
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

    public static void writeUserData(UserRecords userRecords) throws IOException {
        FileWriter fw = new FileWriter(USER_FILEPATH);
        Map<String, User> users = userRecords.getInternalRecord();

        users.forEach((username, user) -> {
            if (username.equals(userRecords.getRootUsername())) {
                return ;  // skips this iteration
            }
            try {
                fw.write(username + ","+ user.getPasswordHash()+ "," + quoteString(user.getName().toString()) +
                        System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        fw.close();
    }

    private static String quoteString(String input) {
        return "\"" + input.replace("\"", "\\\"") + "\"";
    }

    private static String unquoteString(String input) {
        if (input.startsWith("\"") && input.endsWith("\"")) {
            return input.substring(1, input.length() - 1).replace("\\\"", "\"");
        } else {
            throw new IllegalArgumentException("Invalid quoted string format");
        }
    }
}
