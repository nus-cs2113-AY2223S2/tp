package seedu.fst.storage;


import seedu.fst.exceptions.FSTException;
import seedu.fst.exceptions.EmptyStorageFileException;
import seedu.fst.exceptions.ReadStorageFileErrorException;
import seedu.fst.food.Food;
import seedu.fst.food.FoodCategory;
import seedu.fst.food.FoodList;
import seedu.fst.food.Unit;
import seedu.fst.general.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

//@@author ZhongXiangWong
/**
 * Decodes the storage data file into an {@code FoodList} object.
 * The code is adapted from
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookDecoder.java
 */
public class FoodListDecoder {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String SEPARATOR = "\\|";
    private static final char NAME_IDENTIFIER = 'n';
    private static final char EXPIRY_DATE_IDENTIFIER = 'e';
    private static final char QUANTITY_IDENTIFIER = 'q';
    private static final char UNIT_IDENTIFIER = 'u';
    private static final char CATEGORY_IDENTIFIER = 'c';
    private static final String MISSING_IDENTIFIER_ERROR = "Error in reading storage file: Missing identifier." ;
    private static final String INVALID_QUANTITY_ERROR = "Quantity in save file is not a valid number.";

    /**
     * Decodes <code>encodedFoodList</code> into an <code>FoodList</code> containing the decoded food. It is
     * able to handle modifications to the encodedFoodList such as missing data and additional data.
     *
     * @param encodedFoodList this is the line of food attribute of the current food
     * @return the foodList that are able to be processed by this method, unprocessable data will be ignored
     */
    public static FoodList decodeFoodList(List<String> encodedFoodList) {
        final ArrayList<Food> decodedFoodList = new ArrayList<>();
        for (String encodedFood : encodedFoodList) {
            try {
                decodedFoodList.add(decodeFoodFromString(encodedFood));
            } catch (FSTException e) {
                Ui.showError(e.getMessage());
            }
        }
        return new FoodList(decodedFoodList);
    }


    /**
     * This method decodes the attributes related to a string. It has checks to determine if the attribute is valid or
     * invalid. After all the present attributes are checked, the food object will be returned with valid attributes.
     *
     * @param encodedFood contains the list of attributes to be checked
     * @return a Food object with attributes
     * @throws ReadStorageFileErrorException when there are errors with decoding the attributes
     * @throws EmptyStorageFileException  when the line is empty
     */
    private static Food decodeFoodFromString(String encodedFood) throws
            FSTException {

        if (encodedFood.equals("")) {
            throw new EmptyStorageFileException();
        }
        String[] details = encodedFood.split(SEPARATOR);

        String foodName = "";
        String expiryDate = "";
        String quantity = "0.0";
        String unit = Unit.UNIT.abbreviation;
        String category = String.valueOf(FoodCategory.UNCLASSIFIED_FOOD);


        for (String foodDetails : details) {
            try {
                if (foodDetails.equals("")) {
                    continue;
                }

                char identifier = foodDetails.charAt(0);
                String detail = foodDetails.substring(1).trim();
                switch(identifier) {
                case NAME_IDENTIFIER:
                    if (hasValidFoodName(detail)) {
                        assert !detail.equals("");
                        foodName = detail;
                    }
                    break;
                case EXPIRY_DATE_IDENTIFIER:
                    if (hasValidExpiryDate(detail)) {
                        expiryDate = detail;
                    }
                    break;
                case QUANTITY_IDENTIFIER:
                    if(hasValidQuantity(detail)) {
                        quantity = detail;
                    }
                    break;
                case UNIT_IDENTIFIER:
                    if (hasValidUnit(detail)) {
                        unit = detail;
                    }
                    break;
                case CATEGORY_IDENTIFIER:
                    if(hasValidCategory(detail)) {
                        category = detail;
                    }
                    break;
                default:
                    break;
                }
            } catch (StringIndexOutOfBoundsException e) {
                Ui.showError(MISSING_IDENTIFIER_ERROR);
            }
        }
        try {
            return new Food(foodName, expiryDate, Double.valueOf(quantity), unit, category);
        } catch (NumberFormatException e) {
            Ui.showError(INVALID_QUANTITY_ERROR);
        }
        return new Food(foodName, expiryDate, category);
    }

    /**
     * This method checks whether the encoded <code>foodName</code> is valid.
     *
     * @param detail is the encoded foodName
     * @return true if valid
     * @throws ReadStorageFileErrorException when not valid
     */
    private static boolean hasValidFoodName(String detail) throws ReadStorageFileErrorException {
        if (detail.equals("")) {
            throw new ReadStorageFileErrorException("food name: " + detail);
        }
        return true;
    }

    /**
     * This method checks whether the encoded <code>expiryDate</code> is valid.
     *
     * @param detail is the encoded expiryDate
     * @return true if valid
     * @throws ReadStorageFileErrorException when not valid
     */
    private static boolean hasValidExpiryDate(String detail) throws ReadStorageFileErrorException {
        try {
            LocalDate.parse(detail, formatter);
        } catch (DateTimeParseException e) {
            throw new ReadStorageFileErrorException("expiry date: " + detail);
        }
        if (isInvalidDate(detail)) {
            throw new ReadStorageFileErrorException("expiry date: " + detail);
        }
        return true;
    }

    /**
     * This method checks whether the encoded <code>quantity</code> is valid.
     *
     * @param detail is the encoded quantity
     * @return true if valid
     * @throws ReadStorageFileErrorException when not valid
     * @throws NumberFormatException when detail cannot be interpreted as a number
     */
    private static boolean hasValidQuantity(String detail) throws ReadStorageFileErrorException {
        try {
            double value = Double.parseDouble(detail);
            if (value < 0 || value > 9999) {
                throw new ReadStorageFileErrorException("food quantity:" + detail);
            }
        } catch (NumberFormatException e) {
            Ui.showError(INVALID_QUANTITY_ERROR);
            return false;
        }
        return true;
    }

    /**
     * This method checks whether the encoded <code>category</code> is valid.
     *
     * @param detail is the encoded category
     * @return true if valid; false otherwise
     */
    private static boolean hasValidCategory(String detail) {
        for (FoodCategory category : FoodCategory.values()) {
            if (category.name().equals(detail)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks whether the encoded <code>unit</code> is valid.
     *
     * @param detail is the encoded unit
     * @return true if valid; false otherwise
     */
    private static boolean hasValidUnit(String detail) {
        for (Unit unit : Unit.values()) {
            if (unit.abbreviation.equals(detail)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks whether date is valid, based on the concept of leap year.
     *
     * @param date is the date to be checked
     * @return true if valid; false if otherwise
     */
    public static boolean isInvalidDate(String date) {
        String[] splitString = date.split("/", 3);
        String d = splitString[0];
        String m = splitString[1];
        String y = splitString[2];

        if (Integer.parseInt(y) % 4 != 0 && m.equals("02")) {
            return d.equals("29");
        }
        return false;
    }

}
