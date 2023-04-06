package seedu.rainyDay.data;

public class UserData {
    private final SavedData savedData;
    private final MonthlyExpenditures monthlyExpenditures;

    public UserData(SavedData savedData, MonthlyExpenditures monthlyExpenditures) {
        this.savedData = savedData;
        this.monthlyExpenditures = monthlyExpenditures;
    }

    public SavedData getSavedData() {
        return savedData;
    }

    public MonthlyExpenditures getMonthlyExpenditures() {
        return monthlyExpenditures;
    }

}
