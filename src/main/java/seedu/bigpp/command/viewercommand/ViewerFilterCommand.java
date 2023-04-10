package seedu.bigpp.command.viewercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.pc.PCList;
import seedu.bigpp.ui.UI;
import seedu.bigpp.ui.UIState;

import java.util.Arrays;

public class ViewerFilterCommand extends Command {

    private static final String NAME_FLAG = "-name";
    private static final String PRICE_FLAG = "-price";
    private static final String BUILT_FLAG = "-built";
    private static final String CLEAR_FLAG = "-clear";

    public ViewerFilterCommand(String arguments) {
        setArguments(arguments);
    }

    /**
     * Checks for the filter flags and applies it to the PC Viewer menu user
     * interface
     * @return that the filter has been executed successfully
     */
    @Override
    public String executeCommand(DataStorage dataStorage) throws PPException {

        assert UI.getUiState() == UIState.PCVIEWER : "UI state should be PCVIEWER";

        String userInputString = getArguments();
        userInputString = userInputString.toLowerCase();

        String[] userInputStringArray = userInputString.split(" ");
        if (userInputString.equals("")) {
            throw new PPException("Please enter a valid flag");
        }
        if (containsFlag(userInputStringArray, CLEAR_FLAG)) {
            handleClearFlag(userInputStringArray);
            return "Filter cleared";
        }
        if (userInputStringArray.length <= 1) {
            if (hasFlag(userInputStringArray)) {
                if (containsFlag(userInputStringArray, NAME_FLAG)) {
                    handleNameFlag(userInputStringArray);
                }
                if (containsFlag(userInputStringArray, BUILT_FLAG)) {
                    handleBuiltFlag(userInputStringArray);
                }
                if (containsFlag(userInputStringArray, PRICE_FLAG)) {
                    handlePriceFlag(userInputString, userInputStringArray);
                }
                return "";
            } else {
                throw new PPException("Please enter a valid flag and description");
            }
        }
        if (userInputStringArray.length > 1) {
            if (hasFlag(userInputStringArray)) {
                if (containsFlag(userInputStringArray, NAME_FLAG)) {
                    handleNameFlag(userInputStringArray);
                }
                if (containsFlag(userInputStringArray, BUILT_FLAG)) {
                    handleBuiltFlag(userInputStringArray);
                }
                if (containsFlag(userInputStringArray, PRICE_FLAG)) {
                    handlePriceFlag(userInputString, userInputStringArray);
                }
                PCList.setFilterTrue();
            } else {
                throw new PPException("Please enter a valid flag");
            }
            return "Filter completed";
        }
        return "";
    }

    private void handleClearFlag(String[] flagAndDescriptionArray) throws PPException {
        PCList.setFilterFalse();
        PCList.setBuilt("");
        PCList.setPriceFrom("");
        PCList.setName("");
        PCList.setPriceTo("");
    }

    private void handlePriceFlag(String userInputString, String[] flagAndDescriptionArray) throws PPException {
        int priceFlagIndex = indexOfFlag(flagAndDescriptionArray, PRICE_FLAG);
        if (priceFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a price description after the flag");
        }
        String flagPriceDescription = userInputString.split(PRICE_FLAG)[1].trim();
        if (flagPriceDescription.split(" ").length < 4) {
            throw new PPException(
                    "Please enter the full price description after the flag containing the start "
                            + "and end price range");
        }
        String[] flagPriceDescriptionArray = Arrays.copyOfRange(flagPriceDescription.split(" "), 0, 4);
        if (hasFlag(flagPriceDescriptionArray)) {
            throw new PPException(
                    "Flag detected in price description. Please enter a different price"
                            + " description after the flag");
        }
        String fromFlag = flagPriceDescriptionArray[0].trim();
        if (!fromFlag.equals("/from")) {
            throw new PPException("Please use /from to specify the start price range");
        }
        String priceFrom = flagPriceDescriptionArray[1].trim();
        if (priceFrom.matches(".*\\D.*")) {
            throw new PPException("Start price must be a positive integer");
        }
        String toFlag = flagPriceDescriptionArray[2].trim();
        if (!toFlag.equals("/to")) {
            throw new PPException("Please use /to to specify the end price range");
        }
        String priceTo = flagPriceDescriptionArray[3].trim();
        if (priceTo.matches(".*\\D.*")) {
            throw new PPException("End price must be a positive integer");
        }

        int priceFromInt = 0;
        int priceToInt = 0;

        try {
            priceFromInt = Integer.parseInt(priceFrom);
            priceToInt = Integer.parseInt(priceTo);
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a postive integer within 16 bits");
        }
        if (priceFromInt > priceToInt) {
            throw new PPException("Start price must be less than end price");
        }
        if (priceFromInt < 0 || priceToInt < 0) {
            throw new PPException("Price must be greater than 0");
        }
        if (priceFromInt > 1000000 || priceToInt > 1000000) {
            throw new PPException("price must be smaller than 1000000");
        }
        PCList.setPriceFrom(priceFrom);
        PCList.setPriceTo(priceTo);
    }

    private void handleBuiltFlag(String[] flagAndDescriptionArray) throws PPException {
        int builtFlagIndex = indexOfFlag(flagAndDescriptionArray, BUILT_FLAG);
        if (builtFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a built description after the flag");
        }
        String built = flagAndDescriptionArray[builtFlagIndex + 1].trim().toLowerCase();
        if (isFlag(built)) {
            throw new PPException("Flag detected as a built. Please enter another built after the flag");
        }
        if (built.equals("complete") || built.equals("incomplete")) {
            PCList.setBuilt(built);
        } else {
            throw new PPException("built description is incorrect, please enter either complete or incomplete");
        }
    }

    private void handleNameFlag(String[] flagAndDescriptionArray) throws PPException {
        int nameFlagIndex = indexOfFlag(flagAndDescriptionArray, NAME_FLAG);
        if (nameFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a name after the flag");
        }
        String name = flagAndDescriptionArray[nameFlagIndex + 1].trim().toLowerCase();
        if (isFlag(name)) {
            throw new PPException("Flag detected as a name. Please enter another name after the flag");
        }
        PCList.setName(name);
    }

    private static boolean isFlag(String flag) {
        return flag.equals(NAME_FLAG) || flag.equals(PRICE_FLAG) || flag.equals(BUILT_FLAG) || flag.equals(CLEAR_FLAG);
    }

    private static boolean hasFlag(String[] userInputStringArray) {
        for (String flag : userInputStringArray) {
            if (isFlag(flag)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsFlag(String[] flagAndDescriptionArray, String flag) {
        for (String flagAndDescription : flagAndDescriptionArray) {
            if (flagAndDescription.equals(flag)) {
                return true;
            }
        }
        return false;
    }

    private static int indexOfFlag(String[] flagAndDescriptionArray, String flag) {
        for (int i = 0; i < flagAndDescriptionArray.length; i++) {
            if (flagAndDescriptionArray[i].equals(flag)) {
                return i;
            }
        }
        return -1;
    }
}
