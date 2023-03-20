package seedu.duke;

import seedu.duke.company.Company;
import seedu.duke.company.CompanyList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.storage.VenueListStorage;
import seedu.duke.ui.Ui;
import seedu.duke.command.Command;
import seedu.duke.venue.VenueList;
import seedu.duke.exception.WrongFormatException;

import java.util.ArrayList;
import java.util.Scanner;

public class Eventus {

    private Storage storage;
    private CompanyList companyList;
    private VenueList venueList;
    private Ui ui;

    public Eventus() {
        storage = new Storage();
        ArrayList<Company> companyArrayList = new ArrayList<>();
        companyList = new CompanyList(companyArrayList);
        venueList = new VenueList(VenueListStorage.venueListInit());
        ui = new Ui();
        run();
    }

    public void run() {
        ui.showWelcome();
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            try {
                Command c = Parser.parse(input);
                if (c.getCommandType().equals("list venues")){
                    c.execute(venueList);
                } else {
                    c.execute(companyList);
                }
            } catch (WrongFormatException err){
                System.out.println("Wrong Format! Please type <help> for more information");
            } catch (NumberFormatException err){
                System.out.println("Number expected! Please type <help> for more information");
            }
        }
    }

    public static void main(String[] args) {
        new Eventus();
    }
}
