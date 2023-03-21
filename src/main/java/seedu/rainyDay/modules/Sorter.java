package seedu.rainyDay.modules;

import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Sorter {

    public static void sortByValue(FinancialReport statements, ArrayList<Integer> originalIndexes) {
        Map<Double, Integer> sortedIndexes = new TreeMap<Double, Integer>();
        for(Integer index : originalIndexes) {
            double value = statements.getStatementValue(index);
            sortedIndexes.put(value, index);
        }
        for(Map.Entry<Double, Integer> currentEntry : sortedIndexes.entrySet()) {
            //call the printing in UI?
        }
    }
}
