package chching.parser;

import chching.record.Income;

import java.util.HashMap;

public class Incomes {
    public static Income parseIncome(HashMap<String, String> argumentsByField) {
        Income inc = null;
        try {
            String incomeDescription = argumentsByField.get("de");
            String incomeDate = argumentsByField.get("da");
            float incomeValue = Float.parseFloat(argumentsByField.get("v"));
            inc = new Income(incomeDescription, incomeDate, incomeValue);
            assert incomeValue > 0 : "incomeValue has to be more than 0";
            inc = new Income(incomeDescription, incomeDate, incomeValue);
        } catch (Exception e) {
            System.out.println("Trouble adding income");
        }
        return inc;
    }
}
