package chching.parser;

import chching.record.IncomeList;

import java.util.HashMap;

public class Incomes {
    public static chching.record.Income parseIncome(HashMap<String, String> argumentsByField) {
        chching.record.Income inc = null;
        try {
            String incomeDescription = argumentsByField.get("de");
            String incomeDate = argumentsByField.get("da");
            float incomeValue = Float.parseFloat(argumentsByField.get("v"));
            inc = new chching.record.Income(incomeDescription, incomeDate, incomeValue);
        } catch (Exception e) {
            System.out.println("Trouble adding income");
        }
        return inc;
    }
}
