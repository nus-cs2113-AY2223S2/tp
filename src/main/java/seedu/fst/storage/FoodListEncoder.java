package seedu.fst.storage;


import java.util.ArrayList;

import seedu.fst.food.FoodList;
import seedu.fst.food.Food;

//@@author ZhongXiangWong
/**
 * Encodes the {@code FoodList} object into a data file for storage.
 * The code is adapted from
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookEncoder.java
 */
public class FoodListEncoder {

    private static final String SEPARATOR = "|";
    private static final String NAME_IDENTIFIER = "n  ";
    private static final String EXPIRY_DATE_IDENTIFIER = "e  ";
    private static final String QUANTITY_IDENTIFIER = "q  ";
    private static final String UNIT_IDENTIFIER = "u  ";
    private static final String CATEGORY_IDENTIFIER = "c  ";

    /**
     * Encodes all the <code>Food</code> in the <code>toSave</code> into a list of decodable and readable string
     * representation for storage.
     * Changed return type from List to ArrayList
     *
     * @param toSave the foodList object that we want to encode the data of
     */
    public static ArrayList<String> encodeFoodList(FoodList toSave) {
        final ArrayList<String> encodedFoodLists = new ArrayList<>();
        toSave.getFoodList().forEach(food -> encodedFoodLists.add(encodeFoodToString(food)));
        return encodedFoodLists;
    }

    /**
     * Encodes the attributes of <code>Food</code> into a decodable and readable string representation. All attributes
     * will be encoded (missing data / non-initialised data will be initialised as either 0 or NULL).
     *
     * @param food the food object that contains attributes that are associated to it
     */
    private static String encodeFoodToString(Food food) {
        final StringBuilder encodedFoodBuilder = new StringBuilder();

        encodedFoodBuilder.append(SEPARATOR + NAME_IDENTIFIER).append(food.getName());
        encodedFoodBuilder.append(SEPARATOR + EXPIRY_DATE_IDENTIFIER).append(food.getExpiryDate());
        encodedFoodBuilder.append(SEPARATOR + QUANTITY_IDENTIFIER).append(food.getQuantity());
        encodedFoodBuilder.append(SEPARATOR + UNIT_IDENTIFIER).append(food.getUnit());
        encodedFoodBuilder.append(SEPARATOR + CATEGORY_IDENTIFIER).append(food.getCategory());

        return encodedFoodBuilder.toString();
    }
}
