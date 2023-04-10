package seedu.fst.commands;

import seedu.fst.exceptions.FSTException;
import seedu.fst.food.Food;
import seedu.fst.food.FoodCategory;
import seedu.fst.food.FoodList;
import seedu.fst.food.Unit;
import seedu.fst.utils.DateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//@@author tsx0314
/**
 * Represent an Add command
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String ADD_MESSAGE = "I have added this product! :)";

    private static final String NAME_SEPARATOR = "--n";
    private static final String EXPIRY_SEPARATOR = "--e";
    private static final String CATEGORY_SEPARATOR = "--c";
    private static final String QUANTITY_SEPARATOR = "--q";
    private static final String UNIT_SEPARATOR = "--u";

    private static final String INVALID_DATE_MESSAGE =
            "Please input a valid date :<";
    private static final String EXPIRY_DATE_MESSAGE =
            "Please do not add an expired product :<";
    private static final String INVALID_INPUT_MESSAGE = "The number you entered exceeds 9999. " +
            "Please use reasonable value :<";

    private static final String MILLIGRAM_1 = "mg";
    private static final String MILLIGRAM_2 = "milligram";
    private static final String MILLIGRAM_3 = "milligrams";
    private static final String GRAM_1 = "gram";
    private static final String GRAM_2 = "g";
    private static final String GRAM_3 = "grams";
    private static final String KILOGRAM_1 = "kg";
    private static final String KILOGRAM_2 = "kilogram";
    private static final String KILOGRAM_3 = "kilograms";
    private static final String MILLIMETRE_1 = "ml";
    private static final String MILLIMETRE_2 = "millilitre";
    private static final String MILLIMETRE_3 = "millilitres";
    private static final String LITRE_1 = "l";
    private static final String LITRE_2 = "litre";
    private static final String LITRE_3 = "litres";
    private static final String SERVING_1 = "serving";
    private static final String SERVING_2 = "servings";
    private static final String BOX_1 = "box";
    private static final String BOX_2 = "boxes";
    private static final String PACKET_1 = "packet";
    private static final String PACKET_2 = "packets";
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public String details;

    /**
     * Constructor
     *
     * @param details food details
     */
    public AddCommand(String details) {
        this.details = details;
    }

    /**
     * Returns a CommandResult object to display the successful message after executing the command as below
     * Separate the food details and add a new food item in the list according to the size of the String array
     * Input validity is checked for all parameters to ensure that only reasonable food items can be added to the list
     *
     * @param foodList a food list
     * @return a CommandResult object to display the successful message
     */
    public CommandResult execute(FoodList foodList) throws FSTException {
        boolean hasQuantity = details.contains(QUANTITY_SEPARATOR);
        boolean hasCategory = details.contains(CATEGORY_SEPARATOR);
        boolean hasUnit = details.contains(UNIT_SEPARATOR);
        String[] foodDetails = splitDetails(details);
        assert foodDetails.length >= 2 : "Input is wrong";
        String name = foodDetails[0];
        String date = foodDetails[1];

        try {
            LocalDate expiryDate = DateFormatter.parse(date);
            boolean isValid = isValid(date);
            boolean isNew = isTheDateAfterCurrentDate(expiryDate);
            if (!isValid) {
                return new CommandResult(INVALID_DATE_MESSAGE);
            }
            if (!isNew) {
                return new CommandResult(EXPIRY_DATE_MESSAGE);
            }
            assert !name.trim().isEmpty() : "Expected non-empty string for name";
            assert !date.trim().isEmpty() : "Expected non-empty string for date";

            Food newFood;

            assert foodDetails.length == 2 || foodDetails.length == 3 ||
                    foodDetails.length == 4 || foodDetails.length == 5 : "Wrong food details size";


            if (foodDetails.length == 2) {
                newFood = new Food(name, date);
            } else if (foodDetails.length == 3 && hasCategory && !hasQuantity) {
                String c = foodDetails[2];
                FoodCategory category = compareCategory(c);
                newFood = new Food(name, date, category);
            } else if (foodDetails.length == 3 && !hasCategory && hasQuantity) {
                String q = foodDetails[2];
                if (!isNumberReasonable(q)) {
                    return new CommandResult(INVALID_INPUT_MESSAGE);
                }
                assert Double.valueOf(q) > 0 && Double.valueOf(q) <= 9999 : "Unreasonable quantity input";
                newFood = buildFoodWithCategory(name, date, q);
            } else if (foodDetails.length == 4 && hasUnit) {
                String q = foodDetails[2];
                if (!isNumberReasonable(q)) {
                    return new CommandResult(INVALID_INPUT_MESSAGE);
                }
                assert Double.valueOf(q) > 0 && Double.valueOf(q) <= 9999 : "Unreasonable quantity input";
                newFood = buildFoodWithUnit(foodDetails, name, date, q);
            } else if (foodDetails.length == 4 && !hasUnit) {
                String q = foodDetails[2];
                if (!isNumberReasonable(q)) {
                    return new CommandResult(INVALID_INPUT_MESSAGE);
                }
                assert Double.valueOf(q) > 0 && Double.valueOf(q) <= 9999 : "Unreasonable quantity input";
                newFood = buildFoodWithNoUnit(foodDetails, name, date, q);
            } else {
                String q = foodDetails[2];
                if (!isNumberReasonable(q)) {
                    return new CommandResult(INVALID_INPUT_MESSAGE);
                }
                assert Double.valueOf(q) > 0 && Double.valueOf(q) <= 9999 : "Unreasonable quantity input";
                newFood = getFoodWithAllAttributes(foodDetails, name, date, q);
            }
            System.out.println(newFood);
            foodList.addFood(newFood);
            System.out.println();
            return new CommandResult(ADD_MESSAGE);
        } catch (DateTimeParseException e) {
            return new CommandResult(INVALID_DATE_MESSAGE);
        }
    }

    /**
     * Returns a new Food object based on the details given
     * It throws FSTException if any error is caught
     *
     * @param foodDetails a string array of food details
     * @param name the food name
     * @param date the food expiry date
     * @param q the quantity
     *
     * @throws FSTException
     */

    private Food getFoodWithAllAttributes(String[] foodDetails, String name, String date, String q)
            throws FSTException {
        Food newFood;
        Double quantity = Double.valueOf(q);
        String unit = getUnitOfFood(foodDetails[3], quantity);
        String c = foodDetails[4];
        FoodCategory category = compareCategory(c);
        newFood = new Food(name, date, quantity, unit, category);
        return newFood;
    }

    /**
     * Returns a new Food object based on the details given
     * It throws FSTException if any error is caught
     *
     * @param foodDetails a string array of food details
     * @param name the food name
     * @param date the food expiry date
     * @param q the quantity
     * @return newFood a new food object
     * @throws FSTException
     */
    private Food buildFoodWithNoUnit(String[] foodDetails, String name, String date, String q) throws FSTException {
        Food newFood;
        Double quantity = Double.valueOf(q);
        FoodCategory category = compareCategory(foodDetails[3]);
        newFood = new Food(name, date, quantity, category);
        return newFood;
    }

    /**
     * Returns a new Food object based on the details given
     * It throws FSTException if any error is caught
     *
     * @param foodDetails a string array of food details
     * @param name the food name
     * @param date the food expiry date
     * @param q the quantity
     * @return newFood a food object
     * @throws FSTException
     */
    private Food buildFoodWithUnit(String[] foodDetails, String name, String date, String q) throws FSTException {
        Food newFood;
        Double quantity = Double.valueOf(q);
        String unit = getUnitOfFood(foodDetails[3], quantity);
        newFood = new Food(name, date, quantity, unit);
        return newFood;
    }

    /**
     * Returns a new Food object
     * It throws FSTException if any error is caught
     *
     * @param name the food name
     * @param date the food expiry date
     * @param q    the quantity
     * @return newFood a food object
     * @throws FSTException
     */
    private static Food buildFoodWithCategory(String name, String date, String q) throws FSTException {
        Food newFood;
        Double quantity = Double.valueOf(q);
        newFood = new Food(name, date, quantity);
        return newFood;
    }

    //@@author tsx0314

    /**
     * Returns an array of String to store the information of food added
     *
     * @param details food details
     * @return foodDetails a String array of the food details
     */
    public String[] splitDetails(String details) {
        boolean hasQuantity = details.contains(QUANTITY_SEPARATOR);
        boolean hasUnit = details.contains(UNIT_SEPARATOR);
        boolean hasCat = details.contains(CATEGORY_SEPARATOR);

        String name;
        String date;
        String category;
        String quantity;
        String unit;

        String[] temp = details.trim().split(QUANTITY_SEPARATOR);
        String[] temp2 = temp[0].split(CATEGORY_SEPARATOR);
        String[] nameAndExpiryDate = temp2[0].replace(NAME_SEPARATOR, "").trim().split(EXPIRY_SEPARATOR, 2);

        name = nameAndExpiryDate[0].trim();
        date = nameAndExpiryDate[1].trim();

        if (!hasCat && !hasUnit && !hasQuantity) {
            String[] foodDetails = {name, date};
            return foodDetails;
        }

        if (hasCat && !hasUnit && !hasQuantity) {
            category = temp2[1].trim();
            String[] foodDetails = {name, date, category};
            return foodDetails;
        }

        if (!hasCat && hasQuantity && !hasUnit) {
            quantity = temp[1].trim();
            String[] foodDetails = {name, date, quantity};
            return foodDetails;
        }

        if (hasCat && hasQuantity && !hasUnit) {
            quantity = temp[1].trim();
            category = temp2[1].trim();
            String[] foodDetails = {name, date, quantity, category};
            return foodDetails;
        }

        if (!hasCat && hasUnit && hasQuantity) {
            String[] quantityAndUnit = temp[1].trim().split(UNIT_SEPARATOR, 2);
            unit = quantityAndUnit[1].trim();
            quantity = quantityAndUnit[0].trim();
            String[] foodDetails = {name, date, quantity, unit};
            return foodDetails;
        }

        category = temp2[1].trim();
        quantity = temp[1].trim().split(UNIT_SEPARATOR, 2)[0].trim();
        unit = temp[1].trim().split(UNIT_SEPARATOR, 2)[1].trim();
        String[] foodDetails = {name, date, quantity, unit, category};
        return foodDetails;
    }

    /**
     * Returns whether the input date is a valid expiry date
     *
     * @param expiryDate the date
     * @return isValid whether the date is valid
     */
    public boolean isTheDateAfterCurrentDate(LocalDate expiryDate) {
        LocalDate currentDate = LocalDate.now();
        boolean isValid = expiryDate.isAfter(currentDate);
        return isValid;
    }


    /**
     * Returns a boolean value which indicate whether the date is valid
     * This method specially checks for the date 29/02
     * If the date is this special date and the year is not a leap year, it will return false
     * Or else return true, the check is passed.
     *
     * @param date date
     * @return whether it is a valid date
     */
    public boolean isValid(String date) {
        String[] splitString = date.split("/", 3);
        String d = splitString[0];
        String m = splitString[1];
        String y = splitString[2];

        if (Integer.parseInt(y) % 4 != 0 && m.equals("02")) {
            if (d.equals("29")) {
                return false;
            }
        }
        return true;
    }


    /**
     * Return a food category according to the input
     * Any other category is not accepted and will be deemed as OTHERS
     *
     * @param tempCategory a category
     * @return an enum FoodCategory
     */
    public FoodCategory compareCategory(String tempCategory) {

        String cat = tempCategory.toLowerCase();

        switch (cat) {
        case "fruit":
            return FoodCategory.FRUIT;
        case "meat":
            return FoodCategory.MEAT;
        case "vegetable":
            return FoodCategory.VEGETABLE;
        case "dairy":
            return FoodCategory.DAIRY;
        case "grain":
            return FoodCategory.GRAIN;
        case "seafood":
            return FoodCategory.SEAFOOD;
        case "beverage":
            return FoodCategory.BEVERAGE;
        default:
            return FoodCategory.OTHERS;
        }
    }

    /**
     * Return the input number is reasonable
     * Maximum quantity input allowed is 9999
     *
     * @param number a number string
     * @return whether the number is valid
     */
    boolean isNumberReasonable(String number) {
        if (Double.valueOf(number) > 9999 || Double.valueOf(number) < 0) {
            return false;
        }
        return true;
    }

    //@@author wanjuin

    /**
     * Returns the unit of the food.
     *
     * @param unitTemporary    a unit
     * @param quantityInDouble quantity
     * @return unitOfMeasurement a food unit
     */
    public String getUnitOfFood(String unitTemporary, Double quantityInDouble) {
        String unitOfMeasurement;

        switch (unitTemporary.toLowerCase()) {
        case MILLIGRAM_1:
        case MILLIGRAM_2:
        case MILLIGRAM_3:
            unitOfMeasurement = String.valueOf(Unit.MILLIGRAM.abbreviation);
            break;
        case GRAM_1:
        case GRAM_2:
        case GRAM_3:
            unitOfMeasurement = String.valueOf(Unit.GRAM.abbreviation);
            break;
        case KILOGRAM_1:
        case KILOGRAM_2:
        case KILOGRAM_3:
            unitOfMeasurement = String.valueOf(Unit.KILOGRAM.abbreviation);
            break;
        case MILLIMETRE_1:
        case MILLIMETRE_2:
        case MILLIMETRE_3:
            unitOfMeasurement = String.valueOf(Unit.MILLILITER.abbreviation);
            break;
        case LITRE_1:
        case LITRE_2:
        case LITRE_3:
            unitOfMeasurement = String.valueOf(Unit.LITER.abbreviation);
            break;
        case SERVING_1:
        case SERVING_2:
            if (quantityInDouble > 1) {
                unitOfMeasurement = String.valueOf(Unit.SERVINGS.abbreviation);
            } else {
                unitOfMeasurement = String.valueOf(Unit.SERVING.abbreviation);
            }
            break;
        case BOX_1:
        case BOX_2:
            if (quantityInDouble > 1) {
                unitOfMeasurement = String.valueOf(Unit.BOXES.abbreviation);
            } else {
                unitOfMeasurement = String.valueOf(Unit.BOX.abbreviation);
            }
            break;
        case PACKET_1:
        case PACKET_2:
            if (quantityInDouble > 1) {
                unitOfMeasurement = String.valueOf(Unit.PACKETS.abbreviation);
            } else {
                unitOfMeasurement = String.valueOf(Unit.PACKET.abbreviation);
            }
            break;
        default:
            if (quantityInDouble > 1) {
                unitOfMeasurement = String.valueOf(Unit.UNITS.abbreviation);
            } else {
                unitOfMeasurement = String.valueOf(Unit.UNIT.abbreviation);
            }
            break;
        }
        return unitOfMeasurement;
    }
}
