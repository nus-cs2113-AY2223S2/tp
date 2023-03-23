package seedu.entities;

public abstract class Food {
    protected int id;
    protected String name;
    protected String storeName;
    protected int storeNumber;
    protected float calories;
    protected float protein;
    protected float totalFat;
    protected float saturatedFat;
    protected float dietaryFibre;
    protected float carbohydrates;
    protected float sugar;
    protected float sodium;

    public Food(int id, String name, String storeName, int storeNumber, float calories, float protein, float totalFat,
                float saturatedFat, float dietaryFibre, float carbohydrates, float sugar, float sodium) {
        this.id = id;
        this.name = name;
        this.storeName = storeName;
        this.storeNumber = storeNumber;
        this.calories = calories;
        this.protein = protein;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedFat;
        this.dietaryFibre = dietaryFibre;
        this.carbohydrates = carbohydrates;
        this.sugar = sugar;
        this.sodium = sodium;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreNumber() {
        return this.storeNumber;
    }

    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
    }

    public float getCalories() {
        return this.calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(float totalFat) {
        this.totalFat = totalFat;
    }

    public float getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(float saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public float getDietaryFibre() {
        return dietaryFibre;
    }

    public void setDietaryFibre(float dietaryFibre) {
        this.dietaryFibre = dietaryFibre;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public float getSugar() {
        return sugar;
    }

    public void setSugar(float sugar) {
        this.sugar = sugar;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    @Override
    public String toString() {
        return this.name + " from " + this.storeName;
    }
    
}
