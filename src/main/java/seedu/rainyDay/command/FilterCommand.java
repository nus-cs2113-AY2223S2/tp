package seedu.rainyDay.command;


import seedu.rainyDay.data.FinancialStatement;

import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FilterCommand extends Command implements FormatReport {
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

    // todo
    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting FilterCommand.execute()");
        ArrayList<FinancialStatement> filteredList = new ArrayList<>();


        if (filterFlag.equalsIgnoreCase("-d")) {
            filteredList = (ArrayList<FinancialStatement>) financialReport
                    .getFinancialReport().stream()
                    .filter(t -> t.getDescription().contains(this.description))
                    .collect(Collectors.toList());
        } else if (filterFlag.equalsIgnoreCase("-c")) {
            filteredList = (ArrayList<FinancialStatement>) financialReport
                    .getFinancialReport().stream()
                    .filter(t -> t.getCategory().contains(this.description))
                    .collect(Collectors.toList());
        } else if (filterFlag.equalsIgnoreCase("-in")) {
            filteredList = (ArrayList<FinancialStatement>) financialReport
                    .getFinancialReport().stream()
                    .filter(t -> t.getFlowDirection().equals("in"))
                    .collect(Collectors.toList());
        } else if (filterFlag.equalsIgnoreCase("-out")) {
            filteredList = (ArrayList<FinancialStatement>) financialReport
                    .getFinancialReport().stream()
                    .filter(t -> t.getFlowDirection().equals("out"))
                    .collect(Collectors.toList());
        }


        String outcome = "";

        if (filteredList.size() == 0) {
            outcome = "We could not find any matches for your description in your report";
        } else {
            outcome = ""; // todo
            for (int i = 0; i < filteredList.size(); i += 1) {
                logger.log(Level.INFO, "starting statement " + i);
                FinancialStatement currentStatement = filteredList.get(i);

                outcome += FormatReport.formatFinancialStatement(i + 1, currentStatement);
                logger.log(Level.INFO, "passed statement " + i);
            }
        }

        logger.log(Level.INFO, " end of FilterCommand.execute()");

        return new CommandResult(outcome);
    }
}
