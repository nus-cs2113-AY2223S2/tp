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

    private static void showHelpText() {
        System.out.println("Here are the functions of the application!");
        System.out.println("To add a company, type:");
        System.out.println("        add n/<COMPANY_NAME> i/<INDUSTRY> c/<CONTACT_NUMBER> e/<EMAIL>");
        System.out.println(" ");
        System.out.println("To list companies, type:");
        System.out.println("                    list companies");
        System.out.println(" ");
        System.out.println("To list venues, type:");
        System.out.println("                    list venues");
        System.out.println(" ");
        System.out.println("To delete, type:");
        System.out.println("                    delete <INDEX>");
        System.out.println(" ");
        System.out.println("To display this guide at any time, type:");
        System.out.println("                    help");
        System.out.println(" ");
        System.out.println("To load sample data, type:");
        System.out.println("                    load samples");
        System.out.println(" ");
        System.out.println("To purge list data, type:");
        System.out.println("                    purge");
        System.out.println(" ");
        System.out.println("To search for a company, type:");
        System.out.println("                    find company <COMPANY_NAME>");
        System.out.println(" ");
        System.out.println("To filter according to industry, type:");
        System.out.println("                    find industry <INDUSTRY>");
        System.out.println(" ");
        System.out.println("To display unconfirmed attendees, type:");
        System.out.println("                    list unconfirmed");
        System.out.println(" ");
    }

    public void showSuccessfulDeletionMessage() {
        System.out.println("Company information successfully deleted!");
    }

    public void showSuccessfulAdditionMessage(String company) {
        System.out.println(company + " added successfully!");
    }

    public void showSuccessfulPurgingMessage() {
        System.out.println("Data has been deleted successfully!");
    }

    public void showSampleDataLoadedMessage() {
        System.out.println("Sample data has been loaded into the list!");
    }

    public void showSuccessfulConfirmedMessage() {
        System.out.println("Company has been successfully confirmed/uncomfirmed!");
    }

    public void showSortedCompanyList(String industry, ArrayList<Company> sortedCompanyList){
        System.out.println("Here are the companies in <" + industry + "> field.");
        int i=1;
        for(Company company : sortedCompanyList){
            System.out.println(i++ + ".");
            System.out.println(company);
        }
    }

    public void showCompanyFoundMessage(Company company, int idx){
        System.out.println("The company is found in the company list.");
        System.out.println(idx + System.lineSeparator() + company);
    }

    public void showCompanyNotFoundMessage(String targetCompany){
        System.out.println("The company <" + targetCompany + "> is not found in the company list.");
    }


    public void showVenueSelectionMessage(String venue) {
        System.out.println(venue + " has been successfully updated!");
    }

    public void showEventNameSelectionMessage(String eventName) {
        System.out.println(eventName + " has been successfully updated!");
    }

}
