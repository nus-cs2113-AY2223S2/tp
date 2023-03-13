package seedu.brokeMan.income;

import java.util.LinkedList;

public class IncomeList {
    private final LinkedList<Income> incomeList;
    private int listLength = 0;

    public IncomeList() {
        this.incomeList = new LinkedList<Income>();
    }

    /**
     * Adds new income to the list.
     * @param newIncome the new income to be added
     */
    public void addIncome(Income newIncome) {
        incomeList.add(newIncome);
        listLength++;
    }

    /**
     * delete specific income in the list
     * @param index index of the income in the list
     */
    public void deleteIncome(int index) {
        try {
            incomeList.remove(index);
            listLength--;
            System.out.println("Successfully deleted income.");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Invalid index! Please try again.\n");
        }
    }

    /**
     * return the income at specific index
     * @param index index of the income in the list
     * @return the income entry information
     */
    public Income getIncome(int index) {
        return incomeList.get(index);
    }

    /**
     * list out income in the list
     */
    public void listIncome() {
        System.out.println("Here are the income you have made\n");
        int counter = 1;
        for (Income incomeLog : incomeList) {
            String message = String.format("%s. %s", Integer.toString(counter), incomeLog.toString());
            System.out.println(message + '\n');
            counter++;
        }
    }

    /**
     * Edits a specific income entry in the list
     * @param type entry type of the expense to be changed
     * @param index index of the expense in the list
     * @param newEntry new entry that will replace current entry
     */
    public void editExpense(String type, int index, String newEntry) {
        try {
            Income incomeBeingEdited = incomeList.get(index);
            switch (type) {
            case "info": incomeBeingEdited.editInfo(newEntry);
                    break;
            case "amount": incomeBeingEdited.editIncome(Double.parseDouble(newEntry));
                    break;
            default: System.out.println("Invalid type parameter!");
            }
            System.out.println("Successfully edited expense.");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Invalid index! Please try again.\n");
        }
    }



}
