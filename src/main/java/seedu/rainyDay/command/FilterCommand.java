package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class FilterCommand extends Command{
    private static final Logger logger = Logger.getLogger(FilterCommand.class.getName());
    private final String description;
    private final String filterFlag;

    public FilterCommand(String description, String filterFlag) {
        this.description = description;
        this.filterFlag = filterFlag;
    }

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

    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting FilterCommand.execute()");
        ArrayList<FinancialStatement> filteredList = new ArrayList<>();
        ArrayList<Integer> statementIndex = new ArrayList<>();

        if (filterFlag.equalsIgnoreCase("-d")) {
            for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
                if (financialReport.getFinancialStatement(i).getDescription().contains(this.description)) {
                    filteredList.add(financialReport.getFinancialStatement(i));
                    statementIndex.add(i + 1);
                }
            }
        } else if (filterFlag.equalsIgnoreCase("-c")) {
            for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
                if (financialReport.getFinancialStatement(i).getCategory().contains(this.description)) {
                    filteredList.add(financialReport.getFinancialStatement(i));
                    statementIndex.add(i + 1);
                }
            }
        } else if (filterFlag.equalsIgnoreCase("-in")) {
            for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
                if (financialReport.getFinancialStatement(i).getFlowDirectionWord().equals("in")) {
                    filteredList.add(financialReport.getFinancialStatement(i));
                    statementIndex.add(i + 1);
                }
            }
        } else if (filterFlag.equalsIgnoreCase("-out")) {
            for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
                if (financialReport.getFinancialStatement(i).getFlowDirectionWord().equals("out")) {
                    filteredList.add(financialReport.getFinancialStatement(i));
                    statementIndex.add(i + 1);
                }
            }
        } else if (filterFlag.equalsIgnoreCase("-date")) {
            for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
                if (financialReport.getStatementDate(i) != null &&
                        financialReport.getStatementDate(i).equals(
                                LocalDate.parse(this.description, DateTimeFormatter.ofPattern("dd/MM/uuuu")))) {
                    filteredList.add(financialReport.getFinancialStatement(i));
                    statementIndex.add(i + 1);
                }
            }
        }

        String output;
        if (filteredList.size() == 0) {
            output = "We could not find any matches for your description in your report";
            return new CommandResult(output);
        }
        output = "Here are the list of matching items!"; // todo
        CommandResult result = new CommandResult(output);
        ViewResult.printItemsInList(statementIndex);
        return result;
    }
}
