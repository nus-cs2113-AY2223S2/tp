package seedu.rainyDay.data;

public class AllData {
    private final UserData userData;
    private final MonthlyExpenditures monthlyExpenditures;

    public AllData(UserData userData, MonthlyExpenditures monthlyExpenditures) {
        this.userData = userData;
        this.monthlyExpenditures = monthlyExpenditures;
    }

    public UserData getUserData() {
        return userData;
    }

    public MonthlyExpenditures getMonthlyExpenditures() {
        return monthlyExpenditures;
    }

}
