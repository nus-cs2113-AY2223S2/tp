package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        boolean hasInOutFlag = false;
        if (filterFlagAndField.get(0).equalsIgnoreCase("-in") ||
                filterFlagAndField.get(0).equalsIgnoreCase("-out")) {
            hasInOutFlag = true;
            filterFlagAndField.add(filterFlagAndField.get(0));
            filterFlagAndField.remove(0);
        }

        boolean hastwoDates = false;
        if (filterFlagAndField.contains("-date")) {
            int sizeOfFilterFlagAndField = filterFlagAndField.size();
            String possibleDate1 = filterFlagAndField.get(sizeOfFilterFlagAndField - 2);
            String possibleDate2 = "";
            if (hasInOutFlag == true) {
                possibleDate2 = filterFlagAndField.get(sizeOfFilterFlagAndField - 3);
            } else {
                possibleDate2 = filterFlagAndField.get(sizeOfFilterFlagAndField - 1);
            }

            Pattern pattern = Pattern.compile("(\\d{2}\\/\\d{2}\\/\\d{4})");
            Matcher matcher = pattern.matcher(possibleDate1);
            Matcher matcherTwo = pattern.matcher(possibleDate2);

            if (matcher.find() && matcherTwo.find()) {
                hastwoDates = true;
            }
        }

        boolean filteredTwoDates = false;
        boolean isFirstFlag = true;
        for (int i = 0; i < filterFlagAndField.size(); i += 2) {
            if (filteredTwoDates == true) {
                if (i != filterFlagAndField.size() - 1) {
                    i -= 1;
                    continue;
                }
            }

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
                if (hastwoDates == false) {
                    if (isFirstFlag) {
                        isFirstFlag = false;
                        filterByDateFirstFlag(filteredList, statementIndex, i);
                    } else {
                        filterByDate(filteredList, statementIndex, listToFilter, listToFilterStatementIndex, i);
                    }
                } else {
                    if (isFirstFlag) {
                        isFirstFlag = false;
                        filterByTwoDatesFirstFlag(filteredList, statementIndex, i);
                    } else {
                        filterByTwoDates(filteredList, statementIndex, listToFilter, listToFilterStatementIndex, i);
                    }
                    filteredTwoDates = true;
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
        for (int j = 0; j < savedData.getStatementCount(); j += 1) {
            if (savedData.getStatement(j).getFlowDirectionWord().equals("out")) {
                filteredList.add(savedData.getStatement(j));
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
        for (int j = 0; j < savedData.getStatementCount(); j += 1) {
            if (savedData.getStatement(j).getFlowDirectionWord().equals("in")) {
                filteredList.add(savedData.getStatement(j));
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
        for (int j = 0; j < savedData.getStatementCount(); j += 1) {
            if (savedData.getStatementDate(j) != null &&
                    savedData.getStatementDate(j).equals(
                            LocalDate.parse(filterFlagAndField.get(i + 1),
                                    DateTimeFormatter.ofPattern("dd/MM/uuuu")))) {
                filteredList.add(savedData.getStatement(j));
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
        for (int j = 0; j < savedData.getStatementCount(); j += 1) {
            if (savedData.getStatement(j).getCategory().contains(filterFlagAndField.get(i + 1))) {
                filteredList.add(savedData.getStatement(j));
                statementIndex.add(j + 1);
            }
        }
    }

    private void filterByDescriptionFirstFlag(ArrayList<FinancialStatement> filteredList,
                                              ArrayList<Integer> statementIndex, int i) {
        for (int j = 0; j < savedData.getStatementCount(); j += 1) {
            if (savedData.getStatement(j).getDescription().contains(filterFlagAndField.get(i + 1))) {
                filteredList.add(savedData.getStatement(j));
                statementIndex.add(j + 1);
            }
        }
    }

    private void filterByTwoDatesFirstFlag(ArrayList<FinancialStatement> filteredList,
                                           ArrayList<Integer> statementIndex, int i) {
        LocalDate lowerLimit = LocalDate.parse(filterFlagAndField.get(i + 1),
                DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        LocalDate upperLimit = LocalDate.parse(filterFlagAndField.get(i + 2),
                DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        for (int j = 0; j < savedData.getStatementCount(); j += 1) {
            LocalDate statementDate = savedData.getStatement(j).getDate();
            if (!statementDate.isBefore(lowerLimit) && !statementDate.isAfter(upperLimit)) {
                filteredList.add(savedData.getStatement(j));
                statementIndex.add(j + 1);
            }
        }
    }

    private void filterByTwoDates(ArrayList<FinancialStatement> filteredList, ArrayList<Integer> statementIndex,
                                  ArrayList<FinancialStatement> listToFilter,
                                  ArrayList<Integer> listToFilterStatementIndex, int i) {
        LocalDate lowerLimit = LocalDate.parse(filterFlagAndField.get(i + 1),
                DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        LocalDate upperLimit = LocalDate.parse(filterFlagAndField.get(i + 2),
                DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        for (int j = 0; j < listToFilter.size(); j += 1) {
            LocalDate statementDate = listToFilter.get(j).getDate();
            if (!statementDate.isBefore(lowerLimit) && !statementDate.isAfter(upperLimit)) {
                filteredList.add(listToFilter.get(j));
                statementIndex.add(listToFilterStatementIndex.get(j));
            }
        }
    }
}
