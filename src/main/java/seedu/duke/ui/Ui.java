package seedu.duke.ui;

import seedu.duke.company.Company;

import java.util.ArrayList;

public class Ui {

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! Welcome to EveNtUS!");
        System.out.println("Type <help> to get started");
        showLine();
    }

    public void showGuide() {
        showLine();
        showHelpText();
        showLine();
    }

    public void showExitMessage() {
        showLine();
        System.out.println("Bye!");
        showLine();
    }

    public void showHelpText() {
        System.out.println("Here are the functions of the application!");
        System.out.println("To display this guide at any time, type:");
        System.out.println("                    help");
        System.out.println(" ");
        System.out.println("To list companies, type:");
        System.out.println("                    list companies");
        System.out.println(" ");
        System.out.println("To list venues, type:");
        System.out.println("                    list venues");
        System.out.println(" ");
        System.out.println("To list unconfirmed attendees, type:");
        System.out.println("                    list unconfirmed");
        System.out.println(" ");
        System.out.println("To add a company, type:");
        System.out.println("                    add n/<COMPANY_NAME> i/<INDUSTRY> c/<CONTACT_NUMBER> e/<EMAIL>");
        System.out.println(" ");
        System.out.println("To delete company, type:");
        System.out.println("                    delete <INDEX>");
        System.out.println(" ");
        System.out.println("To mark a company's attendance, type:");
        System.out.println("                    confirm <INDEX>");
        System.out.println(" ");
        System.out.println("To unmark a company's attendance, type:");
        System.out.println("                    unconfirm <INDEX>");
        System.out.println(" ");
        System.out.println("To load sample data, type:");
        System.out.println("                    load samples");
        System.out.println(" ");
        System.out.println("To purge company list data, type:");
        System.out.println("                    purge");
        System.out.println(" ");
        System.out.println("To search for a company, type:");
        System.out.println("                    find company <COMPANY_NAME>");
        System.out.println(" ");
        System.out.println("To filter according to industry, type:");
        System.out.println("                    find industry <INDUSTRY>");
        System.out.println(" ");
        System.out.println("To choose a venue, type:");
        System.out.println("                    choose venue <INDEX>");
        System.out.println(" ");
        System.out.println("To exit the program, type:");
        System.out.println("                    exit");
        System.out.println(" ");
        showLine();
    }

    public void showSuccessfulDeletionMessage() {
        showLine();
        System.out.println("Company information successfully deleted!");
        showLine();
    }

    public void showSuccessfulAdditionMessage(String company) {
        showLine();
        System.out.println(company + " added successfully!");
        showLine();
    }

    public void showSuccessfulPurgingMessage() {
        showLine();
        System.out.println("Data has been deleted successfully!");
        showLine();
    }

    public void showSampleDataLoadedMessage() {
        showLine();
        System.out.println("Sample data has been loaded into the list!");
        showLine();
    }

    public void showSuccessfulConfirmedMessage() {
        showLine();
        System.out.println("This company has been successfully confirmed!");
        showLine();
    }

    public void showSuccessfulUnconfirmedMessage() {
        showLine();
        System.out.println("This company has been successfully uncomfirmed!");
        showLine();
    }

    public void showExistingConfirmationMessage() {
        showLine();
        System.out.println("This company is already confirmed!");
        showLine();
    }

    public void showExistingUnconfirmationMessage() {
        showLine();
        System.out.println("This company is already uncomfirmed!");
        showLine();
    }
      

    public void showSortedCompanyList(String industry, ArrayList<Company> sortedCompanyList) {
        showLine();
        System.out.println("Here are the companies in <" + industry + "> field.");
        int i = 1;
        for (Company company : sortedCompanyList) {
            System.out.println(i++ + ".");
            System.out.println(company);
        }
        showLine();
    }

    public void showCompanyFoundMessage(Company company, int idx) {
        showLine();
        System.out.println("The company is found in the company list.");
        System.out.println(idx + System.lineSeparator() + company);
        showLine();
    }

    public void showCompanyNotFoundMessage(String targetCompany) {
        showLine();
        System.out.println("The company <" + targetCompany + "> is not found in the company list.");
        showLine();
    }


    public void showVenueSelectionMessage(String venue) {
        showLine();
        System.out.println(venue + " is your venue!");
        showLine();
    }

    public void showEventNameSelectionMessage(String eventName) {
        showLine();
        System.out.println(eventName + " is your event name!");
        showLine();
    }
}
