package seedu.rainyDay;

import seedu.rainyDay.data.FinancialStatement;

import java.util.ArrayList;
import java.util.Scanner;

public class rainyDay {
    public static void main(String[] args) {
        System.out.println("Hello from rainyDay\n");
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
    public static String generateReport(ArrayList<FinancialStatement> financialReport) {
        if (financialReport.size() == 0) {
            return "Your financial report is empty";
        }
        int totalInflow = 0;
        int totalOutflow = 0;
        String financialStatements = "";
        for (int i = 0; i < financialReport.size(); i += 1) {
            if (financialReport.get(i).getFlowDirection().equals("in")) {
                totalInflow += financialReport.get(i).getValue();
            } else {
                totalOutflow += financialReport.get(i).getValue();
            }
            int index = i + 1;
            String financialStatement = String.join("", String.valueOf(index), ". ",
                    financialReport.get(i).getFullStatement(), System.lineSeparator());
            financialStatements = String.join("", financialStatements, financialStatement);
        }
        String inflowInformation = "Inflow: " + totalInflow;
        String outflowInformation = "Outflow: " + totalOutflow;
        String remainingValueInformation = "Remaining value: " + (totalInflow - totalOutflow);
        String report = String.join(System.lineSeparator(), financialStatements, inflowInformation, outflowInformation,
                remainingValueInformation);
        return report;
    }
}
