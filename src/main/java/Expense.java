public class Expense {
    private double cost;
    private String info;
    private String time;

    public Expense(double cost, String info, String time) {
        this.cost = cost;
        this.info = info;
        this.time = time;
    }

    /**
     * Edits cost entry of the expense
     *
     * @param newCost New desired cost of the expense
     */
    public void editCost(double newCost) {
        this.cost = newCost;
    }

    /**
     * Edits information entry of the expense
     *
     * @param newInfo New desired information of the expense
     */
    public void editInfo(String newInfo) {
        this.info = newInfo;
    }

    /**
     * Edits time entry of the expense
     *
     * @param newTime New desired time of the expense
     */
    public void editTime(String newTime) {
        this.time = newTime;
    }

    public double getCost() {
        return this.cost;
    }

    public String toString() {
        return String.format("$%.2f spent on %s - %s", cost, info, time);
    }
}
