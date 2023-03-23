package chching.parser;

import chching.ChChingException;
import chching.record.Expense;
import chching.record.Target;

import java.util.HashMap;

/**
 * Models a class to parse target.
 */
public class TargetParser {

    /**
     * Parses a target into the TargetStorage
     *
     * @param argumentsByField       Input from users
     */
    public static Target parseTarget(HashMap<String, String> argumentsByField) throws ChChingException {
        Target targ = null;
        try {
            Double targValue = Double.valueOf(argumentsByField.get("v"));
            targ = new Target(targValue);
        } catch (Exception e) {
            throw new ChChingException("Trouble adding target value");
        }
        return targ;
    }
}
