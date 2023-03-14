package seedu.bigpp.component.gpu;

import seedu.bigpp.component.Component;

public class GPU extends Component {
    public static final String COMPONENT_STRING = "gpu";
    private float power;
    private String size;

    public GPU(String name, String brand, float price, float power, String size) {
        super.setName(name);
        super.setBrand(brand);
        super.setPrice(price);
        this.power = power;
        this.size = size;
    }

    public float getPower() {
        return power;
    }

    public String getSize() {
        return size;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "NAME: " + super.getName() +
                "\nBRAND: " + super.getBrand() +
                "\nPRICE: " + super.getPrice() +
                "\nPOWER: " + power +
                "\nSIZE: " + size;
    }
}
