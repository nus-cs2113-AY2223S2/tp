package seedu.Entities;

public abstract class Food {
    protected String name;
    protected String storeName;
    protected int storeNumber;
    protected float calories;

    public Food(String name, String storeName, int storeNumber, float calories) {
        this.name = name;
        this.storeName = storeName;
        this.storeNumber = storeNumber;
        this.calories = calories;
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
    
}
