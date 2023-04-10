package chching.parser;

import chching.ChChing;
import chching.ChChingException;
import chching.record.Target;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Models a class to parse target.
 */
public class TargetParser {
    private static final Logger logger = Logger.getLogger(ChChing.class.getName());

    static {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            new File("data/TargetParserLog.log").createNewFile();
            FileHandler fileHandler = new FileHandler("data/TargetParserLog.log");
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * Parses a target into the TargetStorage
     *
     * @param argumentsByField Input from users
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
        if (!DecimalsChecker.isTwoDecimals(targetValueString)) {
            throw new ChChingException("Target value must be a valid double that is 2 d.p. or less");
        } else if (targ.getValue() > 9999999.99) {
            throw new ChChingException("target value must be less than 10 000 000");
        } else if (targ.getValue() < -9999999.99) {
            throw new ChChingException("target value must be greater than -10 000 000");
        }
        return targ;
    }
}
