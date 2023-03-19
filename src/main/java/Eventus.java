import eventus.company.Company;
import seedu.duke.CompanyList;
import seedu.duke.Parser;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.command.Command;
import seedu.duke.venue.VenueList;
import seedu.duke.WrongFormatException;

import java.util.ArrayList;
import java.util.Scanner;

public class Eventus {

    public static void main(String[] args) {
        assert false : "dummy assertion set to fail";

        Ui ui = new Ui();
        ArrayList<Company> companyArrayList = new ArrayList<>();
        CompanyList companyList = new CompanyList(companyArrayList);
        VenueList venueList = new VenueList(Storage.venueListInit());
        ui.showWelcome();
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            try {
                Command c = Parser.parse(input);
                if (c.getCommandType().equals("list venues")){
                    c.execute(venueList);
                }
                else {
                    c.execute(companyList);
                }
            } catch (WrongFormatException err){
                System.out.println("Wrong Format! Please type <help> for more information");
            }
        }
    }
}
