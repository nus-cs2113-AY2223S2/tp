public class Income {
    private double amount;
    private String info;
    private String time;

    public Income(double amount, String info, String time) {
        this.amount = amount;
        this.info = info;
        this.time = time;
    }

    /**
     * Edits the amount of the income
     * @param newAmount new input amount of the income
     */
    public void editIncome(double newAmount) {
        this.amount = newAmount;
    }

    /**
     * Edits the information entry of the income
     * @param newInfo new input information of the income
     */
    public void editInfo(String newInfo) {
        this.info = newInfo;
    }


    public double getIncome() {
        return this.amount;
    }

    public String toString() {
        return String.format("$%.2f spent on *s - %s", amount, info, time);
    }
}
