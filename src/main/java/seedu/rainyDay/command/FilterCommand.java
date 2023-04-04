package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author ChongQiRong

/**
 * Represents a command that filters from the financial report
 */
public class FilterCommand extends Command {
    private static final Logger logger = Logger.getLogger(FilterCommand.class.getName());
    private final ArrayList<String> filterFlagAndField;
    public FilterCommand(ArrayList<String> filterFlagAndField) {
        this.filterFlagAndField = filterFlagAndField;
    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("FilterCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log FilterCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * Executes the filter command and returns the result
     *
     * @return CommandResult with the relevant success or error message
     */
    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting FilterCommand.execute()");

        ArrayList<FinancialStatement> filteredList = new ArrayList<>();
        ArrayList<Integer> statementIndex = new ArrayList<>();
        ArrayList<FinancialStatement> listToFilter = new ArrayList<>();
        ArrayList<Integer> listToFilterStatementIndex = new ArrayList<>();

        if (filterFlagAndField.get(0).equalsIgnoreCase("-in") ||
                filterFlagAndField.get(0).equalsIgnoreCase("-out")) {
            filterFlagAndField.add(filterFlagAndField.get(0));
            filterFlagAndField.remove(0);
        }

        boolean isFirstFlag = true;
        for (int i = 0; i < filterFlagAndField.size(); i += 2) {
            if (filterFlagAndField.get(i).equalsIgnoreCase("-d")) {
                isFirstFlag = false;
                filterByDescriptionFirstFlag(filteredList, statementIndex, i);
            } else if (filterFlagAndField.get(i).equalsIgnoreCase("-c")) {
                if (isFirstFlag) {
                    isFirstFlag = false;
                    filterByCategoryFirstFlag(filteredList, statementIndex, i);
                } else {
                    filterByCategory(filteredList, statementIndex, listToFilter, listToFilterStatementIndex, i);
                }
            } else if (filterFlagAndField.get(i).equalsIgnoreCase("-date")) {
                if (isFirstFlag) {
                    isFirstFlag = false;
                    filterByDateFirstFlag(filteredList, statementIndex, i);
                } else {
                    filterByDate(filteredList, statementIndex, listToFilter, listToFilterStatementIndex, i);
                }
            } else if (filterFlagAndField.get(i).equalsIgnoreCase("-in")) {
                if (isFirstFlag) {
                    isFirstFlag = false;
                    filterByInflowFirstFlag(filteredList, statementIndex);
                } else {
                    filterByInflow(filteredList, statementIndex, listToFilter, listToFilterStatementIndex);
                }
            } else if (filterFlagAndField.get(i).equalsIgnoreCase("-out")) {
                if (isFirstFlag) {
                    isFirstFlag = false;
                    filterByOutflowFirstFlag(filteredList, statementIndex);
                } else {
                    filterByOutflow(filteredList, statementIndex, listToFilter, listToFilterStatementIndex);
                }
            }

            listToFilter = filteredList;
            listToFilterStatementIndex = statementIndex;
            filteredList = new ArrayList<>();
            statementIndex = new ArrayList<>();
        }

        String output;
        if (listToFilter.size() == 0) {
            output = "We could not find any matches for your description in your report";
            return new CommandResult(output);
        }
        output = String.format("We found %s ", listToFilterStatementIndex.size()) + String.format(
                listToFilterStatementIndex.size() == 1 ? "matching item!" : "matching items!");
        CommandResult result = new CommandResult(output);
        ViewResult.printItemsInList(listToFilterStatementIndex);
        return result;
    }

    private static void filterByOutflow(ArrayList<FinancialStatement> filteredList, ArrayList<Integer> statementIndex,
                                        ArrayList<FinancialStatement> listToFilter,
                                        ArrayList<Integer> listToFilterStatementIndex) {
        for (int j = 0; j < listToFilter.size(); j += 1) {
            if (listToFilter.get(j).getFlowDirectionWord().equals("out")) {
                filteredList.add(listToFilter.get(j));
                statementIndex.add(listToFilterStatementIndex.get(j));
            }
        }
    }

    private void filterByOutflowFirstFlag(ArrayList<FinancialStatement> filteredList,
                                          ArrayList<Integer> statementIndex) {
        for (int j = 0; j < userData.getStatementCount(); j += 1) {
            if (userData.getStatement(j).getFlowDirectionWord().equals("out")) {
                filteredList.add(userData.getStatement(j));
                statementIndex.add(j + 1);
            }
        }
    }

    private static void filterByInflow(ArrayList<FinancialStatement> filteredList, ArrayList<Integer> statementIndex,
                                       ArrayList<FinancialStatement> listToFilter,
                                       ArrayList<Integer> listToFilterStatementIndex) {
        for (int j = 0; j < listToFilter.size(); j += 1) {
            if (listToFilter.get(j).getFlowDirectionWord().equals("in")) {
                filteredList.add(listToFilter.get(j));
                statementIndex.add(listToFilterStatementIndex.get(j));
            }
        }
    }

    private void filterByInflowFirstFlag(ArrayList<FinancialStatement> filteredList,
                                         ArrayList<Integer> statementIndex) {
        for (int j = 0; j < userData.getStatementCount(); j += 1) {
            if (userData.getStatement(j).getFlowDirectionWord().equals("in")) {
                filteredList.add(userData.getStatement(j));
                statementIndex.add(j + 1);
            }
        }
    }

    private void filterByDate(ArrayList<FinancialStatement> filteredList, ArrayList<Integer> statementIndex,
                              ArrayList<FinancialStatement> listToFilter, ArrayList<Integer> listToFilterStatementIndex,
                              int i) {
        for (int j = 0; j < listToFilter.size(); j += 1) {
            if (listToFilter.get(j).getDate().equals(LocalDate.parse(filterFlagAndField.get(i + 1),
                    DateTimeFormatter.ofPattern("dd/MM/uuuu")))) {
                filteredList.add(listToFilter.get(j));
                statementIndex.add(listToFilterStatementIndex.get(j));
            }
        }
    }

    private void filterByDateFirstFlag(ArrayList<FinancialStatement> filteredList, ArrayList<Integer> statementIndex,
                                       int i) {
        for (int j = 0; j < userData.getStatementCount(); j += 1) {
            if (userData.getStatementDate(j) != null &&
                    userData.getStatementDate(j).equals(
                            LocalDate.parse(filterFlagAndField.get(i + 1),
                                    DateTimeFormatter.ofPattern("dd/MM/uuuu")))) {
                filteredList.add(userData.getStatement(j));
                statementIndex.add(j + 1);
            }
        }
    }

    private void filterByCategory(ArrayList<FinancialStatement> filteredList, ArrayList<Integer> statementIndex,
                                  ArrayList<FinancialStatement> listToFilter,
                                  ArrayList<Integer> listToFilterStatementIndex, int i) {
        for (int j = 0; j < listToFilter.size(); j += 1) {
            if (listToFilter.get(j).getCategory().contains(filterFlagAndField.get(i + 1))) {
                filteredList.add(listToFilter.get(j));
                statementIndex.add(listToFilterStatementIndex.get(j));
            }
        }
    }

    private void filterByCategoryFirstFlag(ArrayList<FinancialStatement> filteredList,
                                           ArrayList<Integer> statementIndex, int i) {
        for (int j = 0; j < userData.getStatementCount(); j += 1) {
            if (userData.getStatement(j).getCategory().contains(filterFlagAndField.get(i + 1))) {
                filteredList.add(userData.getStatement(j));
                statementIndex.add(j + 1);
            }
        }
    }

    private void filterByDescriptionFirstFlag(ArrayList<FinancialStatement> filteredList,
                                              ArrayList<Integer> statementIndex, int i) {
        for (int j = 0; j < userData.getStatementCount(); j += 1) {
            if (userData.getStatement(j).getDescription().contains(filterFlagAndField.get(i + 1))) {
                filteredList.add(userData.getStatement(j));
                statementIndex.add(j + 1);
            }
        }
    }
}
