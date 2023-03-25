package app;

import item.Menu;
import order.Transaction;
import ui.MenuUi;
import ui.TransactionUi;
import utility.Ui;

import java.util.Scanner;


public class MoneyGoWhere {

    public Menu menu;
    public Transaction transactions;


    public MoneyGoWhere() {
        menu = new Menu();
        transactions = new Transaction();
    }

    /**
     * Runs the MoneyGoWhere application. This method prompts the user for input using the Ui class, creates a Command
     * object based on the user input, and passes the Command object to the Router to process the command.
     * The loop continues until the user types "exit" to exit the application.
     */
    public void run() {

        Ui ui = new Ui();
        MenuUi menuUi = new MenuUi();
        TransactionUi transactionUi = new TransactionUi();
        Scanner sc = new Scanner(System.in);
        Router router = new Router(menu, transactions, ui, menuUi, transactionUi);

        ui.printWelcomeMessage();

        while (true) {
            ui.promptUserInput();
            String userInput = sc.nextLine();

            if (userInput.equals("exit")) {
                ui.println(ui.getExitMessage());
                break;
            }

            Command command = new Command(userInput);
            router.handleRoute(command);
        }

        sc.close();
    }


}

