package chching.parser;

import chching.ChChingException;
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
        String targetValueString;
        try {
            targetValueString = argumentsByField.get("v");
            Double targetValue = Double.valueOf(argumentsByField.get("v"));
            targ = new Target(targetValue);
        } catch (Exception e) {
            throw new ChChingException("Trouble adding target value");
        }
        if(!DecimalsChecker.isTwoDecimals(targetValueString)) {
            throw new ChChingException("Target value must be a valid double that is 2 d.p. or less");
        } else if (targ.getValue() > 9999999.99) {
            throw new ChChingException("target value must be less than 10 000 000");
        } else if(targ.getValue() < -9999999.99) {
            throw new ChChingException("target value must be greater than -10 000 000");
        }
        return targ;
    }
}
