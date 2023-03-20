package seedu.duke.ui;

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
        exitProgram();
    }

    public void exitProgram() {
        System.exit(0);
    }

    private static void showHelpText() {
        System.out.println("Here are the functions of the application!");
        System.out.println("To add a company, type:");
        System.out.println("        add n/<COMPANY_NAME> c/<CONTACT_NUMBER> e/<EMAIL>");
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
        System.out.println("                    find company");
        System.out.println(" ");
        System.out.println("To filter according to industry, type:");
        System.out.println("                    find industry");
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

    public void showSuccessfulDeleteMessage() {
        System.out.println("Data has been deleted successfully!");
    }

    public void showSampleDataLoadedMessage() {
        System.out.println("Sample data has been loaded into the list!");
    }

    public void showVenueSelectionMessage(String venue) {
        System.out.println(venue + " has been successfully updated!");
    }

}
