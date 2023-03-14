package chching.parser;

import chching.record.IncomeList;

import java.util.HashMap;

public class Incomes {
    public static chching.record.Income parseIncome(HashMap<String, String> argumentsByField) {
        chching.record.Income inc = null;
        try {
            String incomeCategory = argumentsByField.get("c");
            String incomeDescription = argumentsByField.get("de");
            String incomeDate = argumentsByField.get("da");
            float incomeValue = Float.parseFloat(argumentsByField.get("v"));
            assert incomeValue > 0 : "incomeValue has to be more than 0";
            inc = new chching.record.Income(incomeCategory, incomeDescription, incomeDate, incomeValue);
        } catch (Exception e) {
            System.out.println("Trouble adding income");
        }
        return inc;
    }

    public static void editIncome(HashMap<String, String> argumentsByField, IncomeList incomeList) {
        try {
            int index = Integer.parseInt(argumentsByField.get("i"));
            assert index >= 0 : "index should be greater than or equal to zero";
            String updateCategory = null;
            String updateDescription = null;
            String updateDate = null;
            float updateValue = 0;
            if (argumentsByField.containsKey("c")) {
                updateCategory = argumentsByField.get("c");
            }
            if (argumentsByField.containsKey("de")) {
                updateDescription = argumentsByField.get("de");
            }
            if (argumentsByField.containsKey("da")) {
                updateDate = argumentsByField.get("da");
            }
            if (argumentsByField.containsKey("v")) {
                updateValue = Float.parseFloat(argumentsByField.get("v"));
                assert updateValue > 0 : "updateValue has to be more than 0";
            }
            incomeList.editIncome(index, updateCategory, updateDescription, updateDate, updateValue);
            System.out.println("Income modified");
        } catch (Exception e) {
            System.out.println("Trouble editing income");
        }
    }
}
