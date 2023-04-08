package storage;

import data.Currency;
import data.Expense;
import data.ExpenseList;
import data.Time;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StorageTest {

    private static final String ROUND_UP_WARNING = "Expense amount is rounded back to 2 decimal points by default.";
    private static final String logo =
            "*******     **********" + System.lineSeparator() +
            "        *******     **********" + System.lineSeparator() +
            "        ***            ***" + System.lineSeparator() +
            "        ******         ***" + System.lineSeparator() +
            "        ******         ***" + System.lineSeparator() +
            "        ******         ***" + System.lineSeparator() +
            "        ***            ***" + System.lineSeparator() +
            "        ***            ***" + System.lineSeparator() +
            "        *******        ***" + System.lineSeparator() +
            "        *******        ***" + System.lineSeparator() +
            System.lineSeparator();

    public ExpenseList expenseList = new ExpenseList();
    public Storage storage = new Storage(expenseList);
    public Currency currency = new Currency();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;


    @Test
    public void loadExpenses_successful() {
        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(new BigDecimal("2.5"),
                new Time(LocalDate.parse("02-03-2012", formatter)),
                "travel", "SGD", new BigDecimal(1)));
        expenseList.setExpenseList(expenses);
        storage.saveExpenses("src/test/junit.json");
        ArrayList<Expense> testExpenses = storage.loadExpenses("src/test/junit.json");
        assertEquals(expenses, testExpenses);
    }

    @Test
    public void moreDPWarning() throws IOException {
        ArrayList<Expense> testExpenses = storage.loadExpenses("src/test/moreDPRoundingWarning.json");
        String expectedOutput = logo + "Welcome back!" + System.lineSeparator() +
                "More than 2 decimal places detected for Expense 1. " + ROUND_UP_WARNING;
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        String jsonString =
                "  [{\"expenseAmount\": 2.500," +
                "    \"expenseTime\": {" +
                "      \"date\": \"2012-03-02\"" +
                "    }," +
                "    \"description\": \"travel\"," +
                "    \"currencyType\": \"SGD\"," +
                "    \"rate\": 1" +
                "  }]";

        // restore json file
        File file = new File("src/test/moreDPRoundingWarning.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(jsonString);
        fileWriter.close();
    }

    @Test
    public void lessDPWarning() throws IOException {
        ArrayList<Expense> testExpenses = storage.loadExpenses("src/test/lessDPRoundingWarning.json");
        String expectedOutput = logo + "Welcome back!" + System.lineSeparator() +
                "Less than 2 decimal places detected for Expense 1. " + ROUND_UP_WARNING;
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
        String jsonString =
                "  [{\"expenseAmount\": 2.5," +
                        "    \"expenseTime\": {" +
                        "      \"date\": \"2012-03-02\"" +
                        "    }," +
                        "    \"description\": \"travel\"," +
                        "    \"currencyType\": \"SGD\"," +
                        "    \"rate\": 1" +
                        "  }]";
        // restore json file
        File file = new File("src/test/lessDPRoundingWarning.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(jsonString);
        fileWriter.close();
    }



    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
