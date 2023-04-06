package seedu.duke;

import seedu.duke.company.CompanyList;
import seedu.duke.data.VenueListData;
import seedu.duke.event.Event;
import seedu.duke.exception.TooManyVariablesException;
import seedu.duke.exception.IntegerSizeExceededException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.CompanyListDecoder;
import seedu.duke.storage.EventDetailsStorage;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.command.Command;
import seedu.duke.venue.VenueList;
import seedu.duke.exception.WrongFormatException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class to manage all operations
 */
public class Eventus {
    private Storage storage;
    private CompanyList companyList;
    private VenueList venueList;
    private Event event;
    private Ui ui;

    /**
     * Initialise the storage, ui, company list, venue list, event, and executes the program
     */
    public Eventus() {
        storage = new Storage();
        ui = new Ui();
        companyList = new CompanyList(new ArrayList<>(), ui);
        event = new Event(companyList, ui);
        venueList = new VenueList(VenueListData.returnVenueList(), ui);
        run();
    }

    public void run() {
        ui.showWelcome();
        loadSavedInformation();
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            try {
                Command c = Parser.parse(input);
                if (c.getCommandType().equals("list venues")) {
                    c.execute(venueList);
                } else if (c.getCommandType().equals("choose venue")) {
                    c.execute(event, venueList);
                } else if (c.getCommandType().equals("update event name")) {
                    c.execute(event, venueList);
                } else {
                    c.execute(companyList);
                }
            } catch (WrongFormatException | NullPointerException | IndexOutOfBoundsException err) {
                ui.showLine();
                System.out.println("Wrong Format! Please type <help> for more information");
                ui.showLine();
            } catch (NumberFormatException err) {
                ui.showLine();
                System.out.println("Number expected! Please type <help> for more information");
                ui.showLine();
            } catch (TooManyVariablesException err) {
                ui.showLine();
                System.out.println("Too many input field variables! Please type <help> for more information");
                ui.showLine();
            } catch (IntegerSizeExceededException err) {
                ui.showLine();
                System.out.println("Integer value exceeds the maximum integer size. Please try a smaller number");
                ui.showLine();
            }
        }
    }

    /**
     * Loads the data from text files
     */
    public void loadSavedInformation() {
        EventDetailsStorage.eventDetailsInit(event, venueList);
        CompanyListDecoder.read(companyList);
    }

    public static void main(String[] args) {
        new Eventus();
    }
}
