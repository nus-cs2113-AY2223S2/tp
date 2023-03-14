package seedu.bigpp.component.chassis;

import seedu.bigpp.component.Component;

public class Chassis extends Component {
    public static final String COMPONENT_STRING = "chassis";

    private String size;

    public Chassis(String name, String brand, float price, String size) {
        super.setName(name);
        super.setBrand(brand);
        super.setPrice(price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Chassis{" +
                "\nname='" + super.getName() + '\'' +
                ",\n brand='" + super.getBrand() + '\'' +
                ",\n price=" + super.getPrice() +
                ",\n size='" + size + '\'' +
                "\n}";
    }
}
