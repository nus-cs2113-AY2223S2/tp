import seedu.duke.Command.Command;
import seedu.duke.Parser;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.WrongFormatException;
import seedu.duke.venue.VenueList;

import java.util.Scanner;

public class Eventus {

    public static void main(String[] args) {
        Ui ui = new Ui();
        CompanyList companyList = new CompanyList();
        VenueList venueList = new VenueList(Storage.venueListInit());
        ui.showWelcome();
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            try {
                Command c = Parser.parse(input);
                c.execute(venueList);
            } catch (WrongFormatException err){
                System.out.println("Wrong Format! Please type <help> for more information");
            }
        }
    }
}
