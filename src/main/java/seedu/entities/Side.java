package seedu.entities;

public class Side extends Food {
    public Side(int id, String name, String storeName, int storeNumber, float calories, float protein, float totalFat,
                float saturatedFat, float dietaryFibre, float carbohydrates, float sugar, float sodium) {
        super(id, name, storeName, storeNumber, calories, protein, totalFat, saturatedFat, dietaryFibre,
                carbohydrates, sugar, sodium);
    }
}
