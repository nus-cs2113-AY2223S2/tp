package seedu.duke.objects;

public class Alert {
    private String upc;
    private String minmax;
    private Integer stock;

    public Alert (String upc, String minmax, String stock) {
        this.upc = upc;
        this.minmax = minmax;
        this.stock = Integer.parseInt(stock);
    }

    public String getUpc() {
        return upc;
    }

    public String getMinmax() {
        return minmax;
    }

    public Integer getStock() {
        return stock;
    }

}
